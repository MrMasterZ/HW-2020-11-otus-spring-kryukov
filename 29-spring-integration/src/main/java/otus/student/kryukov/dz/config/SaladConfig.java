package otus.student.kryukov.dz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import otus.student.kryukov.dz.domain.*;

@Configuration
public class SaladConfig {

    @Bean
    public QueueChannel ingredientChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel componentChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow saladFlow() {
        return IntegrationFlows.from("ingredientChannel")
                .log()
                .split()
                .<Ingredient, Boolean>route(i -> i instanceof Boil,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf.handle("boilService", "boil")
                                )
                                .subFlowMapping(false, sf -> sf.handle("skipService", "skip"))
                )
                .<Ingredient, Boolean>route(i -> i instanceof Fry,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf.handle("fryService", "fry")
                                )
                                .subFlowMapping(false, sf -> sf.handle("skipService", "skip"))
                )
                .<Ingredient, Boolean>route(i -> i instanceof Clean,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf.handle("cleanService", "clean")
                                )
                                .subFlowMapping(false, sf -> sf.handle("skipService", "skip"))
                )
                .<Ingredient, Boolean>route(i -> i instanceof Extract,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf.<Ingredient, String>route(i -> i.getCannedType(),
                                        subMapping -> subMapping
                                                .subFlowMapping("peas", sf2 -> sf2.transform(Ingredient.class, obj -> new GreenPeas())
                                                )
                                                .subFlowMapping("fish", sf2 -> sf2.transform(Ingredient.class, obj -> new CannedFishes())
                                                )
                                                .defaultSubFlowMapping(sf2 -> sf2.handle("errorService", "error"))

                                ))
                                .subFlowMapping(false, sf -> sf.handle("skipService", "skip"))
                )
                .<Ingredient, Boolean>route(i -> i instanceof Grind,
                        mapping -> mapping
                                .subFlowMapping(true, sf -> sf.handle("grindService", "grind")
                                )
                                .subFlowMapping(false, sf -> sf.handle("skipService", "skip"))
                )
                .handle("addService", "add")
                .aggregate()
                .channel("componentChannel")
                .get();
    }
}
package otus.student.kryukov.dz;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import otus.student.kryukov.dz.domain.Component;
import otus.student.kryukov.dz.domain.Ingredient;

import java.util.Collection;

@MessagingGateway
public interface Salad {

    @Gateway(requestChannel = "ingredientChannel", replyChannel = "componentChannel")
    Collection<Component> process(Collection<Ingredient> ingredients);
}

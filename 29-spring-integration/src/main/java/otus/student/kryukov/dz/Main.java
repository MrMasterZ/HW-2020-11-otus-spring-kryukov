package otus.student.kryukov.dz;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import otus.student.kryukov.dz.domain.*;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

@IntegrationComponentScan
@SuppressWarnings({ "resource", "Duplicates", "InfiniteLoopStatement" })
@ComponentScan
@EnableIntegration
public class Main {
    private static final Ingredient[] OLIVIE_SALAD = {new Potato(), new Carrot(), new Egg(), new Sausage(), new Onion(), new Pickle(), new CanOfGreenPeas(), new Mayonnaise(), new Salt()};
    private static final Ingredient[] CAESAR_SALAD = {new Egg(), new ChickenFillet(), new Nut(), new Tomato(), new Lettuce(), new Cheese(), new Crouton(), new Sauce()};
    private static final Ingredient[] MIMOSA_SALAD = {new Potato(), new Carrot(), new Egg(), new Onion(), new CanOfCannedFishes(), new Mayonnaise(), new Salt()};
    private static final Ingredient[] SEA_SALAD = {new Egg(), new Squid(), new Shrimp(), new Crabstick(), new RedCaviar(), new Mayonnaise(), new Salt()};
    private static final List<Ingredient[]> MENU_SALAD = List.of(OLIVIE_SALAD, CAESAR_SALAD, MIMOSA_SALAD, SEA_SALAD);
    private static String saladName;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        Salad salad = context.getBean(Salad.class);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        while ( true ) {
            Thread.sleep( 3000 );
            pool.execute(() -> {
                salad.process(List.of(getIngredientsForSalad()));
                System.out.println(saladName + " is ready. Bon appetit");
            });
        }
    }

    private static Ingredient[] getIngredientsForSalad() {
        int indexSalad = RandomUtils.nextInt(0, 4);
        infoSalad(indexSalad);
        return MENU_SALAD.get(indexSalad);
    }


    private static void infoSalad(int indexSalad) {
        switch (indexSalad) {
            case 0:
                saladName = "OLIVIE SALAD";
                break;
            case 1:
                saladName = "CAESAR SALAD";
                break;
            case 2:
                saladName = "MIMOSA SALAD";
                break;
            case 3:
                saladName = "SEA SALAD";
                break;
        }
        System.out.println("Start cooking " + saladName);
    }
}
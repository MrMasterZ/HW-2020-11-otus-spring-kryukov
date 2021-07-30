package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class BoilService {

    public Ingredient boil(Ingredient ingredient) {
        System.out.println("boil " + ingredient.getName());
        return ingredient;
    }
}

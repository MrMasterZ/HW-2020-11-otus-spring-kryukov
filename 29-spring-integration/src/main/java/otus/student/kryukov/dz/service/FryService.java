package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class FryService {

    public Ingredient fry(Ingredient ingredient) {
        System.out.println("fry " + ingredient.getName());
        return ingredient;
    }
}

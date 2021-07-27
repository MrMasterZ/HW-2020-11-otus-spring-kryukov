package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class CleanService {

    public Ingredient clean(Ingredient ingredient) {
        System.out.println("clean " + ingredient.getName());
        return ingredient;
    }
}

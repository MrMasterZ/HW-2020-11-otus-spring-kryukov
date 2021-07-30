package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class ErrorService {

    public Ingredient error(Ingredient ingredient) {
        System.out.println("I don't know how to open this can, add this can in the salad");
        return ingredient;
    }
}

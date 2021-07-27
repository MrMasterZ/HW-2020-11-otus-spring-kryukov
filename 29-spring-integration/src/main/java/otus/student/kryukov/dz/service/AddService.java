package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class AddService {

    public Ingredient add(Ingredient ingredient) {
        System.out.println("add " + ingredient.getName());
        return ingredient;
    }
}

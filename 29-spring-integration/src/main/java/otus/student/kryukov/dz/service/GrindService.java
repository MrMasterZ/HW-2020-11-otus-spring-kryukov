package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class GrindService {

    public Ingredient grind(Ingredient ingredient) {
        System.out.println("grind " + ingredient.getName());
        return ingredient;
    }
}

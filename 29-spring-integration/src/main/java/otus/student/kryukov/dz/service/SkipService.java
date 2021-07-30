package otus.student.kryukov.dz.service;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Ingredient;

@Service
public class SkipService {

    public Ingredient skip(Ingredient ingredient) {
        return ingredient;
    }

}

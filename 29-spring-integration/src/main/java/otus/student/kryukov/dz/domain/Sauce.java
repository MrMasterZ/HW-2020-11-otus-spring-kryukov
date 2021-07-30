package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Sauce extends Ingredient {

    private final static String NAME = "sauce";

    @Override
    public String getName() {
        return NAME;
    }
}

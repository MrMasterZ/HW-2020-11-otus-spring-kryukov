package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Mayonnaise extends Ingredient {

    private final static String NAME = "mayonnaise";

    @Override
    public String getName() {
        return NAME;
    }
}

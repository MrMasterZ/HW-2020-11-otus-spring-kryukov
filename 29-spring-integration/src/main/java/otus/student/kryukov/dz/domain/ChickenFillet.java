package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChickenFillet extends Ingredient implements Fry, Grind {

    private final static String NAME = "chicken fillet";

    @Override
    public String getName() {
        return NAME;
    }
}

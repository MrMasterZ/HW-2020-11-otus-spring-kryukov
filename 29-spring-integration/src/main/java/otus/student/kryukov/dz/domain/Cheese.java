package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Cheese extends Ingredient implements Grind {

    private final static String NAME = "cheese";

    @Override
    public String getName() {
        return NAME;
    }
}

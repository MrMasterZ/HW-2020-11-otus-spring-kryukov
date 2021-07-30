package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Tomato extends Ingredient implements Grind {

    private final static String NAME = "tomatoes";

    @Override
    public String getName() {
        return NAME;
    }
}

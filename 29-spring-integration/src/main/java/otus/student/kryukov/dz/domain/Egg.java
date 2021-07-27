package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Egg extends Ingredient implements Boil, Clean, Grind {

    private final static String NAME = "eggs";

    @Override
    public String getName() {
        return NAME;
    }
}

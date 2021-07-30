package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Potato extends Ingredient implements Boil, Clean, Grind {

    private final static String NAME = "potatoes";

    @Override
    public String getName() {
        return NAME;
    }

}

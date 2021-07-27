package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Carrot extends Ingredient implements Boil, Clean, Grind {

    private final static String NAME = "carrots";

    @Override
    public String getName() {
        return NAME;
    }

}

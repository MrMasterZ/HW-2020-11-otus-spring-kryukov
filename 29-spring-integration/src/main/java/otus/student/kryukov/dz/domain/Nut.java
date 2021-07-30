package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Nut extends Ingredient implements Clean, Grind {

    private final static String NAME = "nuts";

    @Override
    public String getName() {
        return NAME;
    }
}

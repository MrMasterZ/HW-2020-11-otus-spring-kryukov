package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Onion extends Ingredient implements Clean, Grind {

    private final static String NAME = "onions";

    @Override
    public String getName() {
        return NAME;
    }
}

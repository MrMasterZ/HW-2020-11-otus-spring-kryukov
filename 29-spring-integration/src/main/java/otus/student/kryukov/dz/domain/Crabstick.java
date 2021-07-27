package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Crabstick extends Ingredient implements Grind {

    private final static String NAME = "crabsticks";

    @Override
    public String getName() {
        return NAME;
    }
}

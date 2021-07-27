package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Sausage extends Ingredient implements Clean, Grind {

    private final static String NAME = "sausages";

    @Override
    public String getName() {
        return NAME;
    }
}

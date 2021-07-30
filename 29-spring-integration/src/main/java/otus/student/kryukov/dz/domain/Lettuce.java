package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Lettuce extends Ingredient implements Grind {

    private final static String NAME = "lettuce";

    @Override
    public String getName() {
        return NAME;
    }
}

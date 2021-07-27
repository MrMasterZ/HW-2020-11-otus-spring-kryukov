package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Pickle extends Ingredient implements Grind {

    private final static String NAME = "pickles";

    @Override
    public String getName() {
        return NAME;
    }
}

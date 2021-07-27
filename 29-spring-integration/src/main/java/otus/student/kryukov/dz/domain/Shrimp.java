package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Shrimp extends Ingredient implements Boil, Clean {

    private final static String NAME = "shrimps";

    @Override
    public String getName() {
        return NAME;
    }
}

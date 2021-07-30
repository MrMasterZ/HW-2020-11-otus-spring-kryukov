package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Salt extends Ingredient {

    private final static String NAME = "salt";

    @Override
    public String getName() {
        return NAME;
    }
}

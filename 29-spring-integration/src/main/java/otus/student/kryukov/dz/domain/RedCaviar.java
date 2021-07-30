package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RedCaviar extends Ingredient {

    private final static String NAME = "red caviar";

    @Override
    public String getName() {
        return NAME;
    }
}

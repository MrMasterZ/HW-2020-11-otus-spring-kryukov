package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Crouton extends Ingredient {

    private final static String NAME = "croutons";

    @Override
    public String getName() {
        return NAME;
    }
}

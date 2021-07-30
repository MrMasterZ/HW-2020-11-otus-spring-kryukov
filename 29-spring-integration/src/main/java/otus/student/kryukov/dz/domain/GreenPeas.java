package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GreenPeas extends Ingredient {

    private final static String NAME = "green peas";

    @Override
    public String getName() {
        return NAME;
    }
}

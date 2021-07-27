package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CannedFishes extends Ingredient implements Grind {

    private final static String NAME = "canned fishes";

    @Override
    public String getName() {
        return NAME;
    }

}

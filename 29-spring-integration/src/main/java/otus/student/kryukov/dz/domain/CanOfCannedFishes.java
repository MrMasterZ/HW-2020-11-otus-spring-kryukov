package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CanOfCannedFishes extends Ingredient implements Extract {

    private final static String NAME = "can of canned fishes";
    private final static String TYPE = "fish";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getCannedType() {
        return TYPE;
    }
}

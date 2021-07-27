package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CanOfGreenPeas extends Ingredient implements Extract {

    private final static String NAME = "can of green peas";
    private final static String TYPE = "peas";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getCannedType() {
        return TYPE;
    }
}

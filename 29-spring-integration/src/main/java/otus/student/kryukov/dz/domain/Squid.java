package otus.student.kryukov.dz.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Squid extends Ingredient implements Boil, Clean, Grind {

    private final static String NAME = "squids";

    @Override
    public String getName() {
        return NAME;
    }
}

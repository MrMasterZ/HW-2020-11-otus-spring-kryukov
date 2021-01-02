package otus.student.kryukov.dz.check;

import otus.student.kryukov.dz.domain.Question;

public interface Checker {

    public byte check(Question question, String answer);

}
package otus.student.kryukov.dz.check;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.auth.AuthServiceConsole;
import otus.student.kryukov.dz.domain.Question;

@Service
public class QuestionChecker implements Checker {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceConsole.class);

    @Override
    public byte check(Question question, String answer) {
        LOGGER.info("QuestionChecker.check()");
        byte mark;
        if (parseQuestionCount(question) == parseAnswerCount(answer)) mark = 1; else mark = 0;
        return mark;
    }

    private static final int parseQuestionCount(Question question) {
        byte n1;
        byte n2;
        byte n3;
        byte n4;
        byte n5;

        if (question.getMark1() == null) n1 = 0;
        else n1 = Byte.parseByte(question.getMark1());
        if (question.getMark2() == null) n2 = 0;
        else n2 = Byte.parseByte(question.getMark2());
        if (question.getMark3() == null) n3 = 0;
        else n3 = Byte.parseByte(question.getMark3());
        if (question.getMark4() == null) n4 = 0;
        else n4 = Byte.parseByte(question.getMark4());
        if (question.getMark5() == null) n5 = 0;
        else n5 = Byte.parseByte(question.getMark5());

        return count(n1, n2, n3, n4, n5);
    }

    private static final int parseAnswerCount(String answer) {
        byte n1;
        byte n2;
        byte n3;
        byte n4;
        byte n5;

        if (answer.contains("1")) n1 = 1;
        else n1 = 0;
        if (answer.contains("2")) n2 = 1;
        else n2 = 0;
        if (answer.contains("3")) n3 = 1;
        else n3 = 0;
        if (answer.contains("4")) n4 = 1;
        else n4 = 0;
        if (answer.contains("5")) n5 = 1;
        else n5 = 0;

        return count(n1, n2, n3, n4, n5);
    }

    private static final int count(byte n1, byte n2, byte n3, byte n4, byte n5) {
        return n1 * 10000 + n2 * 1000 + n3 * 100 + n4 * 10 + n5;
    }
}
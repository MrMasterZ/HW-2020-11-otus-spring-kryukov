package otus.student.kryukov.dz.check;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import otus.student.kryukov.dz.Main;
import otus.student.kryukov.dz.domain.Question;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("Класс QuestionChecker")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
public class QuestionCheckerTest {
    private static Checker checker;

    @Autowired
    public QuestionCheckerTest(Checker checker) {
        this.checker = checker;
    }

    @DisplayName("проверка ответов проводится корректно")
    @Test
    void checkCorrect() {
        Question question = new Question("What annotation must a class have in order for it to be found by @ComponentScan and added in context of Spring? (check all correct answers)", "@Registry", "0", "@Service", "1", "@Repository", "1", "@Configuration", "0", "@Controller", "1");
        String answer = "235";
        assertEquals(1, checker.check(question, answer), "Error! QuestionChecker method check doesn't work");
    }
}

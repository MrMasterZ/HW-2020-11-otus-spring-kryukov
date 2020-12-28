package otus.student.kryukov.dz.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import otus.student.kryukov.dz.domain.Question;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Класс CSVStrategyReaderImpl")
@ExtendWith(MockitoExtension.class)
public class CSVStrategyReaderImplTest {

    private ReaderStrategyQuestion strategy;
    private CSVStrategyReader csvStrategyReader;
    private String csvFile = "/spring-test-for-test.csv";

    @BeforeEach
    void setUp() {
        strategy = Mockito.spy(ReaderStrategyQuestion.class);
        csvStrategyReader = new CSVStrategyReaderImpl(csvFile, strategy);
    }

    @DisplayName("корректно читает CSV-файл по стратегии")
    @Test
    void readCorrect() {
        List<Object> listExpected = new ArrayList<>();
        listExpected.add(new Question("What annotation must a class have in order for it to be found by @ComponentScan and added in context of Spring? (check all correct answers)", "@Registry", "0", "@Service", "1", "@Repository", "1", "@Configuration", "0", "@Controller", "1"));
        listExpected.add(new Question("What annotation can be used to injection dependency (DI)? (check all correct answers)", "@Inject", "1", "@Joined", "0", "@Autowired", "1", "@Resource", "1", null, null));
        listExpected.add(new Question("What is a BeanDefinition?", "interface for accessing future bean metadata", "1", "class for accessing future bean metadata", "0", "interface for creating bean", "0", null, null, null, null));
        listExpected.add(new Question("What are the life cycle statuses of Entity object in Hibernate? (check all correct answers)", "transient", "1", "managed", "0", "persistent", "1", "detached", "1", "removed", "1"));
        listExpected.add(new Question("What are the types of collections in Hibernate? (continue this list: Set Map List)", "Bag", "1", "Slot", "0", "Array", "1", "Queue", "0", null, null));

        Assertions.assertEquals(listExpected, csvStrategyReader.read(), "Error read csv-file " + csvFile + " (class: CSVStrategyReaderImpl)");
    }
}
package otus.student.kryukov.dz.dao;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Question;

@Service
public class ReaderStrategyQuestion implements ReaderStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderStrategyQuestion.class);

    @Override
    public ColumnPositionMappingStrategy get() {
        LOGGER.info("ReaderStrategyQuestion.get()");
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Question.class);
        String[] columns = new String[]{"question", "answer1", "mark1", "answer2", "mark2", "answer3", "mark3", "answer4", "mark4", "answer5", "mark5"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
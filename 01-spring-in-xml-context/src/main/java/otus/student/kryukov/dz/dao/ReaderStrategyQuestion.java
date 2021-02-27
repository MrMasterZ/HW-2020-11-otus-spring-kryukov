package otus.student.kryukov.dz.dao;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import otus.student.kryukov.dz.domain.Question;

public class ReaderStrategyQuestion implements ReaderStrategy {

    @Override
    public ColumnPositionMappingStrategy get() {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Question.class);
        String[] columns = new String[]{"question", "answer1", "answer2", "answer3", "answer4", "answer5"};
        strategy.setColumnMapping(columns);
        return strategy;
    }
}
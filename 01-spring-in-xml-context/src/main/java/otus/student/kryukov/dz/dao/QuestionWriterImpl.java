package otus.student.kryukov.dz.dao;

import otus.student.kryukov.dz.domain.Question;

import java.lang.reflect.Field;
import java.util.List;

public class QuestionWriterImpl implements QuestionWriter {

    @Override
    public void write(List<Object> list) {
        for (Object object : list) {
            writeEachQuestion((Question) object);
        }
    }

    private static final void writeEachQuestion(Question question) {
        try {
            for (Field field : question.getClass().getDeclaredFields()) {
                if (!field.getName().equals("serialVersionUID")) {
                    field.setAccessible(true);
                    Object value = field.get(question);
                    if (value != null) {
                        System.out.println(value);
                    }
                }
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
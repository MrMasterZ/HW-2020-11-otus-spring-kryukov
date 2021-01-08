package otus.student.kryukov.dz.dao;

import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.domain.Question;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.student.kryukov.dz.io.IOService;

@Service
public class QuestionWriterImpl implements QuestionWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionWriterImpl.class);
    private final IOService ioService;

    public QuestionWriterImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void writeQuestion(Question question) {
        LOGGER.info("QuestionWriterImpl.writeQuestion(Question question)");
        int n = 0;
        try {
            for (Field field : question.getClass().getDeclaredFields()) {
                if (!field.getName().equals("serialVersionUID") && !field.getName().regionMatches(0, "mark", 0, 4)) {
                    field.setAccessible(true);
                    Object value = field.get(question);
                    if (value != null) {
                        if (n == 0) {
                            ioService.out(value.toString());
                        } else {
                            ioService.out(n + ") " + value.toString());
                        }
                        n++;
                    }
                }
            }
            ioService.out("");
        } catch (Exception e) {
            LOGGER.error("QuestionWriterImpl.writeEachQuestion(Question question) error:" + e.getMessage());
        }
    }
}
package otus.student.kryukov.dz.dao;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@PropertySource("classpath:application.properties")
@Service
public class CSVStrategyReaderImpl implements CSVStrategyReader {
    private final InputStream inputFile;
    private final ReaderStrategy strategy;
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVStrategyReaderImpl.class);

    public CSVStrategyReaderImpl(@Value("${csv.file.name}") String csvFileName, ReaderStrategy strategy) {
        this.inputFile = CSVStrategyReaderImpl.class.getResourceAsStream(csvFileName);
        this.strategy = strategy;
    }

    @Override
    public List<Object> read() {
        LOGGER.info("CSVStrategyReaderImpl.read()");
        List<Object> listObjects = null;
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputFile))) {
            listObjects = new CsvToBean().parse(strategy.get(), csvReader);
        } catch (Exception e) {
            LOGGER.error("CSVStrategyReaderImpl.read() error:" + e.getMessage());
        }
        return listObjects;
    }
}
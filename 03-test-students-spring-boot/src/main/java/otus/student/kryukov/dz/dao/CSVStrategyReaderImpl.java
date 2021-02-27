package otus.student.kryukov.dz.dao;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import otus.student.kryukov.dz.configs.AppProps;
import otus.student.kryukov.dz.configs.AppPropsTest;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

@EnableConfigurationProperties(AppProps.class)
@Service
public class CSVStrategyReaderImpl implements CSVStrategyReader {
    private final InputStream inputFile;
    private final ReaderStrategy strategy;
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVStrategyReaderImpl.class);

    @Autowired
    public CSVStrategyReaderImpl(AppProps props, ReaderStrategy strategy) {
        String csvFileName;
        if (props.getLocale().equals(new Locale("ru", "RU"))) csvFileName = props.getCsvfilename_ru();
        else csvFileName = props.getCsvfilename_en();
        this.inputFile = CSVStrategyReaderImpl.class.getResourceAsStream(csvFileName);
        this.strategy = strategy;
    }

    // использую перегрузку конструктора для того, чтобы в тестах я мог создавать этот класс (CSVStrategyReaderImpl), но уже с prefix = "test"
    public CSVStrategyReaderImpl(AppPropsTest propsTest, ReaderStrategy strategy) {
        this.inputFile = CSVStrategyReaderImpl.class.getResourceAsStream(propsTest.getCsvfilename());
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
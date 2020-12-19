package otus.student.kryukov.dz.dao;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVStrategyReaderImpl implements CSVStrategyReader {
    private InputStream inputFile;
    private ReaderStrategy strategy;

    public CSVStrategyReaderImpl(String csvFileName, ReaderStrategy strategy) {
        this.inputFile = CSVStrategyReaderImpl.class.getResourceAsStream(csvFileName);
        this.strategy = strategy;
    }

    @Override
    public List<Object> read() {
        List<Object> listObjects = null;
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputFile))) {
            listObjects = new CsvToBean().parse(strategy.get(), csvReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listObjects;
    }
}
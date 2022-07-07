package com.luxoft.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.ArrayUtils;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Consumer;

public class CsvParser implements Parser {

    private final Consumer<String[]> consumer;

    public CsvParser(Consumer<String[]> consumer) {
        Objects.requireNonNull(consumer);
        this.consumer = consumer;
    }

    @Override
    public void parse(String fileName) {
        try (FileReader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(fileReader)) {
            String[] nextRow;
            //skip title line
            String[] titles = reader.readNext();
            while ((nextRow = reader.readNext()) != null) {
                if (ArrayUtils.isNotEmpty(nextRow)) {
                    consumer.accept(nextRow);
                }
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.luxoft.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.Consumer;

public class CsvParser implements Parser {

    private final Consumer<String[]> parser;

    public CsvParser(Consumer<String[]> parser) {
        Objects.requireNonNull(parser);
        this.parser = parser;
    }

    @Override
    public void parse(String fileName) {
        try (FileReader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(fileReader)) {
            String[] nextRow;
            while ((nextRow = reader.readNext()) != null) {
                parser.accept(nextRow);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}

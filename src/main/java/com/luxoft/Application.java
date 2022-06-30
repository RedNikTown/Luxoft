package com.luxoft;

import com.luxoft.convertor.Convertor;
import com.luxoft.convertor.RowConvertor;
import com.luxoft.parser.CsvParser;
import com.luxoft.parser.Parser;
import com.luxoft.writer.StdOutWriter;
import com.luxoft.writer.Writer;

public class Application {
    private final static String FILE_NAME = "src/main/resources/input-data.csv";

    public static void main(String[] args) {
        Convertor<String[], String> convertor = new RowConvertor();
        Writer<String[]> writer = new StdOutWriter(convertor::convert);
        Parser parser = new CsvParser(writer::write);
        parser.parse(FILE_NAME);
    }
}

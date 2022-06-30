package com.luxoft.parser;

import com.luxoft.writer.StdOutWriter;
import com.luxoft.writer.Writer;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.junit.Assert.assertNotNull;

public class ParserTest {

    private final static String FILE_NAME = "src/main/resources/unit-test-data.csv";

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Consumer<String[]> consumer;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Writer<String[]> writer = new StdOutWriter();
        consumer = writer::write;
    }


    @Test(expected = RuntimeException.class)
    public void exceptionIfNotFindFile() {
        Parser parser = new CsvParser(consumer);
        parser.parse("test");
    }

    @Test(expected = NullPointerException.class)
    public void exceptionIfConsumerNull() {
        Parser parser = new CsvParser(null);
    }

    @Test(expected = NullPointerException.class)
    public void exceptionIfFileNameNull() {
        Parser parser = new CsvParser(consumer);
        parser.parse(null);
    }

    @Test
    public void checkThatConsumerWorks() {
        Parser parser = new CsvParser(consumer);
        parser.parse(FILE_NAME);
        assertNotNull(outputStreamCaptor.toString().trim());
    }
}
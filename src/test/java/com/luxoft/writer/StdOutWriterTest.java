package com.luxoft.writer;

import com.luxoft.convertor.Convertor;
import com.luxoft.convertor.RowConvertor;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class StdOutWriterTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test(expected = NullPointerException.class)
    public void exceptionIfConsumerNull() {
        Writer<String[]> parser = new StdOutWriter(null);
    }

    @Test
    public void checkThatWriterWithDefaultConstructorOutputsDataToConsole() {
        String[] actual = {"test1", "test2"};
        String expected = "test1 test2";
        Writer<String[]> writer = new StdOutWriter();
        writer.write(actual);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void checkThatWriterWithConvectorConvertDataAndOutputsDataToConsole() {
        Convertor<String[], String> convertor = new RowConvertor();
        String[] actual = {"test1", "test2"};
        String expected = "test1, test2";
        Writer<String[]> writer = new StdOutWriter(convertor::convert);
        writer.write(actual);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
package com.luxoft.parser;

import com.luxoft.agregator.TeamEffortDataAggregator;
import com.luxoft.convertor.RawDataToEffortConvertor;
import com.luxoft.model.Effort;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertNotNull;

public class ParserTest {

    private final static String FILE_NAME = "src/main/resources/unit-test-data.csv";

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Consumer<String[]> rawDataConsumer;
    private TeamEffortDataAggregator teamEffortAggregator;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Function<String[], Effort> rawToEffortConvertor = RawDataToEffortConvertor::convert;
        teamEffortAggregator = new TeamEffortDataAggregator(rawToEffortConvertor);
        rawDataConsumer = teamEffortAggregator::aggregateTeamEffort;
    }

    @Test(expected = RuntimeException.class)
    public void exceptionIfNotFindFile() {
        Parser parser = new CsvParser(rawDataConsumer);
        parser.parse("test");
    }

    @Test(expected = NullPointerException.class)
    public void exceptionIfConsumerNull() {
        Parser parser = new CsvParser(null);
    }

    @Test(expected = NullPointerException.class)
    public void exceptionIfFileNameNull() {
        Parser parser = new CsvParser(rawDataConsumer);
        parser.parse(null);
    }

    @Test
    public void checkThatConsumerWorks() {
        Parser parser = new CsvParser(rawDataConsumer);
        parser.parse(FILE_NAME);
        assertNotNull(teamEffortAggregator.getTeamEfforts());
    }
}
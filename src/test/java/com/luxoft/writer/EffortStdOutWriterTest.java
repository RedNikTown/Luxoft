package com.luxoft.writer;

import com.luxoft.model.Effort;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class EffortStdOutWriterTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final EffortStdOutWriter effortStdOutWriter = new EffortStdOutWriter();

    Collection<Effort> efforts;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        efforts = new ArrayList<>();
        efforts.add(new Effort("London", 0, 30000));
    }

    @Test
    public void checkThatWriterPrintListOfEffortInTerminal() {
        String expected = "Team, Total Effort, Remaining Effort" + System.lineSeparator() +
                "London, 0.0, 30000.0";
        effortStdOutWriter.write(efforts);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void checkThatWriterPrintEmptyListInTerminal() {
        String expected = "Team, Total Effort, Remaining Effort";
        effortStdOutWriter.write(new ArrayList<>());
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
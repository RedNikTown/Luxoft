package com.luxoft.convertor;

import com.luxoft.model.Effort;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class RawDataToEffortConvertorTest {

    @Test
    public void checkConvert() {
        String[] actual = {"MCPU-10140", "2748508", "2660783", "QA Design- Notify Parties Assignment Confirmed",
                "Open", "Sub-task", "28800", "Medium", "London"};
        Effort expectedEffort = new Effort("London", 0, 28800);
        Function<String[], Effort> convertor = RawDataToEffortConvertor::convert;
        assertEquals(expectedEffort, convertor.apply(actual));
    }

    @Test
    public void checkConvertWithNull() {
        Function<String[], Effort> convertor = RawDataToEffortConvertor::convert;
        assertEquals(new Effort(), convertor.apply(null));
    }

    @Test
    public void checkConvertWithEmptyRow() {
        String[] actual = new String[0];
        Function<String[], Effort> convertor = RawDataToEffortConvertor::convert;
        assertEquals(new Effort(), convertor.apply(actual));
    }
}
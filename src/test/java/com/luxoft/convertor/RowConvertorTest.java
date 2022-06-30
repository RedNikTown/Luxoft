package com.luxoft.convertor;

import org.junit.Assert;
import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RowConvertorTest {

    @Test
    public void checkConvert() {
        String[] actual = {"test1", "test2"};
        String expected = "test1, test2";
        Convertor<String[], String> convertor = new RowConvertor();
        Assert.assertEquals(expected, convertor.convert(actual));
    }

    @Test
    public void checkConvertWithNull() {
        Convertor<String[], String> convertor = new RowConvertor();
        Assert.assertEquals(EMPTY, convertor.convert(null));
    }

    @Test
    public void checkConvertWithEmptyRow() {
        String[] actual = new String[0];
        Convertor<String[], String> convertor = new RowConvertor();
        Assert.assertEquals(EMPTY, convertor.convert(actual));
    }
}
package com.luxoft.convertor;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RowConvertor implements Convertor<String[], String> {

    @Override
    public String convert(String[] strings) {
        return Optional.ofNullable(strings)
                .map(s -> String.join(", ", s))
                .orElse(EMPTY);
    }
}

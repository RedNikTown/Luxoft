package com.luxoft.writer;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class StdOutWriter implements Writer<String[]> {

    Function<String[], String> rowFormatter;

    public StdOutWriter() {
        rowFormatter = Line -> Optional.ofNullable(Line)
                .map(s -> String.join(" ", s))
                .orElse(EMPTY);
    }

    public StdOutWriter(Function<String[], String> rowFormatter) {
        Objects.requireNonNull(rowFormatter);
        this.rowFormatter = rowFormatter;
    }

    @Override
    public void write(String[] rowData) {
        System.out.println(rowFormatter.apply(rowData));
    }
}

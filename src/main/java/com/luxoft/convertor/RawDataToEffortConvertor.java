package com.luxoft.convertor;

import com.luxoft.model.Effort;

public final class RawDataToEffortConvertor {

    private static final int TEAM_COLUMN = 8;
    private static final int ESTIMATE_COLUMN = 6;
    private static final int STATUS_COLUMN = 4;

    private static final String CLOSED_STATUS = "Closed - Complete";

    private RawDataToEffortConvertor() {
    }

    public static Effort convert(String[] strings) {
        if (strings == null || strings.length < 9) {
            return new Effort();
        }
        String team = strings[TEAM_COLUMN];
        double estimate = Double.parseDouble(strings[ESTIMATE_COLUMN]);
        String status = strings[STATUS_COLUMN];
        return new Effort(
                team,
                CLOSED_STATUS.equals(status) ? estimate : 0,
                estimate
        );
    }
}

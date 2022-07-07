package com.luxoft.writer;

import com.luxoft.model.Effort;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

public class EffortStdOutWriter {

    public void write(Collection<Effort> effortData) {
        System.out.println("Team, Total Effort, Remaining Effort");
        if (CollectionUtils.isNotEmpty(effortData)) {
            effortData.forEach(System.out::println);
        }
    }
}

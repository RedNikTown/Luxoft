package com.luxoft.agregator;

import com.luxoft.model.Effort;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class TeamEffortDataAggregator {
    private final Map<String, Effort> effortMap;

    Function<String[], Effort> effortFormatter;

    public TeamEffortDataAggregator(Function<String[], Effort> effortFormatter) {
        Objects.requireNonNull(effortFormatter);
        this.effortFormatter = effortFormatter;
        effortMap = new HashMap<>();
    }

    public void aggregateTeamEffort(String[] data) {
        Effort issueEffort = effortFormatter.apply(data);
        Effort teamEffort = effortMap.computeIfAbsent(issueEffort.getTeam(), Effort::new);
        teamEffort.setRemainingEffort(teamEffort.getRemainingEffort() + issueEffort.getRemainingEffort());
        teamEffort.setTotalEffort(teamEffort.getTotalEffort() + issueEffort.getTotalEffort());
    }

    public Collection<Effort> getTeamEfforts() {
        return effortMap.values();
    }
}

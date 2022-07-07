package com.luxoft.agregator;

import com.luxoft.convertor.RawDataToEffortConvertor;
import com.luxoft.model.Effort;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TeamEffortDataAggregatorTest {

    private TeamEffortDataAggregator teamEffortAggregator;

    @Before
    public void setUp() {
        Function<String[], Effort> rawToEffortConvertor = RawDataToEffortConvertor::convert;
        teamEffortAggregator = new TeamEffortDataAggregator(rawToEffortConvertor);
    }

    @Test
    public void checkAggregateMethod() {
        String[] rawData1 = {"MCPU-10140", "2748508", "2660783", "QA Design- Notify Parties Assignment Confirmed",
                "Open", "Sub-task", "28800", "Medium", "London"};
        String[] rawData2 = {"MCPU-10140", "2748508", "2660783", "QA Design- Notify Parties Assignment Confirmed",
                "Closed - Complete", "Sub-task", "1200", "Medium", "London"};
        Map<String, Effort> map = new HashMap<>();
        map.put("London", new Effort("London", 1200, 30000));
        teamEffortAggregator.aggregateTeamEffort(rawData1);
        teamEffortAggregator.aggregateTeamEffort(rawData2);
        assertTrue(map.values().containsAll(teamEffortAggregator.getTeamEfforts()));
    }

    @Test
    public void checkGetTeamEffortMethod() {
        String[] rawData1 = {"MCPU-10140", "2748508", "2660783", "QA Design- Notify Parties Assignment Confirmed",
                "Open", "Sub-task", "28800", "Medium", "London"};
        teamEffortAggregator.aggregateTeamEffort(rawData1);
        assertNotNull(teamEffortAggregator.getTeamEfforts());
    }
}

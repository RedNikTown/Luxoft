package com.luxoft;

import com.luxoft.agregator.TeamEffortDataAggregator;
import com.luxoft.convertor.RawDataToEffortConvertor;
import com.luxoft.model.Effort;
import com.luxoft.parser.CsvParser;
import com.luxoft.parser.Parser;
import com.luxoft.writer.EffortStdOutWriter;

import java.util.function.Consumer;
import java.util.function.Function;

public class Application {
    private final static String FILE_NAME = "src/main/resources/input-data.csv";

    public static void main(String[] args) {
        Function<String[], Effort> rawDataToEffortConvertor = RawDataToEffortConvertor::convert;
        TeamEffortDataAggregator teamEffortAggregator = new TeamEffortDataAggregator(rawDataToEffortConvertor);
        Consumer<String[]> aggregateTeamEffortConsumer = teamEffortAggregator::aggregateTeamEffort;
        Parser parser = new CsvParser(aggregateTeamEffortConsumer);
        parser.parse(FILE_NAME);

        EffortStdOutWriter effortStdOutWriter = new EffortStdOutWriter();
        effortStdOutWriter.write(teamEffortAggregator.getTeamEfforts());
    }
}

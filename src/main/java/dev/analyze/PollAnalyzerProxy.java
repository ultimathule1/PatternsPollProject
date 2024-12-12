package dev.analyze;

import dev.PollFillingData;
import dev.analyze.strategy.AnalyzerStrategy;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class PollAnalyzerProxy extends PollAnalyzer {
    private String strategyName;
    private final PollAnalyzer delegate;

    public PollAnalyzerProxy(PollAnalyzer delegate) {
        super(null);
        this.delegate = delegate;
        strategyName = extractStrategyName();
    }

    @Override
    public void analyzePoll(List<PollFillingData> pollFillingDataList) {
        System.out.printf("\n*****Starting analyze poll by strategy: %s\n\n", strategyName);
        Instant start = Instant.now();
        delegate.analyzePoll(pollFillingDataList);
        Instant end = Instant.now();
        System.out.printf("*****Ending analyze poll by strategy: %s, totalAnalyzeTimeMS: %d",
                strategyName, Duration.between(start, end).toMillis());
    }

    @Override
    public void changeAnalyzerStrategy(AnalyzerStrategy strategy) {
        delegate.changeAnalyzerStrategy(strategy);
        strategyName = strategy.getClass().getSimpleName();
    }

    private String extractStrategyName() {
        try {
            Field tempField = delegate.getClass().getDeclaredField("strategy");
            tempField.setAccessible(true);
            return tempField.get(delegate).getClass().getSimpleName();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Strategy not found!");
            return "Unknown Strategy";
        }
    }
}

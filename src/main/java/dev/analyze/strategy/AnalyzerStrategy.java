package dev.analyze.strategy;

import dev.analyze.QuestionStatistics;

import java.util.List;

public interface AnalyzerStrategy {
    void makeAnalyze(List<QuestionStatistics> questionStatisticsList);
}

package dev.analyze.strategy;

import dev.analyze.QuestionStatistics;

import java.util.List;

public class FullCountStrategy implements AnalyzerStrategy{
    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        int totalAnswers = questionStatisticsList.stream()
                .mapToInt(q -> q.selectedVariantsCount().values().stream().reduce(0, Integer::sum))
                .sum();

        questionStatisticsList.forEach(questionStatistics -> {
            System.out.println("Question: " + questionStatistics.questionTitle());
            questionStatistics.selectedVariantsCount().forEach((variant, count) -> {
                System.out.println(count + " of " + totalAnswers + " chose: " + variant);
            });
        });
    }
}

package dev.analyze.strategy;

import dev.analyze.QuestionStatistics;

import java.util.List;

public class FullCountStrategy implements AnalyzerStrategy{
    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        int totalUsersChose = questionStatisticsList.getFirst().userSelectedVariantsCount().size();

        questionStatisticsList.forEach(questionStatistics -> {
            System.out.println("Question: " + questionStatistics.questionTitle());
            questionStatistics.selectedVariantsCount().forEach((variant, count) -> {
                System.out.println(count + " out of " + totalUsersChose + " chose: " + variant);
            });
            System.out.println();
        });
    }
}

package dev.analyze.strategy;

import dev.analyze.QuestionStatistics;

import java.util.List;
import java.util.Map;

public class LeastFrequentAnswerStrategy implements AnalyzerStrategy {
    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        questionStatisticsList.forEach(q -> {
            System.out.println("Question: " + q.questionTitle());

            Map.Entry<String, Integer> minAnswer = q.selectedVariantsCount().entrySet().stream()
                    .min(Map.Entry.comparingByValue()).orElseThrow();

            System.out.println("The least popular answer: "
                    + minAnswer.getKey());
            System.out.println(minAnswer.getValue() + " selected this option\n");
        });
    }
}

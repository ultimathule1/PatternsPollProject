package dev.analyze.strategy;

import dev.analyze.QuestionStatistics;

import java.util.List;
import java.util.Map;

public class MostFrequentAnswerStrategy implements AnalyzerStrategy{
    @Override
    public void makeAnalyze(List<QuestionStatistics> questionStatisticsList) {
        questionStatisticsList.forEach(q -> {
            System.out.println("Question: " + q.questionTitle());

            Map.Entry<String, Integer> minAnswer = q.userSelectedVariantsCount().entrySet().stream()
                    .max(Map.Entry.comparingByValue()).orElseThrow();

            System.out.println("Most popular answer: "
                    + minAnswer.getKey());
            System.out.println("Has been selected " + minAnswer.getValue() + " times");
        });
    }
}

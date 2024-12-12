package dev.analyze;

import dev.PollFillingData;
import dev.PollQuestionResponse;
import dev.analyze.strategy.AnalyzerStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PollAnalyzer {
    private AnalyzerStrategy strategy;

    public PollAnalyzer(AnalyzerStrategy strategy) {
        this.strategy = strategy;
    }

    public void changeAnalyzerStrategy(AnalyzerStrategy strategy) {
        this.strategy = strategy;
    }

    public void analyzePoll(List<PollFillingData> pollFillingDataList) {
        List<QuestionStatistics> statistics = collectStatistics(pollFillingDataList);
        strategy.makeAnalyze(statistics);
    }

    private List<QuestionStatistics> collectStatistics(List<PollFillingData> pollFillingDataList) {
        Map<String, QuestionStatistics> questionStatisticsMap = new HashMap<>();

        for (PollFillingData pollFillingData : pollFillingDataList) {
            String user = pollFillingData.username();

            for (PollQuestionResponse questionResponse : pollFillingData.responses()) {
                String questionName = questionResponse.pollQuestion().getTitle();

                //Добавление вопроса в список
                QuestionStatistics questionStatisticsNew = new QuestionStatistics(
                        questionName,
                        new HashMap<>(),
                        new HashMap<>()
                );
                questionStatisticsMap.putIfAbsent(questionName, questionStatisticsNew);

                QuestionStatistics questionStatisticsCurrent = questionStatisticsMap.get(questionName);
                //Подсчет выбраных ответов
                questionResponse.selectedVariants().forEach(selectedVariant -> {
                            questionStatisticsCurrent.selectedVariantsCount()
                                    .merge(selectedVariant, 1, Integer::sum);
                        });

                //Подсчет количества ответов от конкретного юзера
                questionStatisticsCurrent.userSelectedVariantsCount()
                        .merge(user, questionResponse.selectedVariants().size(), Integer::sum);

                questionStatisticsMap.put(questionName, questionStatisticsCurrent);
            }
        }

        return questionStatisticsMap.values().stream().toList();
    }
}

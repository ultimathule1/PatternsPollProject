package dev.facade;

import dev.analyze.PollAnalyzer;
import dev.analyze.PollAnalyzerProxy;
import dev.analyze.strategy.FullCountStrategy;
import dev.analyze.strategy.LeastFrequentAnswerStrategy;
import dev.analyze.strategy.MostFrequentAnswerStrategy;
import dev.poll.Poll;
import dev.poll.PollFillingData;
import dev.poll.PollQuestion;
import dev.poll.PollQuestionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PollLifecycleFacade {
    PollAnalyzer analyzer;

    public PollLifecycleFacade() {
        this.analyzer = new PollAnalyzerProxy(new PollAnalyzer(new FullCountStrategy()));
    }

    public Poll createPoll() {
        return Poll.builder()
                .withPollName("Programming Survey")
                .yesNoPollQuestion("Are you programmer?")
                .oneVariantPollQuestion("How many years of programming experience do you have?")
                .withAnswerVariant("0-1 years")
                .withAnswerVariant("1-3 years")
                .withAnswerVariant("3-5 years")
                .withAnswerVariant("5+ years")
                .and()
                .oneVariantPollQuestion("What is your favorite programming language?")
                .withAnswerVariant("Java")
                .withAnswerVariant("Python")
                .withAnswerVariant("C++")
                .and()
                .optionalQuestion("Do you like your job?")
                .withMaxAnswers(1)
                .withAnswerVariant("Who knows..")
                .withAnswerVariant("Of course")
                .and()
                .pollQuestion("What are your strong qualities?")
                .withMinAnswers(0)
                .withMaxAnswers(5)
                .withAnswerVariant("Leadership")
                .withAnswerVariant("Teamwork")
                .withAnswerVariant("Problem-solving")
                .withAnswerVariant("Communication")
                .withAnswerVariant("Creativity")
                .and()
                .build();
    }

    public List<PollFillingData> getUserResponses(Poll poll) {
        return generatePollFillingDataList(50, poll);
    }

    public void makeAnalyzePoll(List<PollFillingData> pollFillingDataList) {
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzerStrategy(new MostFrequentAnswerStrategy());
        analyzer.analyzePoll(pollFillingDataList);

        analyzer.changeAnalyzerStrategy(new LeastFrequentAnswerStrategy());
        analyzer.analyzePoll(pollFillingDataList);
    }

    /**
     * Генератор пользователей с ответами
     *
     * @param count кол-во пользователей
     * @param poll  определенный опрос
     * @return возвращение списка PollFillingData со всей информацией о выборах ответов
     */
    private static List<PollFillingData> generatePollFillingDataList(int count, Poll poll) {
        List<PollFillingData> pollFillingDataList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String user = "user-" + (i + 1);
            List<PollQuestionResponse> pollQuestionResponses = new ArrayList<>();

            for (PollQuestion pollQuestion : poll.getPollQuestionList()) {
                PollQuestionResponse randomPQR = new PollQuestionResponse(pollQuestion,
                        generateRandomAnswersList(pollQuestion));
                pollQuestionResponses.add(randomPQR);
            }
            pollFillingDataList.add(new PollFillingData(user, pollQuestionResponses));
        }

        return pollFillingDataList;
    }

    /**
     * Генератор ответов для конкретного пользователя
     *
     * @param pollQuestion вопрос с вариантами ответов
     * @return список ответов пользователей
     */
    private static List<String> generateRandomAnswersList(PollQuestion pollQuestion) {
        List<String> answersList = new ArrayList<>(pollQuestion.getAnswers());
        List<String> chosenAnswers = new ArrayList<>();
        final int CHANCE_BREAK = 30;

        for (int i = pollQuestion.getMaxAnswers(); i >= pollQuestion.getMinAnswers() && i > 0; i--) {
            chosenAnswers.add(answersList.remove(ThreadLocalRandom.current().nextInt(answersList.size())));

            //С шансом примерно 30% прервется цикл, если имеется хотя бы еще один выбор из опроса
            if (((i - 1) >= pollQuestion.getMinAnswers()) &&
                    (ThreadLocalRandom.current().nextInt(1, 101) <= CHANCE_BREAK)) {
                break;
            }
        }

        return chosenAnswers;
    }

}

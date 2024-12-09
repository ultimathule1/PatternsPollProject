package dev.builders;

import dev.PollQuestion;

import java.util.ArrayList;
import java.util.List;

public class PollQuestionBuilder {
    private String title;
    private int minAnswers;
    private int maxAnswers;
    private final List<String> answers;
    private final PollBuilder parentBuilder;

    public PollQuestionBuilder(PollBuilder pollBuilder) {
        title = "Empty Question";
        parentBuilder = pollBuilder;
        answers = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getMinAnswers() {
        return minAnswers;
    }

    public int getMaxAnswers() {
        return maxAnswers;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public PollQuestionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PollQuestionBuilder withMinAnswers(int minAnswers) {
        this.minAnswers = minAnswers;
        return this;
    }

    public PollQuestionBuilder withMaxAnswers(int maxAnswers) {
        this.maxAnswers = maxAnswers;
        return this;
    }

    public PollQuestionBuilder withAnswerVariant(String answerVariant) {
        answers.add(answerVariant);
        return this;
    }

    public PollBuilder and() {
        parentBuilder.getPollQuestionList().add(new PollQuestion(this));
        return parentBuilder;
    }
}

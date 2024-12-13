package dev.builders;

import dev.poll.Poll;
import dev.poll.PollQuestion;

import java.util.ArrayList;
import java.util.List;

public class PollBuilder {
    private String pollName;
    private final List<PollQuestion> pollQuestionList;

    public PollBuilder() {
        pollName = "Empty Name";
        pollQuestionList = new ArrayList<>();
    }

    public String getPollName() {
        return pollName;
    }

    public List<PollQuestion> getPollQuestionList() {
        return pollQuestionList;
    }

    public PollBuilder withPollName(String pollName) {
        this.pollName = pollName;
        return this;
    }

    public PollQuestionBuilder pollQuestion(String title) {
        return new PollQuestionBuilder(this)
                .withTitle(title);
    }

    public PollQuestionBuilder oneVariantPollQuestion(String title) {
        return new PollQuestionBuilder(this)
                .withTitle(title)
                .withMinAnswers(1)
                .withMaxAnswers(1);
    }

    public PollBuilder yesNoPollQuestion(String title) {
        return new PollQuestionBuilder(this)
                .withTitle(title)
                .withMinAnswers(1)
                .withMaxAnswers(1)
                .withAnswerVariant("Yes")
                .withAnswerVariant("No")
                .and();
    }

    public PollQuestionBuilder optionalQuestion(String title) {
        return new PollQuestionBuilder(this)
                .withTitle(title)
                .withMinAnswers(0);
    }

    public Poll build() {
        return new Poll(this);
    }
}

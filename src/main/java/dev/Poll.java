package dev;

import dev.builders.PollBuilder;

import java.util.List;

public class Poll {
    private final String pollName;
    private final List<PollQuestion> pollQuestionList;

    public Poll(PollBuilder pollBuilder) {
        pollName = pollBuilder.getPollName();
        pollQuestionList = pollBuilder.getPollQuestionList();
    }

    public String getPollName() {
        return pollName;
    }

    public List<PollQuestion> getPollQuestionList() {
        return pollQuestionList;
    }

    public static PollBuilder builder() {
        return new PollBuilder();
    }
}

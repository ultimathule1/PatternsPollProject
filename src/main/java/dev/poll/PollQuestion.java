package dev.poll;

import dev.builders.PollQuestionBuilder;

import java.util.List;

/**
 *
 */
public class PollQuestion {
    private final String title;
    private final int minAnswers;
    private final int maxAnswers;
    private final List<String> answers;

    public PollQuestion(PollQuestionBuilder builder) {
        if (builder.getMinAnswers() < 0
                || builder.getMaxAnswers() <= 0
                || builder.getMinAnswers() > builder.getMaxAnswers()
                || builder.getMinAnswers() >= builder.getAnswers().size()) {
            throw new IllegalArgumentException("Invalid min or max answers");
        }
        title = builder.getTitle();
        minAnswers = builder.getMinAnswers();
        maxAnswers = builder.getMaxAnswers();
        answers = builder.getAnswers();
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

    @Override
    public String toString() {
        return "PollQuestion{" +
                "title='" + title + '\'' +
                ", minAnswers=" + minAnswers +
                ", maxAnswers=" + maxAnswers +
                ", answers=" + answers +
                '}';
    }
}

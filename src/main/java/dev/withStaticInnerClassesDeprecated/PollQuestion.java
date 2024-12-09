package dev.withStaticInnerClassesDeprecated;

import java.util.List;

@Deprecated
public class PollQuestion {
    private String title;
    private int minAnswers;
    private int maxAnswers;
    List<String> answers;

    public PollQuestion(PollQuestionBuilder builder) {
        this.title = builder.title;
        this.minAnswers = builder.minAnswers;
        this.maxAnswers = builder.maxAnswers;
        this.answers = builder.answers;
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

    public static class PollQuestionBuilder {
        Poll.PollBuilder parentBuilder;
        private String title;
        private int minAnswers;
        private int maxAnswers;
        private List<String> answers;

        public PollQuestionBuilder(Poll.PollBuilder parentBuilder) {
            this.parentBuilder = parentBuilder;
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

        public PollQuestionBuilder withAnswersVariantList(String answersVariant) {
            this.answers.add(answersVariant);
            return this;
        }

        public Poll.PollBuilder and() {
            parentBuilder.pollQuestionList.add(new PollQuestion(this));
            return parentBuilder;
        }
    }
}

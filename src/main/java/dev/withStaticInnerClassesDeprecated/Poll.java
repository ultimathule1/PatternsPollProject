package dev.withStaticInnerClassesDeprecated;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Poll {
    private String pollName;
    private List<PollQuestion> pollQuestionList;

    private Poll(PollBuilder builder) {
        this.pollName = builder.pollName;
        this.pollQuestionList = builder.pollQuestionList;
    }

    public static PollBuilder builder() {
        return new PollBuilder();
    }

    public String getPollName() {
        return pollName;
    }

    public List<PollQuestion> getPollQuestionList() {
        return pollQuestionList;
    }

    public static class PollBuilder {
        private String pollName;
        final List<PollQuestion> pollQuestionList = new ArrayList<>();

        public PollBuilder withPollName(String pollName) {
            this.pollName = pollName;
            return this;
        }

        public PollQuestion.PollQuestionBuilder pollQuestion(String title) {
            return new PollQuestion.PollQuestionBuilder(this)
                    .withTitle(title);
        }

        public PollQuestion.PollQuestionBuilder oneVariantPollQuestion(String title) {
            return new PollQuestion.PollQuestionBuilder(this)
                    .withMinAnswers(1)
                    .withMaxAnswers(1);
        }

        public PollQuestion yesNoPollQuestion(String title) {
            return null;
        }

        public Poll buildPoll() {
            return new Poll(this);
        }
    }
}

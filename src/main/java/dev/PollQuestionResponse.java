package dev;

import dev.withStaticInnerClassesDeprecated.PollQuestion;

import java.util.List;

public record PollQuestionResponse(
        PollQuestion pollQuestion,
        List<String> selectedVariants
) {
}

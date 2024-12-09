package dev;

import java.util.List;

public record PollFillingData(
        String username,
        List<PollQuestionResponse> responses
) {
}
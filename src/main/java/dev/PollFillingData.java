package dev;

import java.util.List;

/**
 * Результат заполнения пользователем опроса
 * @param username      имя пользователя
 * @param responses     список ответов, которые дал пользователь
 */
public record PollFillingData(
        String username,
        List<PollQuestionResponse> responses
) {
}
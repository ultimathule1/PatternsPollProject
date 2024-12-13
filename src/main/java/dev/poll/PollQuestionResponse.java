package dev.poll;

import java.util.List;

/**
 * ответ на определенный вопрос из опроса
 *
 * @param pollQuestion     вопрос на который давался ответ
 * @param selectedVariants выбранные варианты ответа
 */
public record PollQuestionResponse(
        PollQuestion pollQuestion,
        List<String> selectedVariants
) {
}

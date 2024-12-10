package dev.analyze;

import java.util.Map;

/**
 * Статистика по ответам на вопрос
 *
 * @param questionTitle             название вопроса
 * @param selectedVariantsCount     кол-во ответов за конкретный вариант вопроса
 *                                  ключ - вариант ответа, значение - кол-во ответов
 * @param userSelectedVariantsCount кол-во выбранных ответов конкретным пользователем
 *                                  ключ - id логин пользователя, значение - кол-во ответов
 */
public record QuestionStatistics(
        String questionTitle,
        Map<String, Integer> selectedVariantsCount,
        Map<String, Integer> userSelectedVariantsCount
) {
}

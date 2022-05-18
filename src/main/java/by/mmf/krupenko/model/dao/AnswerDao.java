package by.mmf.krupenko.model.dao;

import by.mmf.krupenko.entity.Answer;

import java.util.List;

public interface AnswerDao {
    /**
     * Create an answer.
     *
     * @param answer text of answer
     * @param questionId id of question
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void createAnswer(String answer, int questionId) throws DaoException;

    /**
     * Create an answer.
     *
     * @param questionId id of question
     * @return list of answers
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Answer> findAnswersByQuestionId(int questionId) throws DaoException;
}

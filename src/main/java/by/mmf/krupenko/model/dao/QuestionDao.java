package by.mmf.krupenko.model.dao;

import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.entity.Quiz;

import java.util.List;

/**
 * The interface for working with questions
 */
public interface QuestionDao {
    /**
     * Create a question.
     *
     * @param question question
     * @param quizId id of quiz
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void createQuestion(Question question, String quizId) throws DaoException;

    /**
     * Find a question id.
     *
     * @param questionName name of question
     * @param quizId id of quiz
     * @return id of question
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    int findQuestionId(String questionName, String quizId) throws DaoException;

    /**
     * Find a questions.
     *
     * @param quizId id of quiz
     * @return list of questions
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Question> findQuestionsByQuizId(String quizId) throws DaoException;

    /**
     * Find a question.
     *
     * @param questionId question id
     * @return question
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Question findQuestionByQuestionId(int questionId) throws DaoException;
}

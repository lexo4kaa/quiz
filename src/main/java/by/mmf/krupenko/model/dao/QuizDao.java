package by.mmf.krupenko.model.dao;

import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.service.ServiceException;

import java.util.List;

/**
 * The interface for working with quizzes
 */
public interface QuizDao {
    /**
     * Create a quiz.
     *
     * @param quizName name of quiz
     * @param teacherEmail teacher email
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void createQuiz(String quizName, String teacherEmail) throws DaoException;

    /**
     * Remove quiz by id.
     *
     * @param quizId quiz id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void removeQuiz(String quizId) throws DaoException;

    /**
     * Find quizzes by teacherId.
     *
     * @param teacherEmail teacher email
     * @return list of quizzes
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    List<Quiz> findQuizzesByTeacherEmail(String teacherEmail) throws DaoException;

    /**
     * Find a quiz.
     *
     * @param quizName name of quiz
     * @param teacherEmail teacher email
     * @return quiz
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Quiz findQuizByQuizNameAndTeacherEmail(String quizName, String teacherEmail) throws DaoException;

    /**
     * Find a quiz.
     *
     * @param quizId quiz id
     * @return quiz
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Quiz findQuizByQuizId(String quizId) throws DaoException;

    /**
     * Change count of answers.
     *
     * @param quizId quiz id
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void changeCountOfAnswers(String quizId, int value) throws DaoException;
}

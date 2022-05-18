package by.mmf.krupenko.model.dao;

import by.mmf.krupenko.entity.Quiz;

import java.util.List;

/**
 * The interface for working with users
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
}

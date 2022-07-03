package by.mmf.krupenko.model.service;

import by.mmf.krupenko.entity.Answer;
import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.view.QuizView;

import java.util.List;
import java.util.Map;

/**
 * The interface for working with quizzes
 */
public interface QuizService {
    /**
     * Find quiz by id.
     *
     * @param quizId quiz id
     * @return quiz
     * @throws ServiceException if DaoException occur
     */
    Quiz findQuizById(String quizId) throws ServiceException;

    /**
     * Remove quiz by id.
     *
     * @param quizId quiz id
     * @throws ServiceException if DaoException occur
     */
    void removeQuiz(String quizId) throws ServiceException;

    /**
     * Find quizzes by teacherId.
     *
     * @param teacherEmail teacher email
     * @return list of quizzes
     * @throws ServiceException if DaoException occur
     */
    List<QuizView> findQuizzesByTeacherEmail(String teacherEmail) throws ServiceException;

    /**
     * Create a quiz.
     *
     * @param quizName name of quiz
     * @param teacherEmail teacher email
     * @param values map where key is a question, values are list of string answers
     * @return quiz id
     * @throws ServiceException if DaoException occur
     */
    String createQuiz(String quizName, String teacherEmail, Map<Question, List<String>> values) throws ServiceException;

    /**
     * Find quiz information.
     *
     * @param quizId quiz id
     * @return map where key is a question, values are list of answers
     * @throws ServiceException if DaoException occur
     */
    Map<Question, List<Answer>> findQuizInformation(String quizId) throws ServiceException;
}

package by.mmf.krupenko.model.dao;

import java.util.Map;

/**
 * The interface for working with statistics
 */
public interface StatisticsDao {
    /**
     * Add answer.
     *
     * @param questionId id of question
     * @param answer answer
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    void save(int questionId, String answer) throws DaoException;

    /**
     * Show answers.
     *
     * @param questionId id of question
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Map<String, Integer> show(int questionId) throws DaoException;
}

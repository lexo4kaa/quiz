package by.mmf.krupenko.model.service;

import by.mmf.krupenko.entity.Answer;
import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.view.ResultsView;

import java.util.List;
import java.util.Map;

/**
 * The interface for working with statistics
 */
public interface StatisticsService {
    /**
     * Save answers.
     *
     * @param answers map where key is a question id, values are list of string answers
     * @throws ServiceException if DaoException occur
     */
    void save(Map<Integer, List<String>> answers) throws ServiceException;

    /**
     * Show results answers.
     *
     * @param quizId id of quiz
     * @return result view
     * @throws ServiceException if DaoException occur
     */
    ResultsView show(String quizId) throws ServiceException;
}

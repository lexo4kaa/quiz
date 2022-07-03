package by.mmf.krupenko.model.service.impl;

import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.QuestionDao;
import by.mmf.krupenko.model.dao.QuizDao;
import by.mmf.krupenko.model.dao.StatisticsDao;
import by.mmf.krupenko.model.dao.impl.QuestionDaoImpl;
import by.mmf.krupenko.model.dao.impl.QuizDaoImpl;
import by.mmf.krupenko.model.dao.impl.StatisticsDaoImpl;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.StatisticsService;
import by.mmf.krupenko.model.view.ResultsView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsServiceImpl implements StatisticsService {
    private static Logger logger = LogManager.getLogger();
    private final StatisticsDao statisticsDao;
    private final QuestionDao questionDao;
    private final QuizDao quizDao;

    public StatisticsServiceImpl() {
        statisticsDao = StatisticsDaoImpl.getInstance();
        questionDao = QuestionDaoImpl.getInstance();
        quizDao = QuizDaoImpl.getInstance();
    }

    @Override
    public void save(Map<Integer, List<String>> answers) throws ServiceException {
        try {
            boolean flag = true;
            for (Map.Entry<Integer, List<String>> entry : answers.entrySet())
                for (String answer : entry.getValue()) {
                    statisticsDao.save(entry.getKey(), answer);
                    if (flag) {
                        String quizId = questionDao.findQuestionByQuestionId(entry.getKey()).getQuizId();
                        int countOfAnswers = quizDao.findQuizByQuizId(quizId).getCountOfAnswers();
                        quizDao.changeCountOfAnswers(quizId, countOfAnswers + 1);
                        flag = false;
                    }
                }
        } catch (DaoException e) {
            logger.error("Saving answers is failed in StatisticsServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public ResultsView show(String quizId) throws ServiceException {
        ResultsView resultsView = new ResultsView();
        try {
            Map<Question, Map<String, Integer>> map = new HashMap<>();
            List<Question> questions = questionDao.findQuestionsByQuizId(quizId);
            for (Question question : questions) {
                Map<String, Integer> values = statisticsDao.show(question.getId());
                if (!values.isEmpty())
                    map.put(question, values);
            }
            resultsView.setResults(map);
            resultsView.setQuizTitle(quizDao.findQuizByQuizId(quizId).getName());
        } catch (DaoException e) {
            logger.error("Showing statistics is failed in StatisticsServiceImpl", e);
            throw new ServiceException(e);
        }
        return resultsView;
    }

}

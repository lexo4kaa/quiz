package by.mmf.krupenko.model.service.impl;

import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.QuestionDao;
import by.mmf.krupenko.model.dao.StatisticsDao;
import by.mmf.krupenko.model.dao.impl.QuestionDaoImpl;
import by.mmf.krupenko.model.dao.impl.StatisticsDaoImpl;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.StatisticsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsServiceImpl implements StatisticsService {
    private static Logger logger = LogManager.getLogger();
    private final StatisticsDao statisticsDao;
    private final QuestionDao questionDao;

    public StatisticsServiceImpl() {
        statisticsDao = StatisticsDaoImpl.getInstance();
        questionDao = QuestionDaoImpl.getInstance();
    }

    @Override
    public void save(Map<Integer, List<String>> answers) throws ServiceException {
        try {
            for (Map.Entry<Integer, List<String>> entry : answers.entrySet())
                for (String answer : entry.getValue())
                    statisticsDao.save(entry.getKey(), answer);
        } catch (DaoException e) {
            logger.error("Saving answers is failed in StatisticsServiceImpl", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Question, Map<String, Integer>> show(String quizId) throws ServiceException {
        Map<Question, Map<String, Integer>> map = new HashMap<>();
        try {
            List<Question> questions = questionDao.findQuestionsByQuizId(quizId);
            for (Question question : questions) {
                Map<String, Integer> values = statisticsDao.show(question.getId());
                if (!values.isEmpty())
                    map.put(question, values);
            }
        } catch (DaoException e) {
            logger.error("Showing statistics is failed in StatisticsServiceImpl", e);
            throw new ServiceException(e);
        }
        return map;
    }

}

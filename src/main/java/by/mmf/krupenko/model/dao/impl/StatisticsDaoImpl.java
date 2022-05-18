package by.mmf.krupenko.model.dao.impl;

import by.mmf.krupenko.entity.Statistics;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.StatisticsDao;
import by.mmf.krupenko.model.pool.ConnectionPoolException;
import by.mmf.krupenko.model.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static by.mmf.krupenko.model.dao.column.StatisticsColumn.*;

public class StatisticsDaoImpl implements StatisticsDao {
    private static final StatisticsDao instance = new StatisticsDaoImpl();
    private static final String SQL_INSERT_ANSWER = "INSERT INTO Statistics (Answer, QuestionId) VALUES (?,?)";
    private static final String SQL_FIND_ANSWERS_STATISTICS_BY_QUESTION_ID = "SELECT Answer, Count(*) as count " +
                                                                             "FROM Statistics WHERE QuestionId = ? " +
                                                                             "GROUP BY ANSWER ORDER BY count DESC";

    private StatisticsDaoImpl() {
    }

    public static StatisticsDao getInstance() {
        return instance;
    }

    @Override
    public void save(int questionId, String answer) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ANSWER)) {
            statement.setString(1, answer);
            statement.setInt(2, questionId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while saving answer (questionId:" + questionId + ",answer:" + answer + ")", e);
        }
    }

    @Override
    public Map<String, Integer> show(int questionId) throws DaoException {
        Map<String, Integer> map = new HashMap<>();
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ANSWERS_STATISTICS_BY_QUESTION_ID)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String answer = resultSet.getString(ANSWER);
                int count = resultSet.getInt("count");
                map.put(answer, count);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding answers by question id = " + questionId + " (table Statistics)", e);
        }
        return map;
    }

    private Statistics createStatisticsFromResultSet(ResultSet resultSet) throws SQLException {
        Statistics statistics = new Statistics();
        int id = resultSet.getInt(ID);
        String text = resultSet.getString(ANSWER);
        int questionId = resultSet.getInt(QUESTION_ID);
        statistics.setId(id);
        statistics.setAnswer(text);
        statistics.setQuestionId(questionId);
        return statistics;
    }
}

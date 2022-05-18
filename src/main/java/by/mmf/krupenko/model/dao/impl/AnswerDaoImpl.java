package by.mmf.krupenko.model.dao.impl;

import by.mmf.krupenko.entity.Answer;
import by.mmf.krupenko.model.dao.AnswerDao;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.pool.ConnectionPoolException;
import by.mmf.krupenko.model.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.mmf.krupenko.model.dao.column.AnswerColumn.*;

public class AnswerDaoImpl implements AnswerDao {
    private static final AnswerDao instance = new AnswerDaoImpl();
    private static final String SQL_CREATE_ANSWER = "INSERT INTO Answers (Answer, QuestionId) VALUES (?,?)";
    private static final String SQL_FIND_ANSWERS_BY_QUESTION_ID =   "SELECT Id,Answer,QuestionId FROM Answers " +
                                                                    "WHERE QuestionId = ?";

    private AnswerDaoImpl() {
    }

    public static AnswerDao getInstance() {
        return instance;
    }

    @Override
    public void createAnswer(String answer, int questionId) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ANSWER)) {
            statement.setString(1, answer);
            statement.setInt(2, questionId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while create answer", e);
        }
    }

    @Override
    public List<Answer> findAnswersByQuestionId(int questionId) throws DaoException {
        List<Answer> answers = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ANSWERS_BY_QUESTION_ID)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(createAnswerFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding answers by question id = " + questionId + " (table Answers)", e);
        }
        return answers;
    }

    private Answer createAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        int id = resultSet.getInt(ID);
        String text = resultSet.getString(ANSWER);
        int questionId = resultSet.getInt(QUESTION_ID);
        answer.setId(id);
        answer.setAnswer(text);
        answer.setQuestionId(questionId);
        return answer;
    }
}

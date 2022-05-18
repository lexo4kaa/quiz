package by.mmf.krupenko.model.dao.impl;

import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.entity.QuestionType;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.QuestionDao;
import by.mmf.krupenko.model.pool.ConnectionPoolException;
import by.mmf.krupenko.model.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.mmf.krupenko.model.dao.column.QuestionColumn.*;

public class QuestionDaoImpl implements QuestionDao {
    private static final QuestionDao instance = new QuestionDaoImpl();
    private static final String SQL_FIND_QUESTIONS_BY_QUIZ_ID = "SELECT Id,QuizId,Title,IsRequired,QuestionType " +
                                                                "FROM Questions WHERE QuizId = ?";
    private static final String SQL_FIND_QUESTIONS_BY_QUIZ_ID_AND_TITLE = "SELECT Id,QuizId,Title,IsRequired,QuestionType " +
                                                                          "FROM Questions WHERE QuizId = ? AND Title = ?";
    private static final String SQL_CREATE_QUESTION = "INSERT INTO Questions (QuizId, Title, QuestionType) VALUES (?,?,?)";

    private QuestionDaoImpl() {
    }

    public static QuestionDao getInstance() {
        return instance;
    }

    @Override
    public void createQuestion(Question question, String quizId) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_QUESTION)) {
            statement.setString(1, quizId);
            statement.setString(2, question.getTitle());
            statement.setString(3, question.getQuestionType().getValue());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while create question", e);
        }
    }

    @Override
    public int findQuestionId(String questionName, String quizId) throws DaoException {
        int questionId = -1;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_QUESTIONS_BY_QUIZ_ID_AND_TITLE)) {
            statement.setString(1, quizId);
            statement.setString(2, questionName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionId = createQuestionFromResultSet(resultSet).getId();
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding question id", e);
        }
        return questionId;
    }

    @Override
    public List<Question> findQuestionsByQuizId(String quizId) throws DaoException {
        List<Question> questions = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_QUESTIONS_BY_QUIZ_ID)) {
            statement.setString(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                questions.add(createQuestionFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding questions by quiz id = " + quizId, e);
        }
        return questions;
    }

    private Question createQuestionFromResultSet(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        int id = resultSet.getInt(ID);
        String quizId = resultSet.getString(QUIZ_ID);
        String title = resultSet.getString(TITLE);
        boolean isRequired = resultSet.getBoolean(IS_REQUIRED);
        QuestionType questionType = QuestionType.getQuestionType(resultSet.getString(QUESTION_TYPE)).get();
        question.setId(id);
        question.setQuizId(quizId);
        question.setTitle(title);
        question.setRequired(isRequired);
        question.setQuestionType(questionType);
        return question;
    }
}

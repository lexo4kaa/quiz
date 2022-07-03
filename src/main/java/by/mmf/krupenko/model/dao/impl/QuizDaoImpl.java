package by.mmf.krupenko.model.dao.impl;

import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.QuizDao;
import by.mmf.krupenko.model.pool.ConnectionPoolException;
import by.mmf.krupenko.model.pool.CustomConnectionPool;
import by.mmf.krupenko.resource.CommonConsts;
import by.mmf.krupenko.resource.EncryptorAlgorithm;
import by.mmf.krupenko.util.Encryptor;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static by.mmf.krupenko.model.dao.column.QuizColumn.*;

public class QuizDaoImpl implements QuizDao {
    private static final QuizDao instance = new QuizDaoImpl();
    private static final String SQL_FIND_QUIZZES_BY_TEACHER_EMAIL = "SELECT Id,Name,TeacherEmail,CreationDate,Link,CountOfAnswers " +
                                                                    "FROM Quizzes WHERE TeacherEmail = ?";
    private static final String SQL_FIND_QUIZ_BY_NAME_AND_TEACHER_EMAIL =  "SELECT Id,Name,TeacherEmail,CreationDate,Link,CountOfAnswers " +
                                                    "FROM Quizzes WHERE Name = ? AND TeacherEmail = ?";
    private static final String SQL_FIND_QUIZ_BY_ID =  "SELECT Id,Name,TeacherEmail,CreationDate,Link,CountOfAnswers " +
                                                    "FROM Quizzes WHERE Id = ?";
    private static final String SQL_CREATE_QUIZ = "INSERT INTO Quizzes (Id,Name,TeacherEmail,CreationDate,Link,CountOfAnswers) " +
                                                  "VALUES (?,?,?,?,?,?)";
    private static final String SQL_REMOVE_QUIZ = "DELETE FROM Quizzes WHERE Id = ?";
    private static final String SQL_INCREMENT_COUNT_OF_ANSWERS = "UPDATE Quizzes SET CountOfAnswers = ? WHERE Id = ?";

    private QuizDaoImpl() {
    }

    public static QuizDao getInstance() {
        return instance;
    }

    @Override
    public void createQuiz(String quizName, String teacherEmail) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_QUIZ)) {
            Random rand = new Random();
            String id = Encryptor.encrypt(String.valueOf(rand.nextFloat()), EncryptorAlgorithm.ID);
            statement.setString(1, id);
            statement.setString(2, quizName);
            statement.setString(3, teacherEmail);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setString(5, CommonConsts.QUIZ_PREFIX + id);
            statement.setInt(6, 0);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQL Exception in QuizDaoImpl! ErrorCode:" + e.getErrorCode() +
                                                                ";\nSQLState:" + e.getSQLState(), e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("ConnectionPoolException in QuizDaoImpl", e);
        }
    }

    @Override
    public void removeQuiz(String quizId) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REMOVE_QUIZ)) {
            statement.setString(1, quizId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while removing quiz", e);
        }
    }

    @Override
    public List<Quiz> findQuizzesByTeacherEmail(String teacherEmail) throws DaoException {
        List<Quiz> quizzes = new ArrayList<>();
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_QUIZZES_BY_TEACHER_EMAIL)) {
            statement.setString(1, teacherEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                quizzes.add(createQuizFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding quizzes", e);
        }
        return quizzes;
    }

    @Override
    public Quiz findQuizByQuizNameAndTeacherEmail(String quizName, String teacherEmail) throws DaoException {
        Quiz quiz = new Quiz();
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_QUIZ_BY_NAME_AND_TEACHER_EMAIL)) {
            statement.setString(1, quizName);
            statement.setString(2, teacherEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                quiz = createQuizFromResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding quiz", e);
        }
        return quiz;
    }

    @Override
    public Quiz findQuizByQuizId(String quizId) throws DaoException {
        Quiz quiz = new Quiz();
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_QUIZ_BY_ID)) {
            statement.setString(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                quiz = createQuizFromResultSet(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding quiz", e);
        }
        return quiz;
    }

    @Override
    public void changeCountOfAnswers(String quizId, int value) throws DaoException {
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INCREMENT_COUNT_OF_ANSWERS)) {
            statement.setInt(1, value);
            statement.setString(2, quizId);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while incrementing count of answers", e);
        }
    }

    private Quiz createQuizFromResultSet(ResultSet resultSet) throws SQLException {
        Quiz quiz = new Quiz();
        String id = resultSet.getString(ID);
        String name = resultSet.getString(NAME);
        String teacherEmail = resultSet.getString(TEACHER_EMAIL);
        LocalDate creationDate = resultSet.getDate(CREATION_DATE).toLocalDate();
        String link = resultSet.getString(LINK);
        int countOfAnswers = resultSet.getInt(COUNT_OF_ANSWERS);
        quiz.setId(id);
        quiz.setName(name);
        quiz.setTeacherEmail(teacherEmail);
        quiz.setCreationDate(creationDate);
        quiz.setLink(link);
        quiz.setCountOfAnswers(countOfAnswers);
        return quiz;
    }
}

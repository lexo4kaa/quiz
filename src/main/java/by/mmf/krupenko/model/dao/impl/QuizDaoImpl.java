package by.mmf.krupenko.model.dao.impl;

import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.QuizDao;
import by.mmf.krupenko.model.pool.ConnectionPoolException;
import by.mmf.krupenko.model.pool.CustomConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.mmf.krupenko.model.dao.column.QuizColumn.*;

public class QuizDaoImpl implements QuizDao {
    private static final QuizDao instance = new QuizDaoImpl();
    private static final String SQL_FIND_QUIZZES_BY_TEACHER_EMAIL = "SELECT Id,Name,TeacherEmail,CreationDate,Link " +
                                                                    "FROM Quizzes WHERE TeacherEmail = ?";
    private static final String SQL_FIND_QUIZ_BY_NAME_AND_TEACHER_EMAIL =  "SELECT Id,Name,TeacherEmail,CreationDate,Link " +
                                                    "FROM Quizzes WHERE Name = ? AND TeacherEmail = ?";
    private static final String SQL_FIND_QUIZ_BY_ID =  "SELECT Id,Name,TeacherEmail,CreationDate,Link " +
                                                    "FROM Quizzes WHERE Id = ?";
    private static final String SQL_CREATE_QUIZ = "INSERT INTO Quizzes (Name, TeacherEmail) VALUES (?,?)";

    private QuizDaoImpl() {
    }

    public static QuizDao getInstance() {
        return instance;
    }

    @Override
    public void createQuiz(String quizName, String teacherEmail) throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_QUIZ)) {
            statement.setString(1, quizName);
            statement.setString(2, teacherEmail);
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while create quiz", e);
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

    private Quiz createQuizFromResultSet(ResultSet resultSet) throws SQLException {
        Quiz quiz = new Quiz();
        String id = resultSet.getString(ID);
        String name = resultSet.getString(NAME);
        String teacherEmail = resultSet.getString(TEACHER_EMAIL);
        LocalDate creationDate = resultSet.getDate(CREATION_DATE).toLocalDate();
        String link = resultSet.getString(LINK);
        quiz.setId(id);
        quiz.setName(name);
        quiz.setTeacherEmail(teacherEmail);
        quiz.setCreationDate(creationDate);
        quiz.setLink(link);
        return quiz;
    }
}

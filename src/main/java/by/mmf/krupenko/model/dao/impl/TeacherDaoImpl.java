package by.mmf.krupenko.model.dao.impl;

import by.mmf.krupenko.entity.Teacher;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.TeacherDao;
import by.mmf.krupenko.model.pool.ConnectionPoolException;
import by.mmf.krupenko.model.pool.CustomConnectionPool;
import by.mmf.krupenko.resource.EncryptorAlgorithm;
import by.mmf.krupenko.util.Encryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.mmf.krupenko.model.dao.column.TeacherColumn.*;

public class TeacherDaoImpl implements TeacherDao {
    private static final TeacherDao instance = new TeacherDaoImpl();

    private static final String SQL_FIND_TEACHER_BY_EMAIL = "SELECT Email, FirstName, LastName, Password " +
                                                            "FROM Teachers WHERE Email = ?";


    private static final String SQL_CREATE_TEACHER = "INSERT INTO Teachers (Email, FirstName, LastName, Password) " +
            "VALUES (?,?,?,?)";

    private TeacherDaoImpl() {
    }

    public static TeacherDao getInstance() {
        return instance;
    }

    @Override
    public Optional<Teacher> findTeacherByEmail(String email) throws DaoException {
        createTeacher();
        Optional<Teacher> teacher = Optional.empty();
        try(Connection connection = CustomConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_TEACHER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                teacher = Optional.of(createTeacherFromResultSet(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while finding user", e);
        }
        return teacher;
    }

    private void createTeacher() throws DaoException {
        try (Connection connection = CustomConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_TEACHER)) {
            statement.setString(1, "laputski@gmail.com");
            statement.setString(2, "Alexander");
            statement.setString(3, "Laputski");
            statement.setString(4, Encryptor.encrypt("laputski", EncryptorAlgorithm.PASSWORD));
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error while create question", e);
        }
    }

    private Teacher createTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        String email = resultSet.getString(EMAIL);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String password = resultSet.getString(PASSWORD);
        teacher.setEmail(email);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setPassword(password);
        return teacher;
    }
}

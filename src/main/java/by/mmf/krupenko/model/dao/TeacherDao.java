package by.mmf.krupenko.model.dao;

import by.mmf.krupenko.entity.Teacher;

import java.util.Optional;

/**
 * The interface for working with teachers
 */
public interface TeacherDao {
    /**
     * Find teacher by email (optional).
     *
     * @param email teacher email
     * @return optional teacher
     * @throws DaoException if SQLException or ConnectionPoolException occur
     */
    Optional<Teacher> findTeacherByEmail(String email) throws DaoException;

    void sqlCreateTrigger() throws DaoException;
}

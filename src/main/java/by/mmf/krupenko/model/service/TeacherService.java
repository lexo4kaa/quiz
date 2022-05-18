package by.mmf.krupenko.model.service;

import by.mmf.krupenko.entity.Teacher;

import java.util.Optional;

public interface TeacherService {
    /**
     * Authorize teacher.
     *
     * @param email     email
     * @param password  password
     * @return teacher is authorized or not
     * @throws ServiceException if DaoException occur
     */
    boolean authorizeTeacher(String email, String password) throws ServiceException;

    Optional<Teacher> findTeacherByEmail(String email) throws ServiceException;
}

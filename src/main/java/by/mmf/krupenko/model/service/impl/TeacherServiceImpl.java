package by.mmf.krupenko.model.service.impl;

import by.mmf.krupenko.entity.Teacher;
import by.mmf.krupenko.model.dao.DaoException;
import by.mmf.krupenko.model.dao.TeacherDao;
import by.mmf.krupenko.model.dao.impl.TeacherDaoImpl;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.TeacherService;
import by.mmf.krupenko.resource.EncryptorAlgorithm;
import by.mmf.krupenko.util.Encryptor;
import by.mmf.krupenko.util.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {
    private static Logger logger = LogManager.getLogger();
    private final TeacherDao teacherDao;

    public TeacherServiceImpl() {
        teacherDao = TeacherDaoImpl.getInstance();
    }

    @Override
    public boolean authorizeTeacher(String email, String password) throws ServiceException {
        String findPassword = "";
        String encPassword = Encryptor.encrypt(password, EncryptorAlgorithm.PASSWORD);
        if (UserValidator.isEmailCorrect(email) && UserValidator.isPasswordCorrect(password)) {
            try {
                Teacher teacher = teacherDao.findTeacherByEmail(email).orElseThrow(DaoException::new);
                findPassword = teacher.getPassword();
            } catch (DaoException e) {
                logger.error("teacherDao.findTeacherByEmail(" + email + ") is failed in TeacherServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return findPassword.equals(encPassword);
    }

    @Override
    public Optional<Teacher> findTeacherByEmail(String email) throws ServiceException {
        Optional<Teacher> teacher = Optional.empty();
        if (UserValidator.isEmailCorrect(email)) {
            try {
                teacher = teacherDao.findTeacherByEmail(email);
            } catch (DaoException e) {
                logger.error("userDao.findTeacherByEmail(" + email + ") is failed in TeacherServiceImpl", e);
                throw new ServiceException(e);
            }
        }
        return teacher;
    }
}

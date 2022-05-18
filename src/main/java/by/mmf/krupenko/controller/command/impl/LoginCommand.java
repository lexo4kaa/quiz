package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.entity.Teacher;
import by.mmf.krupenko.model.service.QuizService;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.TeacherService;
import by.mmf.krupenko.model.service.impl.QuizServiceImpl;
import by.mmf.krupenko.model.service.impl.TeacherServiceImpl;
import by.mmf.krupenko.resource.ConfigurationManager;
import by.mmf.krupenko.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;

/**
 * The command is responsible for login
 */
public class LoginCommand implements ActionCommand {
    private static final TeacherService teacherService = new TeacherServiceImpl();
    private static final QuizService quizService = new QuizServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page;
        String email = request.getParameter(EMAIL).toLowerCase();
        String password = request.getParameter(PASSWORD);
        try {
            if (teacherService.authorizeTeacher(email, password)) {
                session.setAttribute(CURRENT_TEACHER_EMAIL, email);
                session.setAttribute(QUIZZES, quizService.findQuizzesByTeacherEmail(email));
                page = ConfigurationManager.getProperty("path.page.quizzes");
            } else {
                session.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } catch(ServiceException e) {
            session.setAttribute(WRONG_ACTION_MESSAGE, MessageManager.getProperty("message.wrongaction"));
            page = ConfigurationManager.getProperty("path.page.index");
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}
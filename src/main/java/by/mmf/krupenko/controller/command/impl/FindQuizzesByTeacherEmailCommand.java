package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.entity.Quiz;
import by.mmf.krupenko.model.service.QuizService;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.impl.QuizServiceImpl;
import by.mmf.krupenko.model.view.QuizView;
import by.mmf.krupenko.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;


/**
 * The command is responsible for finding quizzes by teacherEmail
 */
public class FindQuizzesByTeacherEmailCommand implements ActionCommand {
    private static final QuizService quizService = new QuizServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String teacherEmail = request.getParameter(TEACHER_EMAIL);
        try {
            session.setAttribute(QUIZZES, quizService.findQuizzesByTeacherEmail(teacherEmail));
            page = ConfigurationManager.getProperty("path.page.quizzes");
        } catch (ServiceException e) {
            logger.error("Exception in 'FindQuizzesByTeacherEmailCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}


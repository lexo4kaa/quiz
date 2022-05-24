package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.model.service.QuizService;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.impl.QuizServiceImpl;
import by.mmf.krupenko.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;

public class RemoveQuizCommand implements ActionCommand {
    private static final QuizService quizService = new QuizServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String teacherEmail = (String) session.getAttribute(CURRENT_TEACHER_EMAIL);
        String quizId = request.getParameter(QUIZ_ID);
        try {
            quizService.removeQuiz(quizId);
            session.setAttribute(QUIZZES, quizService.findQuizzesByTeacherEmail(teacherEmail));
            page = ConfigurationManager.getProperty("path.page.quizzes");
        } catch (ServiceException e) {
            logger.error("Exception in 'ToQuizPageCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.FORWARD);
    }
}
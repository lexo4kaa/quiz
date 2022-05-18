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

/**
 * The command is responsible for forwarding to page 'quiz'
 */
public class ToQuizPageCommand implements ActionCommand {
    private static final QuizService quizService = new QuizServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        String quizId = request.getParameter(QUIZ_ID);
        HttpSession session = request.getSession();
        try {
            session.setAttribute(CURRENT_QUIZ, quizService.findQuizById(quizId));
            session.setAttribute(CURRENT_QUIZ_INFORMATION, quizService.findQuizInformation(quizId));
            page = ConfigurationManager.getProperty("path.page.quiz");
        } catch (ServiceException e) {
            logger.error("Exception in 'ToQuizPageCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }

        return new Router(page, Router.RouteType.FORWARD);
    }
}

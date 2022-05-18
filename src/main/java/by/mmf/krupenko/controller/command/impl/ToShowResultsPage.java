package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.model.dao.impl.StatisticsDaoImpl;
import by.mmf.krupenko.model.service.QuizService;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.StatisticsService;
import by.mmf.krupenko.model.service.impl.QuizServiceImpl;
import by.mmf.krupenko.model.service.impl.StatisticsServiceImpl;
import by.mmf.krupenko.resource.ConfigurationManager;
import by.mmf.krupenko.util.QuizParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;
import static by.mmf.krupenko.controller.command.ParameterAndAttribute.QUIZZES;

/**
 * The command is responsible for forwarding to page for students
 */
public class ToShowResultsPage implements ActionCommand {
    private static final StatisticsService statisticsService = new StatisticsServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String quizId = request.getParameter(QUIZ_ID);
        try {
            session.setAttribute(CURRENT_QUIZ_RESULTS, statisticsService.show(quizId));
            page = ConfigurationManager.getProperty("path.page.showResults");
        } catch (ServiceException e) {
            logger.error("Exception in 'ToShowResultsPage', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return new Router(page, Router.RouteType.FORWARD);
    }
}

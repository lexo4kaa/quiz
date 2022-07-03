package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
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

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;

public class SaveAnswersCommand implements ActionCommand {
    private static final StatisticsService statisticsService = new StatisticsServiceImpl();
    private static final QuizService quizService = new QuizServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String teacherEmail = (String) session.getAttribute(CURRENT_TEACHER_EMAIL);
        String input = new String(request.getParameter(SAVE_ANSWERS_AGENT).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        Map<Integer, List<String>> answers = QuizParser.parseAnswers(input);
        try {
            statisticsService.save(answers);
            session.setAttribute(QUIZZES, quizService.findQuizzesByTeacherEmail(teacherEmail));
            page = ConfigurationManager.getProperty("path.page.after_quiz");
        } catch (ServiceException e) {
            logger.error("Exception in 'CreateQuizCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }

        return new Router(page, Router.RouteType.REDIRECT);
    }
}

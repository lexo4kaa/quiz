package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.model.service.QuizService;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.model.service.impl.QuizServiceImpl;
import by.mmf.krupenko.resource.ConfigurationManager;
import by.mmf.krupenko.util.QuizParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;
import static by.mmf.krupenko.resource.CommonConsts.*;

public class CreateQuizCommand implements ActionCommand {
    private static final QuizService quizService = new QuizServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String teacherEmail = (String) session.getAttribute(CURRENT_TEACHER_EMAIL);
        String quizName = new String(request.getParameter(QUIZ_NAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String input = new String(request.getParameter(CREATE_QUIZ_AGENT).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        Map<Question, List<String>> values = QuizParser.parseCreatedQuiz(input);
        try {
            String quizId = quizService.createQuiz(quizName, teacherEmail, values);
            session.setAttribute(SHOWN_QUIZ_ID, QUIZ_PREFIX + quizId);
            session.setAttribute(QUIZZES, quizService.findQuizzesByTeacherEmail(teacherEmail));
            page = ConfigurationManager.getProperty("path.page.qrCodeGenerator");
        } catch (ServiceException e) {
            logger.error("Exception in 'CreateQuizCommand', redirected to error page");
            page = ConfigurationManager.getProperty("path.page.error");
        }

        return new Router(page, Router.RouteType.REDIRECT);
    }
}

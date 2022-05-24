package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.entity.Question;
import by.mmf.krupenko.model.service.ServiceException;
import by.mmf.krupenko.resource.ConfigurationManager;
import by.mmf.krupenko.util.QuizParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;
import static by.mmf.krupenko.resource.CommonConsts.QUIZ_PREFIX;

/**
 * The command is responsible for forwarding to page with QR-code
 */
public class ToQrCodePageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String quizId = request.getParameter(QUIZ_ID);
        session.setAttribute(SHOWN_QUIZ_ID, QUIZ_PREFIX + quizId);
        page = ConfigurationManager.getProperty("path.page.qrCodeGenerator");
        return new Router(page, Router.RouteType.REDIRECT);
    }
}

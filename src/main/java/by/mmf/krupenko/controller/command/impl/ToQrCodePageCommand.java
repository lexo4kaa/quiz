package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.resource.CommonConsts;
import by.mmf.krupenko.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.*;

/**
 * The command is responsible for forwarding to page with QR-code
 */
public class ToQrCodePageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();
        String quizId = request.getParameter(QUIZ_ID);
        session.setAttribute(SHOWN_QUIZ_ID, CommonConsts.QUIZ_PREFIX + quizId);
        page = ConfigurationManager.getProperty("path.page.qrCodeGenerator");
        return new Router(page, Router.RouteType.REDIRECT);
    }
}

package by.mmf.krupenko.controller;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.ActionFactory;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.resource.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.mmf.krupenko.controller.command.ParameterAndAttribute.CURRENT_PAGE;

/**
 * Controller receive request from client (get or post)
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = ActionFactory.defineCommand(request);
        Router router = command.execute(request);
        String page = router.getPagePath();
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_PAGE, page);
        switch (router.getRouteType()) {
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                String path = request.getContextPath() + page;
                response.sendRedirect(path);
                break;
            default:
                logger.error("incorrect route type " + page);
                path = request.getContextPath() + ConfigurationManager.getProperty("path.page.error");
                response.sendRedirect(path);
        }
    }
}
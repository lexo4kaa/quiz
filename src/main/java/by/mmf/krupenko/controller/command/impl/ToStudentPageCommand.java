package by.mmf.krupenko.controller.command.impl;

import by.mmf.krupenko.controller.command.ActionCommand;
import by.mmf.krupenko.controller.command.Router;
import by.mmf.krupenko.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The command is responsible for forwarding to page for students
 */
public class ToStudentPageCommand implements ActionCommand {
    @Override
    public Router execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.forStudent");
        return new Router(page, Router.RouteType.FORWARD);
    }
}

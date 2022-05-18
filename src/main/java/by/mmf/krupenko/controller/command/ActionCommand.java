package by.mmf.krupenko.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface for different commands.
 */
public interface ActionCommand {
    /**
     * Executes command.
     *
     * @param request request
     * @return Router
     */
    Router execute(HttpServletRequest request);
}

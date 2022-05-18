package by.mmf.krupenko.controller.command;

import by.mmf.krupenko.controller.command.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * The class-factory for command.
 */
public class ActionFactory {
    private static final String PARAM_NAME_COMMAND = "command";

    /**
     * Defines command.
     *
     * @param request request
     * @return ActionCommand
     */
    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current;
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return new EmptyCommand();
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new EmptyCommand();
        }
        return current;
    }
}
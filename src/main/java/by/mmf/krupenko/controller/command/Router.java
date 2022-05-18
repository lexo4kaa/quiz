package by.mmf.krupenko.controller.command;

/**
 * Describes the router
 */
public class Router {
    /**
     * Describes all route types
     */
    public enum RouteType {
        FORWARD, REDIRECT
    }

    private final String pagePath;
    private final RouteType routeType;

    public Router(String pagePath, RouteType routeType) {
        this.pagePath = pagePath;
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouteType getRouteType() {
        return routeType;
    }
}

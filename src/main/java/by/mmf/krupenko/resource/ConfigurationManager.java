package by.mmf.krupenko.resource;

import java.util.ResourceBundle;

/**
 * Configuration manager.
 */
public class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

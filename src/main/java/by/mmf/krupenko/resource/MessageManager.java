package by.mmf.krupenko.resource;

import java.util.ResourceBundle;

/**
 * Message manager.
 */
public class MessageManager {
    private static final String BUNDLE_NAME = "pagecontent";

    private MessageManager() {}
    public static String getProperty(String key) {
        ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_NAME);
        return rb.getString(key);
    }
}

package by.mmf.krupenko.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * ConnectionCreator is responsible for creating connections
 */
class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();
    private static final Properties properties = new Properties();
    private static final String FILE_NAME = "properties/database.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String DATABASE_URL;
    private static final String DATABASE_DRIVER;

    private ConnectionCreator(){}

    static {
        try {
            ClassLoader classLoader = ConnectionCreator.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
            DATABASE_URL = properties.getProperty(PROPERTY_URL);
            properties.remove(PROPERTY_URL);
            DATABASE_DRIVER = properties.getProperty(PROPERTY_DRIVER);
            properties.remove(PROPERTY_DRIVER);
            Class.forName(DATABASE_DRIVER);
        } catch (FileNotFoundException e) {
            logger.fatal("FileNotFoundException in ConnectionCreator", e);
            throw new RuntimeException();
        } catch (IOException e) {
            logger.fatal("IOException in ConnectionCreator", e);
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            logger.fatal("ClassNotFoundException in ConnectionCreator", e);
            throw new RuntimeException();
        }
    }

    /**
     * Gets a connection to the database
     *
     * @return connection
     * @throws SQLException sql exception
     */
    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
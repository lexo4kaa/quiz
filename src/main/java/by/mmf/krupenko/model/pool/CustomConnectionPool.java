package by.mmf.krupenko.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CustomConnectionPool is responsible for connections used while the system is running
 */
public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 10;
    private static CustomConnectionPool instance;
    private static Lock lock = new ReentrantLock();

    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> givenAwayConnections;

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingQueue<>(DEFAULT_POOL_SIZE);
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = ConnectionCreator.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            logger.error("creating connection is failed", e);
        }

        if (freeConnections.size() == 0) {
            logger.fatal("connection poll don't created, pool size is 0");
            throw new RuntimeException("connection poll don't created, pool size is 0");
        }
    }

    /**
     * Gets instance.
     *
     * @return instance
     */
    public static CustomConnectionPool getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    logger.info("ConnectionPool was created");
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return connection
     * @throws ConnectionPoolException if InterruptedException occurs
     */
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error("error getting connection", e);
            throw new ConnectionPoolException("error getting connection", e);
        }
        return connection;
    }

    /**
     * Releases connection to connection pool
     *
     * @param connection connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenAwayConnections.remove(connection)) {
            freeConnections.offer(connection);
        } else {
            logger.error("connection does not belong to the pool");
        }
    }

    /**
     * Destroy connection pool.
     *
     * @throws ConnectionPoolException if InterruptedException of SQLException occurs
     */
    public void destroyPool() throws ConnectionPoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                ((ProxyConnection)freeConnections.take()).reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.error("error destroy pool", e);
                throw new ConnectionPoolException("destroyPool error", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() throws ConnectionPoolException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("deregisterDrivers error", e);
                throw new ConnectionPoolException("deregisterDrivers error", e);
            }
        }
    }
}

package com.quantitymeasurementapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class ConnectionPool {

    private static ConnectionPool instance;

    private Queue<Connection> availableConnections = new LinkedList<>();

    private final int MAX_POOL_SIZE = 10;

    private String url;
    private String username;
    private String password;

    private ConnectionPool() {

        url = ApplicationConfig.get("db.url");
        username = ApplicationConfig.get("db.username");
        password = ApplicationConfig.get("db.password");

        try {
            Class.forName(ApplicationConfig.get("db.driver"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized ConnectionPool getInstance() {

        if (instance == null)
            instance = new ConnectionPool();

        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {

        if (!availableConnections.isEmpty())
            return availableConnections.poll();

        return DriverManager.getConnection(url, username, password);
    }

    public synchronized void releaseConnection(Connection connection) {

        if (availableConnections.size() < MAX_POOL_SIZE)
            availableConnections.offer(connection);
    }
}
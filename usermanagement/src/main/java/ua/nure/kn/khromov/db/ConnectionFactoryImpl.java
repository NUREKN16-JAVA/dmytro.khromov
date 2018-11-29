package main.java.ua.nure.kn.khromov.db;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class ConnectionFactoryImpl implements ConnectionFactory {
    private String user;
    private String password;
    private String url;
    private String driver;

    public ConnectionFactoryImpl(String user, String password, String url, String driver) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.driver = driver;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Connection createConnection() throws DatabaseException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }

        try {
            return getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}

package ua.nure.kn.khromov.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
    /**
     * Returns created connection to database.
     *
     * @return created connection to database.
     * @throws ClassNotFoundException if class wasn't found by it's name.
     * @throws java.sql.SQLException if connection can't be created.
     * */
    Connection createConnection() throws DatabaseException;
}
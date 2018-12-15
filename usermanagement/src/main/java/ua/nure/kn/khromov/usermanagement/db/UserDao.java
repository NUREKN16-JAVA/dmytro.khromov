package ua.nure.kn.khromov.usermanagement.db;

import ua.nure.kn.khromov.usermanagement.User;

import java.util.Collection;

public interface UserDao {
    /**
     * Returns new user, created in database.
     *
     * @param user to create.
     * @return created user.
     * @throws DatabaseException if number of inserted rows is not 1.
     * @throws java.sql.SQLException if DatabaseException caused.
     * */
    User create(User user) throws DatabaseException;

    /**
     * Updates row where user id equals to user id in param.
     *
     * @param user to update.
     * @throws DatabaseException if number of rows to update is not 1.
     * @throws java.sql.SQLException if DatabaseException caused.
     * */
    void update(User user) throws DatabaseException;

    /**
     * Deletes row where user id equals to user id in param.
     *
     * @param user to delete.
     * @throws DatabaseException if number of rows to delete is not 1.
     * @throws java.sql.SQLException if DatabaseException caused.
     * */
    void delete(User user) throws DatabaseException;

    /**
     * Finds user by id.
     *
     * @param id of user to find.
     * @throws DatabaseException if there is no user with this id.
     * @throws java.sql.SQLException if DatabaseException caused.
     * */
    User find(Long id) throws DatabaseException;

    /**
     * Finds all users in database.
     *
     * @return collection of users in the database.
     * @throws DatabaseException
     * @throws java.sql.SQLException
     * */
    Collection findAll() throws DatabaseException;

    /**
     * Sets connection to database.
     *
     * @param connectionFactory of connection to database.
     * */
    void setConnectionFactory(ConnectionFactory connectionFactory);
}
package ua.nure.kn.khromov.usermanagement.db;

import ua.nure.kn.khromov.usermanagement.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

class HsqldbUserDao implements UserDao {

    private ConnectionFactory connectionFactory;

    private final static String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    private final static String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
    private final static String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private final static String SELECT_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private final static String SELECT_BY_NAMES = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname=? AND lastname=?";
    private final static String CALL_IDENTITY = "call IDENTITY()";

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * {@inheritDoc}
     * */
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqldbUserDao() {
    }

    public HsqldbUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public User create(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            int numberOfStrings = statement.executeUpdate();

            if (numberOfStrings != 1) {
                throw new DatabaseException("Number of inserted rows: " + numberOfStrings);
            }

            CallableStatement callableStatement = connection.prepareCall(CALL_IDENTITY);
            ResultSet keys = callableStatement.executeQuery();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            }

            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();

            return user;
        } catch (DatabaseException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void update(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.setLong(4, user.getId());

            int updatedRows = statement.executeUpdate();
            if (updatedRows != 1) {
                throw new DatabaseException("Update operation caused exception. Effected rows = " + updatedRows);
            }

            statement.close();
            connection.close();
        } catch (DatabaseException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void delete(User user) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

            statement.setLong(1, user.getId());
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1) {
                throw new DatabaseException("Delete operation failed. Effected rows = " + deletedRows);
            }

            statement.close();
            connection.close();
        } catch (DatabaseException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public User find(Long id) throws DatabaseException {
        User user = null;

        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setLastName(resultSet.getString(3));
                    user.setDateOfBirth(resultSet.getDate(4));
                }
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (DatabaseException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
        return user;
    }

    @Override
    public Collection find(String firstName, String lastName) throws DatabaseException {
        Collection result = new LinkedList();

        try{
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAMES);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (DatabaseException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Collection findAll() throws DatabaseException {
        Collection result = new LinkedList();

        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (DatabaseException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw new DatabaseException(exception.getMessage());
        }
        return result;
    }
}
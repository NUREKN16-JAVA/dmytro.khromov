package ua.nure.kn.khromov.usermanagement.db;

import ua.nure.kn.khromov.usermanagement.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDao implements UserDao {
    long id = 0;
    Map users = new HashMap();

    /**
     * Creates user in DB.
     *
     * @param user to create.
     * @return user.
     * @throws DatabaseException
     * */
    @Override
    public User create(User user) throws DatabaseException {
        Long currentId = new Long(++id);
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    /**
     * Updates user in DB.
     *
     * @param user to update.
     * @throws DatabaseException
     * */
    @Override
    public void update(User user) throws DatabaseException {
        users.remove(user.getId());
        users.put(user.getId(), user);
    }

    /**
     * Deletes user from DB.
     *
     * @param user to delete.
     * @throws DatabaseException
     * */
    @Override
    public void delete(User user) throws DatabaseException {
        users.remove(user.getId());
    }

    /**
     * Finds user in DB.
     *
     * @param id of user to find.
     * @return found user.
     * @throws DatabaseException
     * */
    @Override
    public User find(Long id) throws DatabaseException {
        return (User) users.get(id);
    }

    /**
     * Finds all users in DB.
     *
     * @return all users.
     * @throws DatabaseException
     * */
    @Override
    public Collection findAll() throws DatabaseException {
        return users.values();
    }

    /**
     * Sets connection factory.
     * */
    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }
}

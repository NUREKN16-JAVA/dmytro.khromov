package ua.nure.kn.khromov.usermanagement.db;

import static junit.framework.Assert.fail;

public class DaoFactoryImpl extends DaoFactory {
    @Override
    public UserDao getUserDao() {
        UserDao result = null;

        try {
            Class userDaoClass = Class.forName(properties.getProperty(USER_DAO));
            result = (UserDao) userDaoClass.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        } catch (InstantiationException e) {
            fail(e.getMessage());
        }

        return result;
    }
}

package ua.nure.kn.khromov.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {
    @Override
    public UserDao getUserDao() {
        UserDao result = null;

        try {
            Class userDaoClass = Class.forName(properties.getProperty(USER_DAO));
            result = (UserDao) userDaoClass.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return result;
    }
}

package main.java.ua.nure.kn.khromov.db;

import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.fail;

public class DaoFactory {
    private final Properties properties = new Properties();
    private final static DaoFactory INSTANCE = new DaoFactory();

    private final static String USER_DAO = "dao.ua.nure.kn.khromov.db.UserDao";
    private final static String USER = "connection.user";
    private final static String PASSWORD = "connection.password";
    private final static String URL = "connection.url";
    private final static String DRIVER = "connection.driver";

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    @SuppressWarnings("deprecation")
	public DaoFactory() {
        try {
            this.properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Returns connection to database.
     *
     * @return connection to database.
     * */
    private ConnectionFactory getConnectionFactory() {
        String user = properties.getProperty(USER);
        String password = properties.getProperty(PASSWORD);
        String url = properties.getProperty(URL);
        String driver = properties.getProperty(DRIVER);

        return new ConnectionFactoryImpl(user, password, url, driver);
    }

    /**
     * Returns UserDao that connected to database.
     *
     * @return UserDao that connected to database.
     * @throws ClassNotFoundException if class UserDao wasn't found.
     * @throws IllegalAccessException if user doesn't have access to it.
     * @throws InstantiationException if specified class is an interface or is an abstract class.
     * */
    @SuppressWarnings("deprecation")
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
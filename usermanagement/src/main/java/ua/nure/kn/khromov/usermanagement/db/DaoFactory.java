package ua.nure.kn.khromov.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
    protected static Properties properties;
    private static final String DAO_FACTORY = "dao.factory";
    private static DaoFactory instance;

    protected final static String USER_DAO = "dao.ua.nure.kn.khromov.usermanagement.db.UserDao";
    private final static String USER = "connection.user";
    private final static String PASSWORD = "connection.password";
    private final static String URL = "connection.url";
    private final static String DRIVER = "connection.driver";

    static {
        properties = new Properties();
        try {
            properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized DaoFactory getInstance() {
        if(instance==null){
            try{
                Class factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
                instance = (DaoFactory)factoryClass.newInstance();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    protected DaoFactory() {
    }

    /**
     * Returns connection to database.
     *
     * @return connection to database.
     * */
    protected ConnectionFactory getConnectionFactory() {
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
    public abstract UserDao getUserDao();

    public static void init(Properties prop){
        properties = prop;
        instance = null;
    }
}
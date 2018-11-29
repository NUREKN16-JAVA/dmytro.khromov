package test.java.ua.nure.kn.khromov.db;

import junit.framework.TestCase;
import main.java.ua.nure.kn.khromov.db.DaoFactory;
import main.java.ua.nure.kn.khromov.db.UserDao;

public class DaoFactoryTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Tests UserDao not to be null.
     *
     * Expected result: daoFactory is not null;
     *                  userDao is not null.
     * */
    public void testGetUserDao() {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();

            assertNotNull("DaoFactory instance is null", daoFactory);

            UserDao userDao = daoFactory.getUserDao();

            assertNotNull("UserDao instance is null", userDao);
        }catch (RuntimeException e){
            fail(e.getMessage());
        }
    }
}
package ua.nure.kn.khromov.usermanagement.db;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import ua.nure.kn.khromov.usermanagement.User;

import java.util.Calendar;
import java.util.Collection;

public class HsqldbUserDaoTest extends DatabaseTestCase {

    private HsqldbUserDao dao;
    private ConnectionFactory connectionFactory;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl("sa", "", "jdbc:hsqldb:file:db/usermanagement", "org.hsqldb.jdbcDriver");

        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("userdataset.xml"));

        return dataSet;
    }

    public void setUp() throws Exception {
        super.setUp();
        dao = new HsqldbUserDao(connectionFactory);
    }

    /**
     * Tests user to be created.
     *
     * Expected result: user id is null before creation;
     *                  user is not null after creation;
     *                  user id is not null after creation.
     * */
    public void testCreate() {
        try {
            User user = new User();
            user.setFirstName("Dmitriy");
            user.setLastName("Khromov");
            user.setDateOfBirth(Calendar.getInstance().getTime());

            assertNull(user.getId());

            user = dao.create(user);

            assertNotNull(user);
            assertNotNull(user.getId());
        } catch (DatabaseException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Tests user collection to find.
     *
     * Expected result: user collection is not null after finding all;
     *                  size of user collection is 2.
     * */
    public void testFindAll() {
        try {
            Collection userCollection = dao.findAll();
            assertNotNull("Collection is null", userCollection);
            assertEquals("Collection size", 2, userCollection.size());
        } catch (DatabaseException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Tests user to find.
     *
     * Expected result: user is not null if it exists in database;
     *                  user is null if it isn't exist in database.
     * */
    public void testFind() {
        try {
            User user = dao.find((long) 1000);
            assertNotNull("No user with this id", user);

            user = dao.find((long) 1);
            assertNull("User with id 1 exists", user);
        } catch (DatabaseException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Tests user to update in database.
     *
     * Expected result: first name equals to "Ivan" after updating;
     *                  last name equals to "Ivanov" after updating.
     * */
    public void testUpdate() {
        try {
            User user = new User();
            user.setId((long) 1000);
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setDateOfBirth(Calendar.getInstance().getTime());

            dao.update(user);

            User updatedUser = dao.find((long) 1000);

            assertEquals("Ivan", updatedUser.getFirstName());
            assertEquals("Ivanov", updatedUser.getLastName());
        } catch (DatabaseException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Tests user to delete.
     *
     * Expected result: user collection size equals to 2 before deleting;
     *                  deletedUser is null after deleting;
     *                  user collection size equals to 1 after deleting.
     * */
    public void testDelete() {
        try {
            User userToDelete = dao.find((long)1001);
            Collection userCollection = dao.findAll();
            assertEquals(2, userCollection.size());

            dao.delete(userToDelete);

            User deletedUser = dao.find((long)1001);
            userCollection = dao.findAll();
            assertNull(deletedUser);
            assertEquals(1, userCollection.size());
        } catch (DatabaseException exception) {
            fail(exception.getMessage());
        }
    }
}
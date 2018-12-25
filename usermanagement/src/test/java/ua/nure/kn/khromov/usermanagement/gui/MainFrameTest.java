package ua.nure.kn.khromov.usermanagement.gui;

import com.mockobjects.dynamic.Mock;
import javafx.scene.input.DataFormat;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import junit.framework.TestCase;
import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DaoFactory;
import ua.nure.kn.khromov.usermanagement.db.DaoFactoryImpl;
import ua.nure.kn.khromov.usermanagement.db.MockDaoFactory;
import ua.nure.kn.khromov.usermanagement.db.MockUserDao;
import ua.nure.kn.khromov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class MainFrameTest extends JFCTestCase {

    private static final String FIRST_NAME = "Dmitriy";
    private static final String LAST_NAME = "Khromov";
    private static final Date NOW = new Date();
    private MainFrame mainFrame;
    private Mock mockUserDao;

    public void setUp() throws Exception {
        super.setUp();
        try{
            Properties properties = new Properties();
            properties.setProperty("dao.factory", MockDaoFactory.class.getName());
            DaoFactory.init(properties);
            mockUserDao = ((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao();
            mockUserDao.expectAndReturn("findAll", new ArrayList());

            setHelper(new JFCTestHelper());
            mainFrame = new MainFrame();
        }catch (Exception e){
            fail(e.getMessage());
        }
        mainFrame.setVisible(true);
    }

    public void tearDown() throws Exception {
        try {
            mockUserDao.verify();
            mainFrame.setVisible(false);
            getHelper().cleanUp(this);
            super.tearDown();
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    /**
     * Finds component on frame.
     *
     * @param componentClass on frame.
     * @param name of component on frame.
     * @return found component.
     * */
    private Component find(Class componentClass, String name) {
        NamedComponentFinder finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component '" + name + "'", component);

        return component;
    }

    /**
     * Tests browse controls.
     *
     * Expected results: 3 columns in userTable.
     *                   "ID" column on the first place.
     *                   "First Name" column on the second place.
     *                   "Last Name" column on the third place.
     * */
    public void testBrowseControls() {
        find(JPanel.class, "browsePanel");

        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(3, table.getColumnCount());
        assertEquals("ID", table.getColumnName(0));
        assertEquals(Messages.getString("first_name"), table.getColumnName(1));
        assertEquals(Messages.getString("last_name"), table.getColumnName(2));

        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
    }

    /**
     * Tests add user view.
     *
     * Expected results: user with ID = 1, FIRST_NAME = "Dmitriy", LAST_NAME = "Khromov", DateOfBirth = now tobe created.
     *                   method "findAll" called after adding to fill table.
     *                   no rows in table before clicking "add" button.
     *                   "addPanel" was found.
     *                   "firstNameField" was found.
     *                   "lastNameField" was found.
     *                   "dateOfBirthField" was found.
     *                   "okButton" was found.
     *                   "cancelButton" was found.
     *                   "browsePanel" was found after clicking "Add" button.
     *                   "userTable" was found.
     *                   1 row in table after adding user.
     * */
    public void testAddUser() {
        User user = new User(FIRST_NAME, LAST_NAME, NOW);
        User expectedUser = new User((long) 1, FIRST_NAME, LAST_NAME, NOW);
        mockUserDao.expectAndReturn("create", user, expectedUser);

        ArrayList users = new ArrayList();
        users.add(user);
        mockUserDao.expectAndReturn("findAll", users);

        JTable userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(0, userTable.getRowCount());

        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
        find(JPanel.class, "addPanel");

        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        getHelper().sendString(new StringEventData(this, firstNameField, FIRST_NAME));
        getHelper().sendString(new StringEventData(this, lastNameField, LAST_NAME));

        DateFormat dateFormat = DateFormat.getDateInstance();
        String date = dateFormat.format(NOW);
        getHelper().sendString(new StringEventData(this, dateOfBirthField, date));

        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        userTable = (JTable) find(JTable.class, "userTable");
        assertEquals(1, userTable.getRowCount());
    }
}
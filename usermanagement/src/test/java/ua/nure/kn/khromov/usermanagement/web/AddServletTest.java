package ua.nure.kn.khromov.usermanagement.web;

import ua.nure.kn.khromov.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {

    private static final String OK_BUTTON = "okButton";

    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd(){
        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setDateOfBirth(new Date());
        User user = createUserForTest();
        getMockUserDao().expectAndReturn("create", newUser, user);
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(new Date()));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
    }

    public void testAddEmptyFirstName(){
        addRequestParameter("firstName", "");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(new Date()));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }

    public void testAddEmptyLastName(){
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "");
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(new Date()));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }

    public void testAddEmptyDate(){
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", "");
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }

    public void testAddInvalidDate(){
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", "invalidDate");
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }
}
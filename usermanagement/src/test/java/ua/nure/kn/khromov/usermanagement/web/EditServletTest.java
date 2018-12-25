package ua.nure.kn.khromov.usermanagement.web;

import junit.framework.TestCase;
import ua.nure.kn.khromov.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class EditServletTest extends MockServletTestCase {

    private static final String OK_BUTTON = "okButton";

    public void setUp() throws Exception {
        super.setUp();
        createServlet(EditServlet.class);
    }

    public void testEdit(){
        User user = createUserForTest();
        getMockUserDao().expect("update", user);
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(new Date()));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
    }

    public void testEditEmptyFirstName(){
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(new Date()));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }

    public void testEditEmptyLastName(){
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "");
        addRequestParameter("dateOfBirth", DateFormat.getDateInstance().format(new Date()));
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }

    public void testEditEmptyDate(){
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", "");
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }
    public void testEditInvalidDate(){
        addRequestParameter("id", "1000");
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("dateOfBirth", "invalidDate");
        addRequestParameter(OK_BUTTON, "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Couldn't find error message in session", errorMessage);
    }
}
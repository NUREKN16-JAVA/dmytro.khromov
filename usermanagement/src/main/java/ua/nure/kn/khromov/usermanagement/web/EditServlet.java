package ua.nure.kn.khromov.usermanagement.web;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DaoFactory;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

public class EditServlet extends HttpServlet {
    private static final String OK_BUTTON = "okButton";
    private static final String CANCEL_BUTTON = "cancelButton";
    private static final String BROWSE_SERVLET = "/browse";
    private static final String EDIT_JSP = "/edit.jsp";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String DATE_OF_BIRTH = "dateOfBirth";

    /**
     * If "Ok" button was clicked, edits user.
     * If "Cancel" button was clicked, returns to "/browse" servlet page.
     * Else shows "/edit" servlet page.
     * */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(OK_BUTTON) != null) {
            doOk(req, resp);
        } else if (req.getParameter(CANCEL_BUTTON) != null) {
            doCancel(req, resp);
        } else {
            showPage(req, resp);
        }
    }

    /**
     * Edits user and goes to "/browse" servlet page.
     * */
    private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        try {
            user = getUser(req);
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            showPage(req, resp);
            return;
        }
        try {
            processUser(user);
        } catch (DatabaseException e) {
            try {
                throw new ServletException(e);
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }
        req.getRequestDispatcher(BROWSE_SERVLET).forward(req, resp);
    }

    /**
     * Doesn't save user changes and goes to "/browse" servlet page.
     * */
    private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(BROWSE_SERVLET).forward(req, resp);
    }

    /**
     * Shows "/edit.jsp" page.
     * */
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(EDIT_JSP).forward(req, resp);
    }

    /**
     * Gets user information and checks it.
     * */
    private User getUser(HttpServletRequest req) throws ValidationException {
        User user = new User();
        String id = req.getParameter(ID);
        String firstName = req.getParameter(FIRST_NAME);
        String lastName = req.getParameter(LAST_NAME);
        String dateOfBirth = req.getParameter(DATE_OF_BIRTH);

        if (firstName.isEmpty()) {
            throw new ValidationException("First Name is empty");
        }

        if (lastName.isEmpty()) {
            throw new ValidationException("Last Name is empty");
        }

        if (dateOfBirth.isEmpty()) {
            throw new ValidationException("Date of Birth is empty");
        }

        if (id != null) {
            user.setId(new Long(id));
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        try {
            user.setDateOfBirth(DateFormat.getDateInstance().parse(dateOfBirth));
        } catch (ParseException e) {
            throw new ValidationException("Date format is invalid.");
        }
        return user;
    }

    /**
     * Updates user.
     * */
    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().update(user);
    }
}

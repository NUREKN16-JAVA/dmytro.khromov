package ua.nure.kn.khromov.usermanagement.web;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DaoFactory;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class BrowseServlet extends HttpServlet {
    private static final String ADD_BUTTON = "addButton";
    private static final String EDIT_BUTTON = "editButton";
    private static final String DELETE_BUTTON = "deleteButton";
    private static final String DETAILS_BUTTON = "detailsButton";
    private static final String ADD_SERVLET = "/add";
    private static final String BROWSE_JSP = "/browse.jsp";
    private static final String EDIT_SERVLET = "/edit";
    private static final String USERMANAGEMENT_BROWSE_SERVLET = "/usermanagement/browse";
    private static final String DETAILS_SERVLET = "/details";
    private static final String ID = "id";

    /**
     * Defines what to do when one of the buttons clicked.
     * */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(ADD_BUTTON) != null) {
            add(req, resp);
        } else if (req.getParameter(EDIT_BUTTON) != null) {
            edit(req, resp);
        } else if (req.getParameter(DELETE_BUTTON) != null) {
            delete(req, resp);
        } else if (req.getParameter(DETAILS_BUTTON) != null) {
            details(req, resp);
        } else {
            browse(req, resp);
        }
    }

    /**
     * Goes to "/add" servlet page.
     * */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_SERVLET).forward(req, resp);
    }

    /**
     * Sets user to the session and goes to "/edit" servlet page.
     * */
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setUserToSession(req, resp, EDIT_SERVLET);
    }

    /**
     * Sets user to the session and, if exception raises, goes to "/browse.jsp" page.
     * If user was found, goes to servlet page.
     * */
    private void setUserToSession(HttpServletRequest req, HttpServletResponse resp, String servlet) throws ServletException, IOException {
        String id = getUserId(req, resp);
        if (id == null) {
            return;
        }
        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(id));
            req.getSession(true).setAttribute("user", user);
        } catch (DatabaseException e) {
            req.setAttribute("error", "ERROR:" + e.toString());
            req.getRequestDispatcher(BROWSE_JSP).forward(req, resp);
            return;
        }
        req.getRequestDispatcher(servlet).forward(req, resp);
    }

    /**
     * Deletes user from database and goes to "/browse" servlet page.
     * If exception raises, goes to "/browse.jsp" page.
     * */
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = getUserId(req, resp);
        if (id == null) {
            return;
        }

        try {
            User user = DaoFactory.getInstance().getUserDao().find(new Long(id));
            DaoFactory.getInstance().getUserDao().delete(user);
        } catch (DatabaseException e) {
            req.setAttribute("error", "ERROR:" + e.toString());
            req.getRequestDispatcher(BROWSE_JSP).forward(req, resp);
            return;
        }
        resp.sendRedirect(USERMANAGEMENT_BROWSE_SERVLET);
    }

    /**
     * Gets user id.
     * */
    private String getUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ID);
        if (id == null || id.trim().isEmpty()) {
            req.setAttribute("error", "You have to select a user.");
            req.getRequestDispatcher(BROWSE_JSP).forward(req, resp);
            return null;
        }
        return id;
    }

    /**
     * Sets user to session and goes to "/details" servlet page.
     * */
    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setUserToSession(req, resp, DETAILS_SERVLET);
    }

    /**
     * Finds all users and goes to "/browse.jsp" page.
     * */
    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Collection users;
        try {
            users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher(BROWSE_JSP).forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ua.nure.kn.khromov.usermanagement.web;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DaoFactory;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends EditServlet {
    private static final String ADD_JSP = "/add.jsp";

    /**
     * Shows "Add User" page.
     * */
    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADD_JSP).forward(req, resp);
    }

    /**
     * Creates user.
     * */
    @Override
    protected void processUser(User user) throws DatabaseException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
}

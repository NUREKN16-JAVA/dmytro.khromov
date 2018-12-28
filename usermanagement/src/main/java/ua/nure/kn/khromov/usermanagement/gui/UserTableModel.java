package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.util.Messages;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private List users = null;
    private User user = null;
    private static final String[] COLUMN_NAMES = {"ID", Messages.getString("first_name"), Messages.getString("last_name")};
    private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};

    /**
     * Creates table model in depend on collection of users.
     *
     * @param users to create model.
     * */
    public UserTableModel(Collection users) {
        this.users = new ArrayList(users);
    }

    /**
     * {@inheritDoc]}
     * */
   public UserTableModel(User user){
        this.user = user;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    /**
     * {@inheritDoc]}
     * */
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * {@inheritDoc]}
     * */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = (User) users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getFirstName();
            case 2:
                return user.getLastName();
        }
        return null;
    }

    /**
     * Gets class in depend on columnIndex.
     *
     * @param columnIndex of class to return.
     * @return Class on columnIndex position.
     * */
    public Class getColumnClass(int columnIndex){
        return COLUMN_CLASSES[columnIndex];
    }

    /**
     * Gets class name in depend on columnIndex.
     *
     * @param columnIndex of class name to return.
     * @return String class name on columnIndex position.
     * */
    public String getColumnName(int columnIndex){
        return COLUMN_NAMES[columnIndex];
    }

    public void addUsers(Collection users) {
        this.users.addAll(users);
    }

    public void clearUsers() {
        this.users = new ArrayList();
    }
}

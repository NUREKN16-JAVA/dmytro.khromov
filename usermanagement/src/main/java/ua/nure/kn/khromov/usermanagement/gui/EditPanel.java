package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;

public class EditPanel extends AbstractPanel implements ActionListener {
    private User user;

    /**
     * {@inheritDoc}
     * */
    public EditPanel(MainFrame frame){
        super(frame);
        this.setName("editPanel");
    }

    /**
     * {@inheritDoc}
     * */
    public void initialize() {
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void performAction() {
        setUserInfo(user);
        try{
            parent.getDao().update(user);
        }catch (DatabaseException e1){
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        user = null;
    }

    /**
     * Shows edit panel.
     * */
    public void showEditPanel(User user) {
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getLastNameField().setText(user.getLastName());
        DateFormat dateFormat = DateFormat.getDateInstance();
        getDateOfBirthField().setText(dateFormat.format(user.getDateOfBirth()));
    }
}

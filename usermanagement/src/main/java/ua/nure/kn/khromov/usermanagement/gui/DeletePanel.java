package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;
import ua.nure.kn.khromov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeletePanel extends AbstractPanel implements ActionListener {
    private User user;

    /**
     * {@inheritDoc}
     * */
    public DeletePanel(MainFrame frame){
        super(frame);
        this.setName("deletePanel");
    }

    /**
     * {@inheritDoc}
     * */
    public void initialize() {
        this.setLayout(new BorderLayout());
        this.add(getLabelPanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void performAction() {
        try{
            parent.getDao().delete(user);
        }catch (DatabaseException e1){
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shows delete panel.
     * */
    public void showDeletePanel(User user) {
        this.user = user;
        getLabel().setText(Messages.getString("sure_to_delete") + " " + user.getFirstName() + " " + user.getLastName() + "?");
    }
}
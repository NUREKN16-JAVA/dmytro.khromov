package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;

import javax.swing.*;
import java.awt.*;

public class AddPanel extends AbstractPanel{
    /**
     * {@inheritDoc}
     * */
    public AddPanel(MainFrame frame){
        super(frame);
        this.setName("addPanel");
    }

    /**
     * {@inheritDoc}
     * */
    @Override
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
        User user = new User();
        setUserInfo(user);
        try{
            parent.getDao().create(user);
        }catch (DatabaseException e1){
            JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

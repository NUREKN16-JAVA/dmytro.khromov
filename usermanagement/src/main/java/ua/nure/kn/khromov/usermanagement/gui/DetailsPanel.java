package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.util.Messages;

import java.awt.*;
import java.awt.event.ActionListener;

public class DetailsPanel extends AbstractPanel implements ActionListener {
    /**
     * {@inheritDoc}
     * */
    public DetailsPanel(MainFrame frame){
        super(frame);
        this.setName("detailsPanel");
    }

    /**
     * {@inheritDoc}
     * */
    public void initialize() {
        this.setLayout(new BorderLayout());
        this.add(getLabelPanel(), BorderLayout.CENTER);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void performAction() {
    }

    /**
     * Shows details panel.
     * */
    public void showDetailsPanel(User user) {
        getLabel().setText(new StringBuilder().append("<html><body><div style=\"text-align: center;\">ID:").append(" ").append(user.getId()).append("<br/>")
                .append(Messages.getString("first_name")).append(": ").append(user.getFirstName()).append("<br/>")
                .append(Messages.getString("last_name")).append(": ").append(user.getLastName()).append("<br/>")
                .append(Messages.getString("date_of_birth") + ": " + user.getDateOfBirth()).append("</div></body></html>").toString());
    }
}

package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

public abstract class AbstractPanel extends JPanel implements ActionListener {
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;
    protected MainFrame parent;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;
    private JPanel fieldPanel;
    private JPanel infoPanel;
    private JLabel infoLabel;

    /**
     * Creates panel.
     *
     * @param parent MainFrame from which load the panel.
     * */
    public AbstractPanel(MainFrame parent){
        this.parent = parent;
        initialize();
    }
    /**
     * Initializes current panel.
     * */
    public abstract void initialize();

    /**
     * Gets singleton field panel.
     *
     * @return fieldPanel.
     * */
    public JPanel getFieldPanel() {
        if(fieldPanel == null){
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabeledField(fieldPanel, Messages.getString("first_name"), getFirstNameField());
            addLabeledField(fieldPanel, Messages.getString("last_name"), getLastNameField());
            addLabeledField(fieldPanel, Messages.getString("date_of_birth"), getDateOfBirthField());
        }
        return fieldPanel;
    }

    /**
     * Adds labeled field to panel.
     *
     * @param panel to which add labelText and textField.
     * @param labelText to add to panel.
     * @param textField to add to panel.
     * */
    public void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    /**
     * Gets singleton buttons panel.
     *
     * @return buttonPanel.
     * */
    public JPanel getButtonsPanel() {
        if(buttonPanel == null){
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }
        return buttonPanel;
    }

    /**
     * Gets singleton "ok" button.
     *
     * @return okButton.
     * */
    public JButton getOkButton() {
        if(okButton == null){
            okButton = new JButton();
            okButton.setText(Messages.getString("ok"));
            okButton.setName("okButton");
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }
        return okButton;
    }

    /**
     * Gets singleton "cancel" button.
     *
     * @return cancelButton.
     * */
    public JButton getCancelButton() {
        if(cancelButton == null){
            cancelButton = new JButton();
            cancelButton.setText(Messages.getString("cancel"));
            cancelButton.setName("cancelButton");
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    /**
     * Gets singleton first name field.
     *
     * @return firstNameField.
     * */
    public JTextField getFirstNameField() {
        if(firstNameField == null){
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField");
        }
        return firstNameField;
    }

    /**
     * Gets singleton last name field.
     *
     * @return lastNameField.
     * */
    public JTextField getLastNameField() {
        if(lastNameField == null){
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField");
        }
        return lastNameField;
    }

    /**
     * Gets singleton date of birth field.
     *
     * @return dateOfBirthField.
     * */
    public JTextField getDateOfBirthField() {
        if(dateOfBirthField == null){
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField");
        }
        return dateOfBirthField;
    }

    /**
     * Gets singleton label panel.
     *
     * @return labelPanel.
     * */
    public JPanel getLabelPanel() {
        if(infoPanel == null){
            infoPanel = new JPanel();
            infoPanel.add(getLabel(), null);
        }
        return infoPanel;
    }

    /**
     * Gets singleton info label.
     *
     * @return infoLabel.
     * */
    public JLabel getLabel() {
        if(infoLabel == null){
            infoLabel = new JLabel();
        }
        return infoLabel;
    }

    /**
     * Gets singleton button panel.
     *
     * @return buttonPanel.
     * */
    public JPanel getButtonPanel() {
        if(buttonPanel == null){
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
        }
        return buttonPanel;
    }

    /**
     * Performs action if button "ok" clicked.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if("ok".equalsIgnoreCase(e.getActionCommand())){
            performAction();
        }
        clearFields();
        this.setVisible(false);
        parent.showBrowsePanel();
    }

    /**
     * Performs action.
     * */
    public abstract void performAction();

    /**
     * Clears fields.
     * */
    public void clearFields(){
        getFirstNameField().setText("");
        getFirstNameField().setBackground(Color.WHITE);

        getLastNameField().setText("");
        getLastNameField().setBackground(Color.WHITE);

        getDateOfBirthField().setText("");
        getDateOfBirthField().setBackground(Color.WHITE);
    }

    /**
     * Sets user's info.
     *
     * @param user to set info to.
     * */
    public void setUserInfo(User user){
        user.setFirstName(getFirstNameField().getText());
        user.setLastName(getLastNameField().getText());

        DateFormat dateFormat = DateFormat.getDateInstance();
        try{
            user.setDateOfBirth(dateFormat.parse(getDateOfBirthField().getText()));
        }catch (ParseException e1){
            getDateOfBirthField().setBackground(Color.RED);
        }
    }
}

package ua.nure.kn.khromov.usermanagement.gui;

import ua.nure.kn.khromov.usermanagement.User;
import ua.nure.kn.khromov.usermanagement.db.DaoFactory;
import ua.nure.kn.khromov.usermanagement.db.DatabaseException;
import ua.nure.kn.khromov.usermanagement.db.UserDao;
import ua.nure.kn.khromov.usermanagement.util.Messages;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private BrowsePanel browsePanel;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    private AddPanel addPanel;
    private DeletePanel deletePanel;
    private DetailsPanel detailsPanel;
    private UserDao dao;
    private Long userId;
    private EditPanel editPanel;

    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDao();
        initialize();
    }

    /**
     * Gets UserDao dao.
     * */
    public UserDao getDao() {
        return dao;
    }

    /**
     * Initializes MainFrame structure properties.
     * */
    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("user_management"));
        this.setContentPane(getContentPanel());
    }

    /**
     * Shows panel.
     *
     * @param panel to show.
     * */
    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }

    /**
     * Shows add panel.
     * */
    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    /**
     * Shows browse panel.
     * */
    public void showBrowsePanel() {
        this.showPanel(getBrowsePanel());
    }

    /**
     * Shows delete panel.
     *
     * @param user to delete.
     * */
    public void showDeletePanel(User user) {
        DeletePanel deletePanel = getDeletePanel();
        ((DeletePanel)deletePanel).showDeletePanel(user);
        this.showPanel(getDeletePanel());
    }

    /**
     * Shows details panel.
     *
     * @param user to get details.
     * */
    public void showDetailsPanel(User user){
        DetailsPanel detailsPanel = getDetailsPanel();
        ((DetailsPanel)detailsPanel).showDetailsPanel(user);
        this.showPanel(getDetailsPanel());
    }

    /**
     * Shows edit panel.
     *
     * @param user to edit.
     * */
    public void showEditPanel(User user){
        EditPanel editPanel = getEditPanel();
        ((EditPanel)editPanel).showEditPanel(user);
        this.showPanel(getEditPanel());
    }

    /**
     * Gets singleton browse panel.
     *
     * @return browsePanel.
     * */
    private BrowsePanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();

        return browsePanel;
    }

    /**
     * Gets singleton edit panel.
     *
     * @return editPanel.
     * */
    private EditPanel getEditPanel(){
        if(editPanel== null){
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }

    /**
     * Gets singleton edit panel.
     *
     * @return editPanel.
     * */
    private DetailsPanel getDetailsPanel() {
        if(detailsPanel == null){
            detailsPanel = new DetailsPanel(this);
        }

        return detailsPanel;
    }

    /**
     * Gets singleton delete panel.
     *
     * @return deletePanel.
     * */
    private DeletePanel getDeletePanel() {
        if (deletePanel == null) {
            deletePanel = new DeletePanel(this);
        }

        return deletePanel;
    }

    /**
     * Gets singleton add panel.
     *
     * @return addPanel.
     * */
    private AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }

        return addPanel;
    }

    /**
     * Gets singleton content panel.
     *
     * @return contentPanel.
     * */
    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }

        return contentPanel;
    }

    /**
     * Gets user.
     *
     * @return user if user was found in DB.
     * @return null if user was not found.
     * */
    public User getUser() {
        try {
            return dao.find(userId);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Main method to load application.
     * */
    public static void main(String args[]) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

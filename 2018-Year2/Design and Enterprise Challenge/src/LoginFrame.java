import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import model.Date;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-09
 */
public class LoginFrame {
    private Client c1;
    public JPanel LoginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JPanel loginScreen;
    private JPanel loaderScreen;
    private JPanel header;
    private JLabel workGroupLabel;
    private JLabel label_currentJob;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JButton logoutButton;
    private JLabel activeTaskDuration;
    private JButton systemDefinedButton;
    private JButton userDefinedButton;
    private JPanel MainMenu;
    private JPanel SystemDefined;
    public JPanel UserDefined;
    private JButton returnButton;
    public JPanel UserPanel;
    private JList<String> sidebarLog;
    private JButton Job_WoodCutting;
    private JButton Job_Picking;
    private JButton Job_Loading;
    private JButton Job_Break;
    private JButton Job_Other;
    private JScrollPane LogScrollPane;
    public JPanel UserDefined_Main;
    public JPanel UserDefined_Loading;
    private JButton Order2;
    private JLabel Order1;
    private JPanel picksPanel;
    private JList orderList;
    private JLabel successLabel;
    private DefaultListModel<String> logModel;


    private Date dateHandler = new Date(timeLabel, dateLabel);


    public LoginFrame(Client c) {
        this.c1 = c;
        loginButton.addActionListener(e -> c1.runLogin());
        logoutButton.addActionListener(e -> c.logout());
        returnButton.addActionListener(e -> c.showPreviousScreen());
        userDefinedButton.addActionListener(e -> c.updateUserScreen("UserDefined"));
        systemDefinedButton.addActionListener(e -> c.updateUserScreen("SystemDefined"));
        Job_WoodCutting.addActionListener(e -> c.startTask(Job_WoodCutting.getText()));
        Job_Break.addActionListener(e -> c.startTask(Job_Break.getText()));
        Job_Loading.addActionListener(e -> c.startTask(Job_Loading.getText()));
        Job_Picking.addActionListener(e -> c.startTask(Job_Picking.getText()));
        Job_Loading.addActionListener(e -> {
            c.updateUserDefinedScreen("UserDefined_Loading");
            c.startTask(Job_Loading.getText());
        });
        Order2.addActionListener(e -> picksPanel.setVisible(!picksPanel.isVisible()));

                /**
                 * Add ENTER Key functionallity
                 */
                KeyAdapter enterPress = new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        super.keyPressed(e);
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                c1.runLogin();
                        }
                    }
                };
        loginButton.addKeyListener(enterPress);
        usernameField.addKeyListener(enterPress);
        passwordField.addKeyListener(enterPress);

    }

    public void setUpMainFrame(int privilleges) {
        if (privilleges == 0 || privilleges == 1 || privilleges == 2) {
            systemDefinedButton.setVisible(false);
        } else if (privilleges == 3) {
            systemDefinedButton.setVisible(true);
        }
    }

    public DefaultListModel<String> getLogPane() {
        return this.logModel;
    }

    private void createUIComponents() {

        // TODO: place custom component creation code here

        logModel = new DefaultListModel<>();
        sidebarLog = new JList<>(logModel);

        sidebarLog.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        sidebarLog.setLayoutOrientation(JList.VERTICAL);
        sidebarLog.setVisibleRowCount(3);
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(String text) {
        this.usernameField.setText(text);
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(String text) {
        this.passwordField.setText(text);
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String text) {
        this.errorLabel.setText(text);
    }

    public JLabel getWorkGroupLabel() {
        return workGroupLabel;
    }

    public void setWorkGroupLabel(String text) {
        this.workGroupLabel.setText(text);
    }

    public JLabel getLabel_currentJob() {
        return label_currentJob;
    }

    public void setLabel_currentJob(String text) {
        this.label_currentJob.setText(text);
    }

    public JLabel getSuccessLabel() {
        return successLabel;
    }

    public void setSuccessLabel(String message) {
        this.successLabel.setText(message);
    }

    public Date getDateHandler() {
        return dateHandler;
    }

    public void setDateHandler(Date dateHandler) {
        this.dateHandler = dateHandler;
    }

    public JLabel getActiveTaskDuration() {
        return activeTaskDuration;
    }

    public void setActiveTaskDuration(String text) {
        this.activeTaskDuration.setText(text);
    }

    public JButton getReturnButton() {
        return returnButton;
    }

}

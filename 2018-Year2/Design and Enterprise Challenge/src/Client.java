
import model.Chronomter;
import model.Date;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.Timer;

/**
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2019-01-09
 */
public class Client {
    public static JFrame mainFrame;
    private LoginFrame SCREEN = new LoginFrame(this);
    private JPanel cardsPanel = SCREEN.LoginPanel;
    private CardLayout cardLayout = (CardLayout) SCREEN.LoginPanel.getLayout();
    private int loginAttempts;
    private java.util.Timer timer = new Timer();
    private int loginBlocked = 5;
    private Stack<String> screensOrder = new Stack<>();
    private Date dateModule = new Date(SCREEN.getTimeLabel(), SCREEN.getDateLabel());
    private JPanel mainCardsPanel = SCREEN.UserPanel;
    private CardLayout mainCardLayout = (CardLayout) SCREEN.UserPanel.getLayout();

    private JPanel UserDefinedPanel = SCREEN.UserDefined;
    private CardLayout UserDefinedCardLayout = (CardLayout) SCREEN.UserDefined.getLayout();

    private String lastMessage = "Starting...";
    private int logSize = 0;
    private Employee employee;

    private Chronomter chronomter = new Chronomter(SCREEN.getActiveTaskDuration());

    Client() {
        setupAndRunGUI();
    }

    public static void main(String[] args) {
        Client c = new Client();
    }

    private void setupAndRunGUI() {
        mainFrame = new JFrame("Exceeding Exceptional Excellence");
        mainFrame.setContentPane(cardsPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        screensOrder.push("Login");
    }

    public void updateScreen(String cardName) {
        if (!screenUsed(cardName)) {
            screensOrder.push(cardName);
            cardLayout.show(cardsPanel, cardName);
        }
    }

    public void updateUserScreen(String cardName) {
        if (!screenUsed(cardName)) {
            SCREEN.getReturnButton().setVisible(true);
            screensOrder.push(cardName);
            mainCardLayout.show(mainCardsPanel, cardName);
        }
    }
    public void updateUserDefinedScreen(String cardName) {
        if (!screenUsed(cardName)) {
            screensOrder.push(cardName);
            UserDefinedCardLayout.show(UserDefinedPanel, cardName);
        }
    }

    private boolean screenUsed(String cardName) {
        for (String name : screensOrder) {
            if (cardName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void logout() {
        cardLayout.show(cardsPanel, "Login");
        SCREEN.getLogPane().removeAllElements();
        logSize = 0;
    }

    public void showPreviousScreen() {
        endTask();
        screensOrder.pop();
        switch (screensOrder.lastElement()) {
            case "UserDefined":
                mainCardLayout.show(mainCardsPanel,"UserDefined");
                UserDefinedCardLayout.show(UserDefinedPanel,"UserDefined_Main");
                break;
            case "MainMenu":
                mainCardLayout.show(mainCardsPanel,"MainMenu");
                SCREEN.getReturnButton().setVisible(false);
                cardLayout.show(cardsPanel, screensOrder.lastElement()); // show the last element in the stack
                break;
            default:
                break;
        }
//        if (!screensOrder.empty()) {
//            endTask();
//            screensOrder.pop(); // release the current one
//            if(screensOrder.lastElement().equals("MainMenu")) {
//                SCREEN.getReturnButton().setVisible(false);
//            }
//            if (screensOrder.size() > 1) {
//                mainCardLayout.show(mainCardsPanel, screensOrder.lastElement());
//                if (screensOrder.lastElement().equals("MainMenu")) {
//                    SCREEN.getReturnButton().setVisible(false);
//                }
//            } else {
//                cardLayout.show(cardsPanel, screensOrder.lastElement()); // show the last element in the stack
//            }
//        }
    }

    public void runLogin() {
            this.employee = login();
            if (this.employee == null) {
                checkFraud();
                clearLoginTextFields();
            } else {
                SCREEN.setWorkGroupLabel(getWorkGroup(this.employee.getWorkGroup()));
                dateModule.startChronomter(chronomter,SCREEN.getActiveTaskDuration());
                showLoggedInScreen();
                updateScreen("MainMenu");
                prepareScreen(this.employee);
            }
    }

    private void showLoggedInScreen() {
        cardLayout.show(cardsPanel, "Home");
        writeToConsole(lastMessage);
    }

    private void prepareScreen(Employee current) {
        SCREEN.setUpMainFrame(current.getPrivilleges());
    }

    private Employee login() {
        String pwd = new String(SCREEN.getPasswordField().getPassword());
        for (Employee employee : Config.EMPLOYEES) {
            if (SCREEN.getUsernameField().getText().equals(employee.getUsername()) && pwd.equals(employee.getPassword())) {
                System.out.println("The username and password match! ");
                System.out.println("Welcome : " + employee.getUsername());
                clearLoginTextFields();
                SCREEN.setErrorLabel("");
                loginAttempts = 0;
                loginBlocked = 5;
                return employee;
            }
        }
        return null;
    }

    private void checkFraud() {
        loginAttempts++;
        if (loginAttempts > 3) {
            SCREEN.setErrorLabel("To many attempts! Please log in after " + loginBlocked + " seconds! ");
            loginBlocked *= loginAttempts / 3;
        } else {
            SCREEN.setErrorLabel("The username and/or password is incorrect. Please try again! ");
        }
    }

    private void clearLoginTextFields() {
        SCREEN.setUsernameField("");
        SCREEN.setPasswordField("");
    }

    private String getWorkGroup(int workGroupID) {
        String workGroup = null;
        switch (workGroupID) {
            case 0:
                workGroup = "Picking";
                break;
            case 1:
                workGroup = "Wood Cutting";
                break;
            case 2:
                workGroup = "Loading";
                break;
            case 3:
                workGroup = "Supervising";
                break;
            default:
                workGroup = "N/A";
                break;
        }
        return workGroup;
    }

    public void writeToConsole(String message) {
        if (logSize == 0) {
            SCREEN.getLogPane().addElement("[" + SCREEN.getTimeLabel().getText() + "] " + lastMessage);
            logSize = 1;
        } else {
            String lastMessage = SCREEN.getLogPane().getElementAt(logSize - 1);
            SCREEN.getLogPane().removeElementAt(logSize - 1);
            SCREEN.getLogPane().addElement(lastMessage +
                    " (Duration: [" + SCREEN.getActiveTaskDuration().getText() + "]");
            SCREEN.getLogPane().addElement("[" + SCREEN.getTimeLabel().getText() + "] " + message);
            logSize++;
        }
    }

    public void startTask(String taskDescription) {
        this.employee.setCurrentTask(taskDescription);
        writeToConsole("Starting Task: " + taskDescription);
        dateModule.startChronomter(chronomter,SCREEN.getActiveTaskDuration());
        SCREEN.getLabel_currentJob().setText(taskDescription);
    }

    public void endTask() {
        writeToConsole("Exiting Task " + this.employee.getCurrentTask());
        writeToConsole("Idle time...");
        this.employee.setCurrentTask("Nothing");
        dateModule.startChronomter(chronomter,SCREEN.getActiveTaskDuration());
        SCREEN.getLabel_currentJob().setText(this.employee.getCurrentTask());
    }

}

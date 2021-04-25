import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * LoginPageGUI.java
 *
 * This program prompts a user to enter a username and password in order to log-in to a preexisting account
 * or create a new account for a new user.
 *
 * @author Team 060, Section 11
 * @version May 03, 2021
 * */

public class LoginPageGUI implements Runnable {

    //Make GUI elements global fields
    JLabel instructions;
    JLabel emailLabel;
    JTextField emailField;
    JLabel passwordLabel;
    JTextField passwordField;
    JButton enterButton;
    JButton makeAccountButton;

    //Create an EDT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LoginPageGUI());
    }

    //Creates the GUI
    public void run() {
        //Set Up JFrame
        JFrame frame = new JFrame("Welcome to CampsGram!"); //sets new frame
        frame.setSize(480,200); //sets frame size
        frame.setLocationRelativeTo(null); //sets the location of the frame
        frame.setResizable(false); //sets the frame as unresizable

        //Create Elements and add them to JPanel
        JPanel panel = new JPanel();
        instructions = new JLabel("Insert E-mail and Password to Login, or Create New Account to Begin");
        panel.add(instructions);
        emailLabel = new JLabel("Enter your Email:     ");
        panel.add(emailLabel);
        emailField = new JTextField("", 20);
        panel.add(emailField);
        passwordLabel = new JLabel("Enter your Password:       ");
        panel.add(passwordLabel);
        passwordField = new JTextField("", 20);
        panel.add(passwordField);
        enterButton = new JButton("            Enter             ");
        enterButton.addActionListener(actionListener); //add action listener to respond to button clicks
        panel.add(enterButton);
        makeAccountButton = new JButton("    Make New Account    ");
        makeAccountButton.addActionListener(actionListener); //add action listener to respond to button clicks
        panel.add(makeAccountButton);

        //Add JPanel to JFrame and make visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //Code to perform when Enter Button is clicked
            if (e.getSource() == enterButton) {
                if (validateAccount()) {
                    //TODO: Switch Screens
                }
            }
            //Code to perform when Make Account Button is clicked
            if (e.getSource() == makeAccountButton) {
                //TODO: Flesh out Login-Account Transition
                //sample account
                String accountName = emailField.getText();

                //Creates an ArrayList of Test Profiles
                ArrayList<ProfileGUI> profiles = new ArrayList<>();
                for(int i = 0; i < 7; i++) {
                    profiles.add(new ProfileGUI());
                }

                AccountGUI acc = new AccountGUI(accountName, profiles);

                acc.run();
                /*
                if (createAccount()) {

                }
                 */
                
                if (createAccount()) {
                    //TODO: Switch Screens
                }
            }
        }
    };

    //The method sends the user inputs to the server to validate whether the account exists
    public boolean validateAccount() {
        boolean validAccount = false;
        String validation;
        String email;
        String password;

        //Checks whether input fields are empty
        if (emailField.getText() == null || passwordField.getText() == null) {
            JOptionPane.showMessageDialog(null, "Please Enter Both an Email AND a Password!",
                    "CampsGram Login", JOptionPane.ERROR_MESSAGE);

        } else if (!emailField.getText().contains("@") || !emailField.getText().contains(".")) {
            JOptionPane.showMessageDialog(null, "Please Enter a Valid Email Address",
                    "CampsGram Login", JOptionPane.ERROR_MESSAGE);
        } else if (passwordField.getText().length() < 8) {
            JOptionPane.showMessageDialog(null, "The Password Must Be At Least 8 " +
                    "Characters Long!", "CampsGram Login", JOptionPane.ERROR_MESSAGE);
        } else {
            email = emailField.getText();
            emailField.setText("");
            password = passwordField.getText();
            passwordField.setText("");

            //Tries to connect to server and send inputs
            try {
                Socket client = new Socket("localhost", 1234);
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(client.getOutputStream());

                writer.write("Validate");
                writer.println();
                writer.write(email);
                writer.println();
                writer.write(password);
                writer.println();

                writer.flush();

                validation = reader.readLine();
                if(validation.equals("False")) {
                    validAccount = false;
                } else {
                    validAccount = true;
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                        "CampsGram", JOptionPane.ERROR_MESSAGE);
            }

        }
        return validAccount;

    }

    //This method sends the user inputs to determine whether a new account may be created
    public boolean createAccount() {
        boolean accountCreated = false;
        String creation;
        String email;
        String password;

        //Checks whether input fields are empty
        if (emailField.getText() == null || passwordField.getText() == null) {
            JOptionPane.showMessageDialog(null, "Please Enter Both an Email AND a Password!",
                    "CampsGram", JOptionPane.ERROR_MESSAGE);
        } else {
            email = emailField.getText();
            emailField.setText("");
            password = passwordField.getText();
            passwordField.setText("");

            //Attempts to connect to server and send inputs
            try {
                Socket client = new Socket("localhost", 1234);
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer = new PrintWriter(client.getOutputStream());

                writer.write("Create");
                writer.println();
                writer.write(email);
                writer.println();
                writer.write(password);
                writer.println();

                writer.flush();

                creation = reader.readLine();
                if(creation.equals("False")) {
                    accountCreated = false;
                } else {
                    accountCreated = true;
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                        "CampsGram", JOptionPane.ERROR_MESSAGE);
            }

        }
        return accountCreated;
    }
}

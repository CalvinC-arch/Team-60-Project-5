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
 * run method - creates and displays the main GUI
 * validateAccount method - boolean method that is run whenever the EnterButton is clicked. It attempts to connect to the server and send it "Validate" to signal to the
 * server what to do. Then it sends the server the email and password submitted in the text fields. If the server is able to adequately process this request, it sends
 * true or false back, which is read in. This boolean value is returned.
 * createAccount method - boolean method that is very similar to the validateAccount method, except that it sends the server the word "Create" as a signal for what
 * processing the server should do. This method also reads in true or false from the server to determine whether the processing was complete.
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

       
        frame.add(panel, BorderLayout.CENTER); //add JPanel to JFrame
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //set default close operation to dispose
        frame.setVisible(true); //makes the frame visible to the user

    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) { //detects button clicks
            if (e.getSource() == enterButton) { //Code to perform when Enter Button is clicked
                validateAccount();
            }
            if (e.getSource() == makeAccountButton) { //Code to perform when Make Account Button is clicked
                //TODO: Flesh out Login-Account Transition
                //sample account
                String accountName = emailField.getText();

                //adds sample profiles
                ArrayList<Profile> profiles = new ArrayList<>();
                for(int i = 0; i < 7; i++) {
                    profiles.add(new Profile());
                }

                //creates and runs the account object
                Account acc = new Account(emailField.getText(), passwordField.getText(), profiles);
                acc.run();

                //erics validate code is below
                /*
                if (createAccount()) {

                }
                 */
                
                createAccount();
            }
        }
    };

    //The method sends the user inputs to the server to validate whether the account exists
    public void validateAccount() {
        //variables to use in the method
        boolean validAccount = false;
        String validation;
        String email;
        String password;

        while (!validAccount) {
            //Checks whether input fields are empty. Display prompts user to fill out all fields if not.
            if (emailField.getText() == null || passwordField.getText() == null) {
                JOptionPane.showMessageDialog(null, "Please Enter Both an Email AND a Password!",
                        "CampsGram Login", JOptionPane.ERROR_MESSAGE);

                //Checks whether the email field input contains special characters '@' and '.'. Display prompts user to input a valid email if not.
            } else if (!emailField.getText().contains("@") || !emailField.getText().contains(".")) {
                emailField.setText("");
                JOptionPane.showMessageDialog(null, "Please Enter a Valid Email Address",
                        "CampsGram Login", JOptionPane.ERROR_MESSAGE);

                //Checks whether the password field contains at least 8 characters. Display prompts user to make an adequately long password if not.
            } else if (passwordField.getText().length() < 8) {
                passwordField.setText("");
                JOptionPane.showMessageDialog(null, "The Password Must Be At Least 8 " +
                        "Characters Long!", "CampsGram Login", JOptionPane.ERROR_MESSAGE);

            } else {
                //If inputs are in the proper format, they are stored as variables
                email = emailField.getText(); //store email field input in variable
                emailField.setText(""); //reset the text field to empty
                password = passwordField.getText(); //store password field input in variable
                passwordField.setText(""); //reset the text field to empty

                //Tries to connect to server and send inputs
                try {
                    Socket client = new Socket("localhost", 1234);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter writer = new PrintWriter(client.getOutputStream());

                    writer.write("Validate"); //send signal word
                    writer.println();
                    writer.write(email); //send email
                    writer.println();
                    writer.write(password); //send password
                    writer.println();

                    writer.flush(); //flush buffer

                    //TODO: Establish protocols for receiving profile array list

                    validation = reader.readLine(); //read in true or false
                    if(validation.equals("False")) {
                        validAccount = false;
                    } else {
                        validAccount = true;
                    } // update boolean based on response
                    Account account = new Account()

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                            "CampsGram", JOptionPane.ERROR_MESSAGE); // display error message if cannot connect to server and let user try again
                }

            }
        }

    }

    //This method sends the user inputs to determine whether a new account may be created
    public void createAccount() {
        //variables used in method
        boolean accountCreated = false;
        String creation;
        String email;
        String password;

        while (!accountCreated) {
            //Checks whether input fields are empty. Display prompts user to fill out all fields
            if (emailField.getText() == null || passwordField.getText() == null) {
                JOptionPane.showMessageDialog(null, "Please Enter Both an Email AND a Password!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE);
                //Checks whether the email field input contains special characters '@' and '.'. Display prompts user to input a valid email if not.
            } else if (!emailField.getText().contains("@") || !emailField.getText().contains(".")) {
                emailField.setText("");
                JOptionPane.showMessageDialog(null, "Please Enter a Valid Email Address",
                        "CampsGram Login", JOptionPane.ERROR_MESSAGE);

                //Checks whether the password field contains at least 8 characters. Display prompts user to make an adequately long password if not.
            } else if (passwordField.getText().length() < 8) {
                passwordField.setText("");
                JOptionPane.showMessageDialog(null, "The Password Must Be At Least 8 " +
                        "Characters Long!", "CampsGram Login", JOptionPane.ERROR_MESSAGE);

            } else {
                //If email and password have proper format, store them in variables
                email = emailField.getText(); //store email
                emailField.setText(""); //reset email field
                password = passwordField.getText(); //store password
                passwordField.setText(""); //reset password field

                //Attempts to connect to server and send inputs
                try {
                    Socket client = new Socket("localhost", 1234);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter writer = new PrintWriter(client.getOutputStream());

                    writer.write("Create"); //send signal
                    writer.println();
                    writer.write(email); //send email
                    writer.println();
                    writer.write(password); //send password
                    writer.println();

                    writer.flush(); //flush buffer



                    creation = reader.readLine(); //read in true or false
                    if(creation.equals("False")) {
                        accountCreated = false;
                    } else {
                        accountCreated = true;
                        EnterInfoGUI enterInfoGUI = new EnterInfoGUI();
                        //TODO: Run a method
                    } //update boolean

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                            "CampsGram", JOptionPane.ERROR_MESSAGE); //display error message is cannot connect to server and let user try again
                }

            }
        }

    }
}

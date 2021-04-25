import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FriendsListGUI.java
 *
 * This program creates a GUI displaying the list of friends for a profile. It contains methods that are run
 * when buttons are clicked that take care of communicating with the server in order to process accepting and
 * declining friend requests, unfriending current friends, viewing friends' profiles, and navigating away
 * from the page.
 *
 * @author Team 060, Section 11
 * @version May 03, 2021
 * */

public class FriendsListGUI implements Runnable {

    //GUI elements are global fields
    JButton backButton;

    JComboBox<String> requestsLists;
    JButton acceptButton;
    JButton declineButton;

    JComboBox<String> friendsList;
    JButton viewProfileButton;
    JButton unfriendButton;

    //Create the EDT
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new FriendsListGUI());
    }

    //Assemble the GUI
    public void run() {
        //Settings for the JFrame
        JFrame frame = new JFrame("CampsGram Friends List");
        frame.setSize(480, 200);
        frame.setResizable(false);

        //Add Back Button to Top of Screen
        JPanel northPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(actionListener);
        northPanel.add(backButton);
        frame.add(northPanel, BorderLayout.NORTH);

        //Add List of Friend Requests, Accept Button, Decline Button to Middle of Screen
        JPanel centralPanel = new JPanel();
        requestsLists = new JComboBox<>();
        requestsLists.setMaximumRowCount(3);
        centralPanel.add(requestsLists);
        acceptButton = new JButton("Accept");
        acceptButton.addActionListener(actionListener);
        centralPanel.add(acceptButton);
        declineButton = new JButton("Decline");
        declineButton.addActionListener(actionListener);
        centralPanel.add(declineButton);
        frame.add(centralPanel, BorderLayout.CENTER);

        //If no friend requests, disable accept and decline buttons
        if (requestsLists.getItemCount() == 0) {
            acceptButton.setEnabled(false);
            declineButton.setEnabled(false);
        } else {
            acceptButton.setEnabled(true);
            declineButton.setEnabled(true);
        }

        //Add friend list, view profile button, and unfriend button to screen
        JPanel southPanel = new JPanel();
        friendsList = new JComboBox<>();
        friendsList.setMaximumRowCount(3);
        southPanel.add(friendsList);
        viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(actionListener);
        southPanel.add(viewProfileButton);
        unfriendButton = new JButton("Unfriend");
        unfriendButton.addActionListener(actionListener);
        southPanel.add(unfriendButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        //If no friends, disable view profile and unfriend buttons
        if (friendsList.getItemCount() == 0) {
            viewProfileButton.setEnabled(false);
            unfriendButton.setEnabled(false);
        } else {
            viewProfileButton.setEnabled(true);
            unfriendButton.setEnabled(true);
        }

        //Make the frame visible
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    //Create action listener to respond to button clicks
    ActionListener actionListener = new ActionListener() {
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() == acceptButton) {
                if (acceptFriend()) {
                    friendsList.addItem((String) requestsLists.getSelectedItem());
                    requestsLists.removeItemAt(requestsLists.getSelectedIndex());
                    //TODO: Auto-Update other user's friend list
                }
            }
            if (e.getSource() == declineButton) {
                if (declineFriend()) {
                    requestsLists.removeItemAt(requestsLists.getSelectedIndex());
                }
            }
            if (e.getSource() == viewProfileButton) {
               if (viewProfile()) {
                   //TODO: Display Friend's Profile
               }
            }
            if (e.getSource() == unfriendButton) {
                if (unfriend()) {
                    friendsList.removeItemAt(friendsList.getSelectedIndex());
                    //TODO: Auto-Update other user's friend list
                }
            }
            if (e.getSource() == backButton) {
                if (back()) {
                    //TODO: Display user's profile
                }
            }
        }
    };

    //Method sends signal to server that accept button was clicked
    //Returns true or false depending on whether the proper processing was completed
    public boolean acceptFriend() {
        boolean friendAccepted = false;
        String friend = (String) requestsLists.getSelectedItem();

        try {
            Socket client = new Socket("localhost", 1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            writer.write("Accept");
            writer.println();
            writer.write(friend);
            writer.println();

            writer.flush();

            String verification = reader.readLine();
            if(verification.equals("True")){
                friendAccepted = true;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                    "CampsGram", JOptionPane.ERROR_MESSAGE);
        }
        return friendAccepted;
    }

    //Method sends signal to server that decline button was clicked
    //Returns true or false depending on whether the proper processing was completed
    public boolean declineFriend() {
        boolean friendDeclined = false;
        String friend = (String) requestsLists.getSelectedItem();

        try {
            Socket client = new Socket("localhost", 1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            writer.write("Decline");
            writer.println();
            writer.write(friend);
            writer.println();

            writer.flush();

            String verification = reader.readLine();
            if(verification.equals("True")){
                friendDeclined = true;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                    "CampsGram", JOptionPane.ERROR_MESSAGE);
        }
        return friendDeclined;
    }

    //Method sends signal to server that view profile button was clicked
    //Returns true or false depending on whether the proper processing was completed
    public boolean viewProfile() {
        boolean profileViewed = false;
        String friend = (String) friendsList.getSelectedItem();

        try {
            Socket client = new Socket("localhost", 1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            writer.write("View");
            writer.println();
            writer.write(friend);
            writer.println();

            writer.flush();

            String verification = reader.readLine();
            if(verification.equals("True")){
                profileViewed = true;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                    "CampsGram", JOptionPane.ERROR_MESSAGE);
        }
        return profileViewed;
    }

    //Method sends signal to server that unfriend button was clicked
    //Returns true or false depending on whether the proper processing was completed
    public boolean unfriend() {
        boolean unfriended = false;
        String friend = (String) requestsLists.getSelectedItem();

        try {
            Socket client = new Socket("localhost", 1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            writer.write("Unfriend");
            writer.println();
            writer.write(friend);
            writer.println();

            writer.flush();

            String verification = reader.readLine();
            if(verification.equals("True")){
                unfriended = true;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                    "CampsGram", JOptionPane.ERROR_MESSAGE);
        }
        return unfriended;
    }


    //Method sends signal to server that back button was clicked
    //Returns true or false depending on whether the proper processing was completed
    //TODO: Improve functionality of this method
    public boolean back() {
        boolean back = false;

        try {
            Socket client = new Socket("localhost", 1234);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            writer.write("Back");
            writer.println();
            writer.flush();

            String verification = reader.readLine();
            if(verification.equals("True")){
                back = true;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An Error Has Occurred. Try Again.",
                    "CampsGram", JOptionPane.ERROR_MESSAGE);
        }
        return back;
    }
}

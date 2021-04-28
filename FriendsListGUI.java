import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * FriendsListGUI.java
 *
 * This program creates a GUI displaying the list of friends for a profile. It contains methods that are run
 * when buttons are clicked that take care of communicating with the server in order to process accepting and
 * declining friend requests, unfriending current friends, viewing friends' profiles, and navigating away
 * from the page.
 *
 * run method - creates the GUI
 * acceptFriend method - boolean method run when accept button is clicked. It sends "Accept" to the server as a signal to process accepting friend request and receives
 * true or false back from the server depending on if the request was properly processed and returns this value
 * declineFriend method - boolean method run when decline button is clicked. It sends "Decline" to the server as a signal to process declining friend request and receives
 * true or false back from the server depening on if the request was properly processed and returns this value
 * viewProfile method - boolean method run when view profile button is clicked. It sends "View" to the server as a signal to process viewing friend's profile and receives 
 * true or false back from the server depending on if the rquest was properly processed and returns this value
 * unfriend method - boolean method run when the unfriend button is clicked. It sends "Unfriend" to the server as a signal to process unfriending and receives true or false
 * back from the server depending on the request was properly processed and returns this value
 *
 * @author Team 060, Section 11
 * @version May 03, 2021
 * */

public class FriendsListGUI implements Runnable {

    //GUI elements are global fields
    JButton backButton;

    JComboBox<String> requestsSentList;
    JButton rescindRequestButton;

    JComboBox<String> requestsPendingList;
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
        JFrame frame = new JFrame("CampsGram Friends List"); //title JFrame
        frame.setSize(600, 240); //sets size of JFrame
        frame.setResizable(false); //makes JFrame unable to be resized

        //Add Back Button to Top of Screen
        JPanel northPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(actionListener); //add action listener to back button
        northPanel.add(backButton);
        frame.add(northPanel, BorderLayout.NORTH);

        //Add List of Friend Requests, Accept Button, Decline Button to Middle of Screen
        JPanel centralPanel = new JPanel();
        JPanel centralPanelNorth = new JPanel();
        requestsSentList = new JComboBox<>();
        requestsSentList.setMaximumRowCount(3);
        //TODO: Add array elements to JComboBox
        centralPanelNorth.add(requestsSentList);
        rescindRequestButton = new JButton("Rescind Friend Request");
        rescindRequestButton.addActionListener(actionListener);
        centralPanelNorth.add(rescindRequestButton);
        centralPanel.add(centralPanelNorth, BorderLayout.NORTH);

        if(requestsSentList.getItemCount() == 0) {
            rescindRequestButton.setEnabled(false);
        } else {
            rescindRequestButton.setEnabled(true);
        }


        JPanel centralPanelSouth = new JPanel();
        requestsPendingList = new JComboBox<>();
        requestsPendingList.setMaximumRowCount(3);
        //TODO: Add array elements to JComboBox
        centralPanelSouth.add(requestsPendingList);
        acceptButton = new JButton("Accept");
        acceptButton.addActionListener(actionListener); //add action listener to accept button
        centralPanelSouth.add(acceptButton);
        declineButton = new JButton("Decline"); 
        declineButton.addActionListener(actionListener); //add action listener to decline button
        centralPanelSouth.add(declineButton);
        centralPanel.add(centralPanelSouth, BorderLayout.SOUTH);
        frame.add(centralPanel, BorderLayout.CENTER);

        //If no friend requests, disable accept and decline buttons
        if (requestsPendingList.getItemCount() == 0) {
            acceptButton.setEnabled(false);
            declineButton.setEnabled(false);
        } else {
            acceptButton.setEnabled(true);
            declineButton.setEnabled(true);
        }

        //Add friend list, view profile button, and unfriend button to screen
        JPanel southPanel = new JPanel();
        JLabel friendsLabel = new JLabel("Current Friends: ");
        southPanel.add(friendsLabel);
        friendsList = new JComboBox<>();
        friendsList.setMaximumRowCount(3);
        //TODO: Add Array Elements to JComboBox
        southPanel.add(friendsList);
        viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(actionListener); //add action listener to view profile button
        southPanel.add(viewProfileButton);
        unfriendButton = new JButton("Unfriend");
        unfriendButton.addActionListener(actionListener); //add action listener to unfriend button
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
        
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //set default close operation to dispose
        frame.setVisible(true); //make the frame visible
    }

    //Create action listener to respond to button clicks
    ActionListener actionListener = new ActionListener() {
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() == acceptButton) {
                if (acceptFriend()) {
                    friendsList.addItem((String) requestsPendingList.getSelectedItem());
                    requestsPendingList.removeItemAt(requestsPendingList.getSelectedIndex());
                    //TODO: Auto-Update other user's friend list
                }
           } //code that is run if accept button is clicked
           if (e.getSource() == declineButton) {
                if (declineFriend()) {
                    requestsPendingList.removeItemAt(requestsPendingList.getSelectedIndex());

                }
           } //code that is run if decline button is clicked
           if (e.getSource() == viewProfileButton) {
               if (viewProfile()) {
                   //TODO: Display Friend's Profile
               }
           } //code that is run if view profile button is clicked
           if (e.getSource() == unfriendButton) {
                if (unfriend()) {
                    friendsList.removeItemAt(friendsList.getSelectedIndex());
                    //TODO: Auto-Update other user's friend list
                }
           } //code that is run if unfriend button is clicked
           if (e.getSource() == backButton) {
                if (back()) {
                    //TODO: Display user's profile

                }
           } //code that is run if back button is clicked
           if (e.getSource() == rescindRequestButton) {
               rescindRequest();
           }
        }
    };

    //Method sends signal to server that accept button was clicked
    //Returns true or false depending on whether the proper processing was completed
    public boolean acceptFriend() {
        boolean friendAccepted = false;
        String friend = (String) requestsPendingList.getSelectedItem();

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
        String friend = (String) requestsPendingList.getSelectedItem();

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
        String friend = (String) requestsPendingList.getSelectedItem();

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

    public void rescindRequest() {

    }
}


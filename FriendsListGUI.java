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

    //Deals with network io
    IOMachine ioMachine;

    //GUI elements are global fields
    //NorthPanel element
    JButton backButton;

    //CentralPanelNorth elements
    JComboBox<String> requestsSentList;
    JButton rescindRequestButton;

    //CentralPanelSouth elements
    JComboBox<String> requestsPendingList;
    JButton acceptButton;
    JButton declineButton;

    //SouthPanel elements
    JComboBox<String> friendsList;
    JButton viewProfileButton;
    JButton unfriendButton;

    //Panels
    JPanel northPanel;
    JPanel centralPanel;
    JPanel centralPanelNorth;
    JPanel centralPanelSouth;
    JPanel southPanel;

    JFrame frame;

    //Fields for creating FriendsListGUI object
    private ArrayList<String> sentList;
    private ArrayList<String> receivedList;
    private ArrayList<String> friendList;
    private String username;

    //Create the EDT
    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new FriendsListGUI());
    }*/

    //FriendsListGUI object constructor
    public FriendsListGUI(String username, ArrayList<String> sentList, ArrayList<String> receivedList,
                          ArrayList<String> friendList) throws NullPointerException {
        this.username = username;
        this.sentList = sentList;
        this.receivedList = receivedList;
        this.friendList = friendList;
    }

    public FriendsListGUI(FriendsListGUI list, IOMachine ioMachine) throws NullPointerException {
        this.username = list.username;
        this.sentList = list.sentList;
        this.receivedList = list.receivedList;
        this.friendList = list.friendList;
        this.ioMachine = ioMachine;
    }



    public String getUsername() {
        return this.username;
    }

    //Assemble the GUI
    public void run() {
        //Settings for the JFrame
        frame = new JFrame("CampsGram Friends List"); //title JFrame
        frame.setSize(600, 240); //sets size of JFrame
        frame.setResizable(false); //makes JFrame unable to be resized

        //Add Back Button to Top of Screen
        northPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(actionListener); //add action listener to back button
        northPanel.add(backButton);
        frame.add(northPanel, BorderLayout.NORTH);

        centralPanel = new JPanel();
        centralPanelNorth = new JPanel();
        JLabel sent = new JLabel("Sent Friend Requests:");
        centralPanelNorth.add(sent);
        requestsSentList = new JComboBox<>();
        requestsSentList.setMaximumRowCount(3);



        centralPanelNorth.add(requestsSentList);
        rescindRequestButton = new JButton("Rescind Friend Request");
        rescindRequestButton.addActionListener(actionListener); //add action listener to rescind friend request button
        centralPanelNorth.add(rescindRequestButton);
        centralPanel.add(centralPanelNorth, BorderLayout.NORTH);

        

        centralPanelSouth = new JPanel();
        JLabel received = new JLabel("Pending Friend Requests:");
        centralPanelSouth.add(received);
        requestsPendingList = new JComboBox<>();
        requestsPendingList.setMaximumRowCount(3);



        centralPanelSouth.add(requestsPendingList);
        acceptButton = new JButton("Accept");
        acceptButton.addActionListener(actionListener); //add action listener to accept button
        centralPanelSouth.add(acceptButton);
        declineButton = new JButton("Decline"); 
        declineButton.addActionListener(actionListener); //add action listener to decline button
        centralPanelSouth.add(declineButton);
        centralPanel.add(centralPanelSouth, BorderLayout.SOUTH);
        frame.add(centralPanel, BorderLayout.CENTER);

        

        //Add friend list, view profile button, and unfriend button to screen
        southPanel = new JPanel();
        JLabel friendsLabel = new JLabel("Current Friends: ");
        southPanel.add(friendsLabel);
        friendsList = new JComboBox<>();
        friendsList.setMaximumRowCount(3);



        southPanel.add(friendsList);
        viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(actionListener); //add action listener to view profile button
        southPanel.add(viewProfileButton);
        unfriendButton = new JButton("Unfriend");
        unfriendButton.addActionListener(actionListener); //add action listener to unfriend button
        southPanel.add(unfriendButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        
        
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //set default close operation to dispose
        frame.setVisible(true); //make the frame visible

        Timer timer = new Timer(3000, refresher);
        timer.setRepeats(true);
        timer.start();
    }

    //Create action listener to respond to button clicks
    ActionListener actionListener = new ActionListener() {
       public void actionPerformed(ActionEvent e) {
           if (e.getSource() == acceptButton) {
               String username = (String) requestsPendingList.getSelectedItem();
               ioMachine.acceptFriend(getUsername(), username); //changes saved in server

           } //code that is run if accept button is clicked
           if (e.getSource() == declineButton) {
               String username = (String) requestsPendingList.getSelectedItem();
               ioMachine.declineFriend(getUsername(), username); //changes saved in server

           } //code that is run if decline button is clicked
           if (e.getSource() == viewProfileButton) {
               String username = (String) friendsList.getSelectedItem();
               Profile friend = ioMachine.findProfile(username); //retrieve friend's profile from the server
               ViewProfile view = new ViewProfile(friend, ioMachine);
               view.run();

           } //code that is run if view profile button is clicked
           if (e.getSource() == unfriendButton) {
               String username = (String) friendsList.getSelectedItem();
               ioMachine.unfriend(getUsername(), username); //changes saved in server

           } //code that is run if unfriend button is clicked
           if (e.getSource() == rescindRequestButton) {
               String username = (String) requestsSentList.getSelectedItem();
               ioMachine.rescindRequest(getUsername(), username); //changes saved in server

           }
           if (e.getSource() == backButton) {
               frame.dispose(); //close the GUI screen
           }//code that is run if back button is clicked
        }
    };

   //Code that Runs every 3 seconds, updating the view of another profile list
    transient ActionListener refresher = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            //saves the current selection
            String currentSelectionReceived = (String) requestsPendingList.getSelectedItem();

            //retrieves the complete list of users, remove the current users name from the list
            ArrayList<String> receivedRequests = ioMachine.findProfile(getUsername()).getRequestsReceived();

            //sets the usernames to the current list of users
            String[] received = receivedRequests.toArray(new String[receivedRequests.size()]);
            DefaultComboBoxModel<String> modelReceived = new DefaultComboBoxModel<>(received);
            requestsPendingList.setModel(modelReceived);

            //If no received friend requests, disable accept and decline buttons
            if (requestsPendingList.getItemCount() == 0) {
                acceptButton.setEnabled(false);
                declineButton.setEnabled(false);
            } else {
                acceptButton.setEnabled(true);
                declineButton.setEnabled(true);
            }
            
            //sets the selection what it was previously
            requestsPendingList.setSelectedItem(currentSelectionReceived);




            //saves the current selection
            String currentSelectionFriend = (String) friendsList.getSelectedItem();

            //retrieves the complete list of users, remove the current users name from the list
            ArrayList<String> friends = ioMachine.findProfile(getUsername()).getFriends();

            //sets the usernames to the current list of users
            String[] friend = friends.toArray(new String[friends.size()]);
            DefaultComboBoxModel<String> modelFriend = new DefaultComboBoxModel<>(friend);
            friendsList.setModel(modelFriend);

            //If no friends, disable view profile and unfriend buttons
            if (friendsList.getItemCount() == 0) {
                viewProfileButton.setEnabled(false);
                unfriendButton.setEnabled(false);
            } else {
                viewProfileButton.setEnabled(true);
                unfriendButton.setEnabled(true);
            }
            
            //sets the selection what it was previously
            friendsList.setSelectedItem(currentSelectionFriend);




            //saves the current selection
            String currentSelectionSent = (String) requestsSentList.getSelectedItem();

            //retrieves the complete list of users, remove the current users name from the list
            ArrayList<String> sentRequests = ioMachine.findProfile(getUsername()).getRequestsSent();

            //sets the usernames to the current list of users
            String[] sent = sentRequests.toArray(new String[sentRequests.size()]);
            DefaultComboBoxModel<String> modelSent = new DefaultComboBoxModel<>(sent);
            requestsSentList.setModel(modelSent);

            //If no sent friend requests, disable rescind friend request button
            if(requestsSentList.getItemCount() == 0) {
                rescindRequestButton.setEnabled(false);
            } else {
                rescindRequestButton.setEnabled(true);
            }
            
            //sets the selection what it was previously
            requestsSentList.setSelectedItem(currentSelectionReceived);



            //updates the GUI
            frame.revalidate();
            centralPanelNorth.repaint();
            centralPanelSouth.repaint();
            southPanel.repaint();

        }
    };

}

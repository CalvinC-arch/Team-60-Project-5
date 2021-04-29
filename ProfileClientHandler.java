import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ProfileClientHandler
 *
 * This class contains the server side processing and user interface logic for the project. It is created from a thread
 * created by the ProfileServer class. The constructor allows multiple objects of this type to be created, allowing for
 * multiple clients to connect to the server
 *
 * Geeksforgeeks was referenced to determine how to utilize threads for multiple client-server connections
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

class ProfileClientHandler extends Thread {

    final ObjectInputStream dis; //data input stream used to communicate with the server
    final ObjectOutputStream dos; //data output stream used to communicate with the server
    final Socket s; //socket used to communicate with the server
    ArrayList<Account> accounts = ProfileServer.getAccounts();  //the master arraylist of accounts on the server

    /**
     * Creates a new ProfileClientHandler object
     *
     * @param s data input stream used to communicate with the server
     * @param dis data output stream used to communicate with the server
     * @param dos socket used to communicate with the server
     */

    public ProfileClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    /**
     * Runs functionality for the server
     *
     * Current cases: AddProfile, DeleteProfile, SendProfile, SendAccount, AddAccount, DeleteAccount,
     * AddInterest, RemoveInterest, AddFriend, RemoveFriend, EditEducation, EditEmail, EditPhoneNumber, EditAboutMe
     */

    @Override
    public void run() {

        //Initialize variables common across multiple cases

        String command; //command from the client to access functionality
        String username; //username passed from client
        String email; //email passed from the client
        String password; //password passed from client
        String parameter; // generic parameter passed by the client
        boolean objectFound; //whether or not an object was found for a search (usually profile or account)
        Profile profile; //profile sent by the client
        ArrayList<Profile> profiles; //profile arraylist sent by client

        try {
            while (true) {

                switch(command = (String) dis.readObject()) { //look for command sent from client

                    //TODO Insert Cases for various functions here!

                    case "AddProfile": //adds a profile to an account based on account email

                        //read in and initialize variables
                        email = (String) dis.readObject();
                        profile = (Profile) dis.readObject();
                        objectFound = false;

                        for (int i = 0; i < accounts.size(); i++) { //search the accounts for a matching email

                            if (accounts.get(i).getEmail().equals(email)) { //if account matches, add passed profile
                                accounts.get(i).addProfile(profile);
                                dos.writeObject("True");
                                objectFound = true;
                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                        break;

                    case "DeleteProfile": //Deletes a profile based on username

                        //read in and initialize variables
                        username = (String) dis.readObject();
                        objectFound = false;

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).deleteProfile(username);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                        break;

                    case "SendProfile": //Sends a profile specific by username to the client.

                        //read in and initialize variables
                        objectFound = false;
                        username = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    dos.writeObject(accounts.get(i).getProfiles().get(j));
                                    objectFound = true;
                                }
                            }
                        }

                        if (!objectFound) {
                            dos.writeObject("False");
                        }

                        break;

                    case "AddAccount": //Creates an account and adds it to the Account Master ArrayList

                        email = (String) dis.readObject();
                        password = (String) dis.readObject();
                        profiles = (ArrayList<Profile>) dis.readObject();

                        accounts.add(new Account(email, password, profiles));

                        dos.writeObject("True");

                        break;

                    case "DeleteAccount": //Deletes an account from the Account Master Arraylist

                        objectFound = false;
                        email = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts for a matching email

                            if (accounts.get(i).getEmail().equals(email)) {

                                dos.writeObject("True");
                                accounts.remove(i);
                                objectFound = true;
                            }
                        }

                        if (!objectFound) {
                            dos.writeObject("False");
                        }

                        break;

                    case "SendAccount":

                        objectFound = false;
                        email = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts for a matching email

                            if (accounts.get(i).getEmail().equals(email)) {

                                dos.writeObject("True");
                                dos.writeObject(accounts.get(i).getPassword());
                                dos.writeObject(accounts.get(i).getProfiles());

                                objectFound = true;
                            }
                        }

                        if (!objectFound) {
                            dos.writeObject("False");
                        }

                        break;

                    case "AddInterest": //Adds an interest to a profile

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).getProfiles().get(j).addInterest(parameter);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                        break;

                    case "RemoveInterest": //Removes an interest from a profile

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) { //if profile found

                                    for (int k = 0; k < accounts.get(i).getProfiles().get(j).getInterests().size(); k++) { //search interests

                                        if (accounts.get(i).getProfiles().get(j).getInterests().get(k).equals(parameter)) { //if interest found
                                            accounts.get(i).getProfiles().get(j).getInterests().remove(k);
                                            objectFound = true;
                                        }
                                    }
                                }
                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                        break;

                    case "EditEducation":

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).getProfiles().get(j).setEducation(parameter);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                    case "EditEmail":

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).getProfiles().get(j).setEmail(parameter);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                    case "EditPhoneNumber":

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).getProfiles().get(j).setPhoneNumber(Long.parseLong(parameter)); //TODO change phone data type (?)
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                    case "EditAboutMe":

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).getProfiles().get(j).setAboutMe(parameter);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                    case "AcceptFriend": //Accepts a friend request

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(parameter)) {
                                    dos.writeObject("True");
                                    accounts.get(i).getProfiles().get(j).addFriend(username);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                            break;
                        }

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    accounts.get(i).getProfiles().get(j).addFriend(parameter);
                                }

                            }
                        }

                    case "RemoveFriend": //removes a friend from a profile

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) { //if profile found

                                    for (int k = 0; k < accounts.get(i).getProfiles().get(j).getFriends().size(); k++) { //search friends

                                        if (accounts.get(i).getProfiles().get(j).getFriends().get(k).equals(parameter)) { //if friend found
                                            accounts.get(i).getProfiles().get(j).getFriends().remove(k);
                                            objectFound = true;
                                        }
                                    }
                                }
                            }
                        }

                        if (!objectFound) { //if account not found, send back false
                            dos.writeObject("False");
                        }

                        break;

                    case "SendRequest":

                        objectFound = false;
                        username = (String) dis.readObject();
                        parameter = (String) dis.readObject();

                        //add received request
                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) { //if profile found
                                    accounts.get(i).getProfiles().get(j).addReceivedRequest(parameter);
                                    objectFound = true;
                                }
                            }
                        }

                        if (!objectFound) {
                            dos.writeObject("False");
                            break;
                        }

                        //add sent request
                        for (int i = 0; i < accounts.size(); i++) { //search accounts

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) { //search profiles

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) { //if profile found
                                    accounts.get(i).getProfiles().get(j).addSentRequest(username);
                                }
                            }
                        }

                        dos.writeObject("True");

                    case "Decline":
                        /*must remove username A from the list of pending friend requests of
                         * profile B and remove username B from the list of sent requests for
                         * profile A
                         *
                         * Receives: "Decline", then username     Sends: True or False
                         * */

                    case "Rescind":
                        /*must remove username A from the list of sent requests of Profile B and
                         * remove username B from the list of pending friend requests received
                         * by Profile A
                         *
                         * Receives: "Rescind", then username     Sends: True or False
                         * */

                    default: //if no cases match the command
                        System.out.println("You sent to the server, but didn't match a case :(");

                }

            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: There was a connection issue",
                    "Campsgram", JOptionPane.ERROR_MESSAGE);
        }

        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: There was a connection issue",
                    "Campsgram", JOptionPane.ERROR_MESSAGE);
        }
    }
}
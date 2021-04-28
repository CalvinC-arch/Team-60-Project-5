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
     */

    @Override
    public void run() {

        String command; //command from the client to access functionality
        String username; //username passed from client
        String email; //email passed from the client
        boolean objectFound; //whether or not an object was found for a search (usually profile or account)
        Profile profile; //profile sent by the client

        System.out.println("A new client handler is operational");

        try {
            while (true) {

                switch(command = (String) dis.readObject()) {

                    //TODO Insert Cases for various functions here!

                    case "AddProfile":

                        email = (String) dis.readObject();
                        profile = (Profile) dis.readObject();
                        objectFound = false;

                        for (int i = 0; i < accounts.size(); i++) {

                            if (accounts.get(i).getEmail().equals(email)) {
                                accounts.get(i).addProfile(profile);
                                dos.writeObject("True");
                                objectFound = true;
                            }
                        }

                        if (!objectFound) {
                            dos.writeObject("False");
                        }

                        break;

                    case "DeleteProfile": //Deletes a profile based on username

                        username = (String) dis.readObject();
                        objectFound = false;

                        for (int i = 0; i < accounts.size(); i++) {

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) {

                                if (accounts.get(i).getProfiles().get(j).getUsername().equals(username)) {
                                    dos.writeObject("True");
                                    accounts.get(i).deleteProfile(username);
                                    objectFound = true;
                                }

                            }
                        }

                        if (!objectFound) {
                            dos.writeObject("False");
                        }

                        break;

                    case "SendProfile": //Sends a profile specific by username to the client.

                        objectFound = false;
                        username = (String) dis.readObject();

                        for (int i = 0; i < accounts.size(); i++) {

                            for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) {

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

                    default:
                        System.out.println("You sent to the server, but didn't match a case");

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
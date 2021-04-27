import javax.swing.*;
import java.io.*;
import java.net.*;

/**
 * ProfileClientHandler
 *
 * This class contains the server side processing and user interface logic for the project. It is created from a thread
 * created by the ProfileServer class. The constructor allows multiple objects of this type to be created, allowing for
 * multiple clients to connect to the server
 *
 * Geeksforgeeks was referenced to determine how to utilize threads for mutliple client-server connections
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

class TestClientHandler extends Thread {

    final ObjectInputStream dis; //data input stream used to communicate with the server
    final ObjectOutputStream dos; //data output stream used to communicate with the server
    final Socket s; //socket used to communicate with the server

    /**
     * Creates a new ProfileClientHandler object
     *
     * @param s data input stream used to communicate with the server
     * @param dis data output stream used to communicate with the server
     * @param dos socket used to communicate with the server
     */

    public TestClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos) {
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

        String command;
        System.out.println("A new client handler is operational");

        try {
            while (true) {

                command = (String) dis.readObject();
                System.out.println(command);

                switch(command) {

                    case "View": //Client wants to view profile

                        //THIS IS A TEST FOR NETWORK I/O
                        System.out.println("In View");

                        String code = (String) dis.readObject();
                        System.out.println("Code is: " + code);

                        if (code.equals("True")) {
                            dos.writeObject("True");
                        } else {
                            dos.writeObject("False");
                        }

                        System.out.println("Returned");

                        //END OF TEST

                        break;

                    case "CreateProfile": //Client wants to create profile

                        System.out.println("CreateProfile");

                        break;

                    case "ObjectTest": //TESTING HOW OBJECTS WORK

                        TestingProfile profile = (TestingProfile) dis.readObject();

                        profile.setAboutMe("The object thing worked");
                        System.out.println("About me changed!");

                        dos.writeObject(profile);

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
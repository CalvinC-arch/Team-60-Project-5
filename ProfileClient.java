import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ProfileClient
 *
 * The .java file that the a user will run to connect to the server and access the social media functionality. It
 * contains a main method that automatically connects the user to the server.
 *
 * N/A
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

public class ProfileClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        boolean check = false; //used to determine whether a connection to the server has been achieved

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection with server port 1234
        Socket s = new Socket(ip, 1234);   //CHANGE FOR USE WITH UNITY TO 5555

        // obtaining input and out streams
        ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

        try { //connect to server and set up network io, display error message if connection unsuccessful
            while(!check) {
                Socket socket = new Socket("localhost", 1234);   //CHANGE FOR USE WITH UNITY TO 5555

                check = true;
            }

        } catch (IOException e) { //display error message is connection not established
            JOptionPane.showMessageDialog(null, "Error: Connection Cannot be Established",
                    "Campsgram", JOptionPane.ERROR_MESSAGE);

            return;
        }

        //Confirm connection successful
        JOptionPane.showMessageDialog(null, "Connection Established", "CampsGram",
                JOptionPane.INFORMATION_MESSAGE);

        //creates an IOMachine to easily access the server
        IOMachine ioMachine = new IOMachine(dos, dis);

        //Runs the Login GUI
        LoginPageGUI Login = new LoginPageGUI(ioMachine);
        Login.run();

        //TEST SECTION
        //This gets the profile passed from the server and reads the aboutMe section, then deletes the profile, then
        //attempts to receive the profile, then adds the profile back, then reads the profile and aboutMe again. Shows
        //that the cases and network IO function. Can leave out the adding to show that profile deletion persists
        //past client disconnect

        //GETTING PROFILE SENT
        //Write command and username to server
        dos.writeObject("SendProfile");
        dos.writeObject("ccarta");

        //read whether the profile was found or not
        String result = (String) dis.readObject();

        if (result.equals("True")) {

            //read profile and display about me section
            Profile profileSent = (Profile) dis.readObject();
            System.out.println(profileSent.getAboutMe());

        } else {

            System.out.println("No profile found");

        }

        //DELETING PROFILE
        //Write command and username to server
        dos.writeObject("DeleteProfile");
        dos.writeObject("ccarta");

        //read whether the profile was found or not
        result = (String) dis.readObject();

        if (result.equals("True")) {
            System.out.println("Profile Deleted!");
        } else {
            System.out.println("No profile found");
        }

        //GETTING PROFILE SENT
        //Write command and username to server
        dos.writeObject("SendProfile");
        dos.writeObject("ccarta");

        //read whether the profile was found or not
        result = (String) dis.readObject();

        if (result.equals("True")) {

            //read profile and display about me section
            Profile profileSent = (Profile) dis.readObject();
            System.out.println(profileSent.getAboutMe());

        } else {

            System.out.println("No profile found");

        }

        //ADDING PROFILE
        //Write command, email, and profile to add to server
        dos.writeObject("AddProfile");
        dos.writeObject("ccarta@purdue.edu");
        dos.writeObject(new Profile("testProfile.csv"));

        //read whether the profile was found or not
        result = (String) dis.readObject();

        if (result.equals("True")) {
            System.out.println("Profile Added!");
        } else {
            System.out.println("Profile not able to be added");
        }

        //GETTING PROFILE SENT
        //Write command and username to server
        dos.writeObject("SendProfile");
        dos.writeObject("ccarta");

        //read whether the profile was found or not
        result = (String) dis.readObject();

        if (result.equals("True")) {

            //read profile and display about me section
            Profile profileSent = (Profile) dis.readObject();
            System.out.println(profileSent.getAboutMe());

        } else {

            System.out.println("No profile found");

        }

        //RECEIVING ACCOUNT PROFILES

        dos.writeObject("SendAccountProfiles");
        dos.writeObject("ccarta@purdue.edu");

        result = (String) dis.readObject();

        if (result.equals("True")) {

            ArrayList<Profile> profiles = (ArrayList<Profile>) dis.readObject();
            System.out.println(profiles.size());

        } else {

            System.out.println("No profiles found");

        }

        //ADDING ACCOUNT

        dos.writeObject("CreateAccount");
        dos.writeObject("btac1000@gmail.com");
        dos.writeObject("bananas");

        ArrayList<Profile> profiles = null;
        dos.writeObject(profiles);

        result = (String) dis.readObject();

        if (result.equals("True")) {

            System.out.println("Account added!");

        } else {

            System.out.println("Account not able to be added");

        }

        //DELETING AN ACCOUNT

        dos.writeObject("DeleteAccount");
        dos.writeObject("btac1000@gmail.com");

        result = (String) dis.readObject();

        if (result.equals("True")) {

            System.out.println("Account deleted!");

        } else {

            System.out.println("Account not able to be deleted");

        }

        //END OF TEST SECTION

    }
}

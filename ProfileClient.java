import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

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
        Socket s = new Socket(ip, 1234);

        // obtaining input and out streams
        ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

        try { //connect to server and set up network io, display error message if connection unsuccessful
            while(!check) {
                Socket socket = new Socket("localhost", 1234);

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                check = true;
            }

        } catch (IOException e) { //display error message is connection not established
            JOptionPane.showMessageDialog(null, "Error: Connection Cannot be Established",
                    "Campsgram", JOptionPane.ERROR_MESSAGE);
        }

        //Confirm connection successful
        JOptionPane.showMessageDialog(null, "Connection Established", "CampsGram",
                JOptionPane.INFORMATION_MESSAGE);

        //TODO GUI stuff goes here!

    }
}
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
    public static void main(String[] args) throws IOException {

        boolean check = false; //used to determine whether a connection to the server has been achieved

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

    }
}

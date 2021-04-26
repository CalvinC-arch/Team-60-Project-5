import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 * ClientTest
 *
 * This exists to test network IO
 *
 * THIS IS A TEST FILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
 *
 * N/A
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

public class TestClient {
    public static void main(String[] args) throws IOException {

        boolean check = false; //used to determine whether a connection to the server has been achieved
        Scanner scanner = new Scanner(System.in);

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection with server port 5056
        Socket s = new Socket(ip, 1234);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

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


        //TODO client side functionality, listen for GUI stuff and display GUI stuff

        //Test sending a command to the server
        System.out.println("Enter a command (use View for testing): ");
        String command = scanner.nextLine();

        dos.writeUTF(command);

        System.out.println("Command sent");

        dos.writeUTF("True");

        System.out.println("Code sent");

        String reply = dis.readUTF();

        System.out.println("Reply received");

        System.out.println(reply);

        if (reply.equals("True")) {
            System.out.println("Success");
        }
        //END OF TEST

    }
}

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

public class TestClientTwo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        boolean check = false; //used to determine whether a connection to the server has been achieved
        Scanner scanner = new Scanner(System.in);

        // getting localhost ip
        InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection with server port 4321
        Socket s = new Socket(ip, 4321);

        System.out.println("output and input streams reached, client");

        // obtaining input and output object streams
        ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream dis = new ObjectInputStream(s.getInputStream());


        System.out.println("output and input streams made, client");

        try { //connect to server and set up network io, display error message if connection unsuccessful
            while(!check) {
                Socket socket = new Socket("localhost", 4321);

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

        //Test sending a command to the server
        System.out.println("Enter a command (View first, ObjectTest second): ");
        String command = scanner.nextLine();

        dos.writeObject(command);

        System.out.println("Command sent");

        dos.writeObject("True");

        System.out.println("Code sent");

        String reply = (String) dis.readObject();

        System.out.println("Reply received");

        System.out.println(reply);

        if (reply.equals("True")) {
            System.out.println("Success");
        }

        TestingProfile profile = new TestingProfile("testProfile.csv");
        System.out.println(profile.getAboutMe());

        System.out.println("Enter a command (View first, ObjectTest second): ");
        command = scanner.nextLine();

        dos.writeObject(command);

        dos.writeObject(profile);

        profile = (TestingProfile) dis.readObject();

        System.out.println(profile.getAboutMe());
        //END OF TEST

    }
}
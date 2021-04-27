import java.io.*;
import java.net.*;

/**
 * ProfileServer
 *
 * This class consists of a main method that manages server connections. The main logic loops constantly to detect new
 * client connections. When a client connects, this class creates a new ProfileClientHandler object that is then used
 * to interface with the client.
 *
 * Geeksforgeeks was referenced to determine how to utilize threads for mutliple client-server connections
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

//Class uses port 1234
public class TestingServer
{
    public static void main(String[] args) throws IOException
    {

        ServerSocket ss = new ServerSocket(4321); //server socket that the server uses to connect to clients

        while (true) //loops indefinitely so that the server is always responsive to new connections
        {
            Socket s = null; //socket used by the client

            try
            {

                s = ss.accept();

                //confirm a client is connected, and outputs the socket information
                System.out.println("A new client is connected : " + s);

                System.out.println("output and input streams reached, server");

                //Create new input and output stream to communicate with client
                ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream dis = new ObjectInputStream(s.getInputStream());


                System.out.println("output and input streams made, server");

                //Create new thread to handle client
                Thread t = new TestClientHandler(s, dis, dos);
                t.start();

            }
            catch (Exception e){ //close socket if an exception occurs
                s.close();
            }
        }
    }
}

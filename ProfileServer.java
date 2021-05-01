import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * ProfileServer
 *
 * This class consists of a main method that manages server connections. The main logic loops constantly to detect new
 * client connections. When a client connects, this class creates a new ProfileClientHandler object that is then used
 * to interface with the client.
 *
 * Geeksforgeeks was referenced to determine how to utilize threads for multiple client-server connections
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

//Class uses port 1234
public class ProfileServer
{

    private static ArrayList<Account> accounts = new ArrayList<>(); //Account Master ArrayList

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static void main(String[] args) throws IOException
    {

        //Create server socket on port 1234
        ServerSocket ss = new ServerSocket(1234); //server socket that the server uses to connect to clients

        ArrayList<String> serverBackup = new ArrayList<>(); //Arraylist of all the lines in the server backup
        ArrayList<Profile> profiles = new ArrayList<>(); //Arraylist of profiles associated with the account
        String[] splitLine; //Array containing the line, split by commas
        String line = "banana"; //The line from the .csv server backup file, initialized as a random word

        try {
            //Read in file using correct filename using new bufferedReader
            File f = new File("ServerBackupFile.csv");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            //reads in the line from the file
            while (!(line == null)) {

                line = bfr.readLine();

                if (!(line == null)) {
                    serverBackup.add(line);
                }
            }

            bfr.close();

            for (int i = 0; i < serverBackup.size(); i++) {

                splitLine = serverBackup.get(i).split(",");

                for (int j = 2; j < splitLine.length; j++) {

                    profiles.add(new Profile(splitLine[j] + "Export.csv"));

                }

                accounts.add(new Account(splitLine[0], splitLine[1], profiles));

                System.out.println("Server reset from backup");
            }

        } catch (IOException e) { //if IO Exception, likely due to incorrect file name
            System.out.println("There was no server backup");
        }

        while (true) //loops indefinitely so that the server is always responsive to new connections
        {
            Socket s = null; //socket used by the client

            try
            {

                s = ss.accept();

                //confirm a client is connected, and outputs the socket information
                System.out.println("A new client is connected : " + s);

                //Create new input and output stream to communicate with client
                ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
                ObjectInputStream dis = new ObjectInputStream(s.getInputStream());

                //Create new thread to handle client
                Thread t = new ProfileClientHandler(s, dis, dos);
                t.start();

            }
            catch (Exception e){ //close socket if an exception occurs

                File f = new File("ServerBackupFile.csv");
                PrintWriter pw = new PrintWriter(new FileWriter(f, false));

                for(int i = 0; i < accounts.size(); i++) {

                    pw.print(accounts.get(i).getEmail() + ",");
                    pw.print(accounts.get(i).getPassword());

                    for (int j = 0; j < accounts.get(i).getProfiles().size(); j++) {
                        accounts.get(i).getProfiles().get(j).writeExportFile();
                        pw.print("," + accounts.get(i).getProfiles().get(j).getUsername());
                    }

                    pw.println();

                    pw.close();
                }

                s.close();

            }
        }
    }
}
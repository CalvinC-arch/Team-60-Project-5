import java.io.*;
import java.net.*;

// Server class
public class ProfileServer
{
    public static void main(String[] args) throws IOException
    {

        ServerSocket ss = new ServerSocket(1234);

        while (true)
        {
            Socket s = null;

            try
            {

                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                //Create new thread to handle client
                Thread t = new ProfileClientHandler(s, dis, dos);
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}

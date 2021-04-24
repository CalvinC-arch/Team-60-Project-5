import javax.swing.*;
import java.io.*;
import java.net.*;

class ProfileClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // Constructor
    public ProfileClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {

        //TODO insert functionality here

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

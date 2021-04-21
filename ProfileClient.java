import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProfileClient {
    public static void main(String[] args) throws IOException {
        String hostName = JOptionPane.showInputDialog(null, "Enter the host name", "CampsGram",
                JOptionPane.QUESTION_MESSAGE);

        int i = 0;
        String portNum;
        do {
            portNum = JOptionPane.showInputDialog(null, "Enter the port number", "CampsGram",
                    JOptionPane.QUESTION_MESSAGE);
            if (!(portNum.matches("\\d+"))) {
                JOptionPane.showMessageDialog(null, "Enter a valid port number", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                i++;
            }
        } while (i == 0);

        Socket socket = new Socket(hostName, Integer.parseInt(portNum));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        JOptionPane.showMessageDialog(null, "Connection Established", "CampsGram",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

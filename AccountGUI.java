import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountGUI implements Runnable{

    private String accountName;
    private int numProfiles; //how many profiles the account owns

    public AccountGUI(String name, int numProfiles) {
        this.accountName = name;
        this.numProfiles = numProfiles;
    }


    public void run() {
        JFrame frame = new JFrame("Account: " + accountName);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel topBar = new JPanel();
        JButton deleteAcc = new JButton("Delete");
        JButton addProfile = new JButton("Add Profile");


        JPanel mainPanel = new JPanel(new GridLayout(numProfiles, 1));

        //TODO Network I/O for profiles
        for(int i = 0; i < numProfiles; i++) {
            System.out.println(i);
            JPanel profilePanel = new JPanel();
            JTextField profileName = new JTextField("Derek");
            JButton view = new JButton("View");
            JButton delete = new JButton("Delete");

            profilePanel.add(profileName);
            profilePanel.add(view);
            profilePanel.add(delete);

            mainPanel.add(profilePanel);
        }

        frame.add(topBar, BorderLayout.NORTH);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add functionality to buttons

        }
    };
}

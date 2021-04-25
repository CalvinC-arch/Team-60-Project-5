import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountGUI implements Runnable{

    private String accountName;
    private ArrayList<ProfileGUI> profiles; //how many profiles the account owns

    public AccountGUI(String name, ArrayList<ProfileGUI> profiles) {
        this.accountName = name;
        this.profiles = profiles;
    }

    public void run() {
        JFrame frame = new JFrame("Account: " + accountName);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel topBar = new JPanel();
        JButton deleteAcc = new JButton("Delete");
        deleteAcc.addActionListener(topBarListener);
        JButton addProfile = new JButton("Add Profile");
        addProfile.addActionListener(topBarListener);


        JPanel mainPanel = new JPanel(new GridLayout(profiles.size() / 2 + 1, 2));

        //TODO Network I/O for profiles
        for(int i = 0; i < profiles.size(); i++) {
            System.out.println(i);
            JPanel profilePanel = new JPanel();
            JTextArea profileName = new JTextArea(profiles.get(i).getName());
            JButton view = new JButton("View");
            view.addActionListener(profileListener);
            view.setActionCommand("view" + i);
            JButton delete = new JButton("Delete");
            delete.addActionListener(profileListener);
            delete.setActionCommand("delete" + i);

            profilePanel.add(profileName);
            profilePanel.add(view);
            profilePanel.add(delete);

            mainPanel.add(profilePanel);
        }

        frame.add(topBar, BorderLayout.NORTH);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    ActionListener topBarListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add functionality to buttons

        }
    };

    ActionListener profileListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().contains("view")) {
                int profileIndex = Integer.parseInt(e.getActionCommand().substring(4));
                profiles.get(profileIndex).run();
            } else if(e.getActionCommand().contains("delete")){
                int profileIndex = Integer.parseInt(e.getActionCommand().substring(4));
                profiles.remove(profileIndex);
                //TODO figure out how to remove the panel from GUI
            } else {
                System.out.println("failure");

            }
        }
    };
}

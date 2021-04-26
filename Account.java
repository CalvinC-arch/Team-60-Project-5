import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
AccountGUI useful methods:

AccountGUI(String name, ArrayList<Franken> profiles)
  - name is name of the account
  - profiles is a list of profiles under the account

.run() ~ runs the GUI for an AccountGUI object

Creators: Will Stonebridge
 */

public class Account {
    private final String email;
    private final String password;
    private ArrayList<Profile> profiles;

    //frame and panels
    ArrayList<JPanel> panelList;
    JFrame frame;
    JPanel bottomBar;
    JPanel mainPanel;

    //bottom bar buttons
    JButton deleteAcc; //deletes the account
    JButton addProfile; //creates a new profile when pushed

    public Account(String email, String password, ArrayList<Profile> profiles) throws NullPointerException {
        this.email = email;
        this.password = password;
        this.profiles = profiles;
    }
    
    

    public void run() {
        //Creates the frame
        frame = new JFrame("Account: " + email);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Sets up the bottom bar and its buttons
        bottomBar = new JPanel();
        deleteAcc = new JButton("Delete");
        deleteAcc.addActionListener(bottomBarListener);
        addProfile = new JButton("Add Profile");
        addProfile.addActionListener(bottomBarListener);
        bottomBar.add(deleteAcc);
        bottomBar.add(addProfile);

        //The profiles will be laid out like a grid with 2 columns
        mainPanel = new JPanel(new GridLayout(profiles.size() / 2 + 1, 2));

        //TODO Network I/O for profiles
        for(int i = 0; i < profiles.size(); i++) {

            //Creates a panel with a view and delete button
            //The buttons will be assigned the action commands with either
            //the word "view" or "delete" followed by their profiles index
            //in the profile arraylist
            JPanel profilePanel = new JPanel();
            JLabel profileName = new JLabel(profiles.get(i).getName());
            JButton view = new JButton("View");
            view.addActionListener(profileListener);
            view.setActionCommand("view" + i);
            JButton delete = new JButton("Delete");
            delete.addActionListener(profileListener);
            delete.setActionCommand("delete" + i);
            profilePanel.add(profileName);
            profilePanel.add(view);
            profilePanel.add(delete);
            panelList.add(profilePanel);

            //adds the profile panel to the main panel
            mainPanel.add(profilePanel);
        }

        //adds the main panel and bottom bar to the frame
        frame.add(mainPanel);
        frame.add(bottomBar, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    ActionListener bottomBarListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add functionality to buttons
            if(e.getSource() == deleteAcc) {
                //TODO add delete Acc functionality
            }
            if(e.getSource() == addProfile) {
                //Uses EnterInfo GUI to create fields for a profile
                String name = EnterInfoGUI.showNameInputDialog();
                ArrayList<String> interests = new ArrayList<>(); //TODO
                ArrayList<String> friends = new ArrayList<>();
                String education = EnterInfoGUI.showEducationInputDialog();
                String email = EnterInfoGUI.showEmailInputDialog();
                String phone = EnterInfoGUI.showPhoneInputDialog();
                String aboutMe = EnterInfoGUI.showAboutInputDialog();

                //creates a new profile and adds it to the array list of profiles
                Profile profile = new Profile(name, interests, friends, education, email, phone, aboutMe);
                profiles.add(profile);

                //TODO add updating functionality
                mainPanel.remove(panelList.get(profileIndex));

            }
        }
    };

    ActionListener profileListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {


            if (e.getActionCommand().contains("view")) //Runs if the received action command contains view
            {
                /*retrieves the integer in the action command, which represents the index of the profile in the
                profiles array*/
                int profileIndex = Integer.parseInt(e.getActionCommand().substring(4));

                //runs the GUI for the given profile
                profiles.get(profileIndex).run();
            }
            else if (e.getActionCommand().contains("delete")) //Runs if the received action command contains delete
            {
                /*retrieves the integer in the action command, which represents the index of the profile in the
                profiles array*/
                int profileIndex = Integer.parseInt(e.getActionCommand().substring(4));

                //removes the profile from the arraylist of profiles
                profiles.remove(profileIndex);

                //TODO figure out how to remove the panel from GUI
            }
        }
    };

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public ArrayList<Profile> getProfiles() {
        return this.profiles;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }
}

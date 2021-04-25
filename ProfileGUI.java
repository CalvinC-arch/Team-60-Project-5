import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfileGUI implements Runnable{

    private String name; //name of the user
    private String phone; //phone of the user
    private String email; //email of the user
    private String interests; //an array of the users interests

    //top elements
    JButton edit; //edit button
    JButton requests; //requests button

    //bottom elements
    JTextField searchBox; //text box for searching users
    JTextField addBox; //text box for adding users
    JButton search; //search button
    JButton add; //add user button

    //Text Boxes
    JLabel nameText;
    JLabel phoneText;
    JLabel emailText;
    JLabel interestsText;


    //TODO make more extensive
    public ProfileGUI(String name, String phone, String email, String interests)
    {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.interests = interests;

    }

    //for testing purposes
    public ProfileGUI()
    {
        this.name = "zeke";
        this.phone = "1234567890";
        this.email = "EmailOrSomething";
        this.interests = "sldkfjsdklfjds";


    }

    public void run() {
        JFrame frame = new JFrame();
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //top panel
        JPanel topPanel = new JPanel();
        edit = new JButton("Edit Profile");
        edit.addActionListener(actionListener);
        topPanel.add(edit);
        requests = new JButton("View Friend Requests");
        requests.addActionListener(actionListener);
        topPanel.add(requests);
        frame.add(topPanel, BorderLayout.NORTH);

        //bottom panel
        JPanel bottomPanel = new JPanel();
        searchBox = new JTextField(5);
        bottomPanel.add(searchBox);
        search = new JButton("Search User");
        search.addActionListener(actionListener);
        bottomPanel.add(search);
        addBox = new JTextField(5);
        bottomPanel.add(addBox);
        add = new JButton("Add Friend");
        add.addActionListener(actionListener);
        bottomPanel.add(add);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        //Everything below is for the center panel which holds user info
        //TODO fill the panels

        //name panel
        JPanel namePanel = new JPanel();
        nameText = new JLabel("USER: " + this.name);
        namePanel.add(nameText);

        //phone panel
        JPanel phonePanel = new JPanel();
        phoneText = new JLabel("PHONE: " + this.phone);
        phonePanel.add(phoneText);

        //email panel
        JPanel emailPanel = new JPanel();
        emailText = new JLabel("EMAIL: " + this.email);
        emailPanel.add(emailText);

        //interests panel
        JPanel interestsPanel = new JPanel();
        interestsText = new JLabel("INTERESTS:\n" + interests);
        interestsPanel.add(interestsText);

        //Combines the above three panels in grid layout
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(2, 2));
        profilePanel.add(namePanel);
        profilePanel.add(phonePanel);
        profilePanel.add(emailPanel);
        profilePanel.add(interestsPanel);
        frame.add(profilePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add I/O aspects to edit
            if(e.getSource() == edit) {
                name = EnterInfoGUI.showNameInputDialog();
                email = EnterInfoGUI.showEmailInputDialog();
                phone = EnterInfoGUI.showPhoneInputDialog();
                interests = EnterInfoGUI.showInterestsInputDialog();

                nameText.setText(name);
                emailText.setText(email);
                phoneText.setText(phone);
                interestsText.setText(interests);
            }
            if(e.getSource() == requests) {
                new FriendsListGUI().run();
            }
            //TODO Utilize data from bottom panel I/O
            if(e.getSource() == search) {
                String profileSearch = searchBox.getText(); //the profile name entered by the user
                searchBox.setText("");
            }
            if(e.getSource() == add) {
                String profileAdd = addBox.getText(); //the profile name entered by the user
                addBox.setText("");
            }
        }
    };

    public String getName() {
        return this.name;
    }
}

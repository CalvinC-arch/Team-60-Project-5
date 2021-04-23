import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfileGUI implements Runnable{

    private String name; //name of the user
    private long phone; //phone of the user
    private String email; //email of the user
    private ArrayList<String> interests; //an array of the users interests

    //top elements
    JButton edit; //edit button
    JButton delete; //delete button
    JButton requests; //requests button

    //bottom elements
    JTextField searchBox; //text box for searching users
    JTextField addBox; //text box for adding users
    JButton search; //search button
    JButton add; //add user button

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ProfileGUI());
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
        delete = new JButton("Edit Profile");
        delete.addActionListener(actionListener);
        topPanel.add(delete);
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
        JPanel namePanel = new JPanel();
        JTextArea name = new JTextArea("USER: ");
        namePanel.add(name);

        JPanel contactPanel = new JPanel();
        JTextArea contact = new JTextArea("PHONE: EMAIL:");
        contactPanel.add(contact);

        JPanel interestsPanel = new JPanel();
        JTextArea interests = new JTextArea("INTERESTS: ");
        interestsPanel.add(interests);

        //Combines the above three panels in grid layout
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(3, 1));
        profilePanel.add(namePanel);
        profilePanel.add(contactPanel);
        profilePanel.add(interestsPanel);
        frame.add(profilePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add functionality to top panel buttons
            if(e.getSource() == edit) {

            }
            if(e.getSource() == delete) {

            }
            if(e.getSource() == requests) {

            }
            //TODO Utilize data from bottom panel
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
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

/*
Profile useful methods:

Profile(String name, ArrayList<String> interests, ArrayList<String> friends, String education,
        String email, String phone, String aboutMe)
  - constructor with all fields

Profile()
  - constructor for a test profile object (has prefilled fields)

.run() ~ runs the GUI for an profile object

Creators: Will, Eric
 */

public class Profile implements Runnable{

    //Profile Info
    private String name;
    private ArrayList<String> interests;
    private ArrayList<String> friends;
    private String education;
    private String email;
    private String phone;
    private String aboutMe;

    //Frames and Panels
    JFrame frame;
    JPanel topPanel;
    JPanel bottomPanel;

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
    JLabel educationText;
    JLabel aboutMeText;
    JLabel interestsText;

    public Profile(String name, ArrayList<String> interests, ArrayList<String> friends, String education,
                   String email, String phone, String aboutMe) {

        this.name = name;
        this.interests = interests;
        this.friends = friends;
        this.education = education;
        this.email = email;
        this.phone = phone;
        this.aboutMe = aboutMe;
    }

    public Profile(String filename) throws IOException {

        String profile = null; //line of profile info from .csv
        String[] profileInfo; //array of profile info from .csv
        int listLength; //length interests and friends arrays
        int index = 0; //index of profileInfo
        int tempIndex; //holds index that is reached before appending to a list
        boolean fileRead = false;

        //initialize new interests and friends lists so they can be added to
        this.interests = new ArrayList<>();
        this.friends = new ArrayList<>();

        //read from file, runs while the boolean indicating the file has been read is false
        while (!fileRead) {

            try {
                //Read in file using correct filename using new bufferedReader
                File f = new File(filename);
                FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr);

                //reads in the line from the file
                profile = bfr.readLine();

                bfr.close();

                //change fileRead to true to exit loop
                fileRead = true;

            } catch (IOException e) { //if IO Exception, likely due to incorrect file name

                //display error message and ask for new filename for next loop iteration
                JOptionPane.showMessageDialog(null, "Not a valid file name!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE);

                filename = JOptionPane.showInputDialog(null, "Enter a valid filename: ",
                        "CampsGram", JOptionPane.QUESTION_MESSAGE);
            }
        }

        //split the .csv by commas into a string array for data processing
        profileInfo = profile.split(",");

        //set name to first index in array and iterate the index counter
        this.name = profileInfo[index];
        index++;

        //find length of interests list and iterate the index counter
        listLength = Integer.parseInt(profileInfo[index]);
        index++;

        //set tempIndex to index for loop iterate
        tempIndex = index;

        //loop from current index to index at start of loop + the number of items in the loop to ensure all list items
        //are found
        while (index < listLength + tempIndex) {
            interests.add(profileInfo[index]);
            index++;
        }

        //repeat the same process as previous for the friends arraylist
        listLength = Integer.parseInt(profileInfo[index]);
        index++;

        tempIndex = index;

        while (index < listLength + tempIndex) {
            friends.add(profileInfo[index]);
            index++;
        }

        //set fields to indexes from the .csv as appropriate for the remaining fields
        this.education = profileInfo[index];
        index++;

        this.email = profileInfo[index];
        index++;

        this.phone = profileInfo[index];
        index++;

        this.aboutMe = profileInfo[index] + ",";
        index++;

        //loop through the rest of the indexes in profileInfo and append to the aboutMe section with a comma
        //to account for user entered commas in the about me section
        while (index < profileInfo.length) {
            this.aboutMe = this.aboutMe + profileInfo[index] + ", ";
            index++;
        }

        //remove last two characters, as the aboutMe loop will leave a comma and space at the end of the process
        this.aboutMe = this.aboutMe.substring(0, this.aboutMe.length() - 2);
    }

    //TODO delete test constructor
    public Profile() {
        this.name = "zeke";
        this.phone = "1234567890";
        this.email = "EmailOrSomething";
        this.education = "Purdue";
        this.aboutMe = "Hola, yo estoy zeke";
        this.interests = new ArrayList<>();

    }

    public void writeExportFile() throws IOException {

        try {
            //creating new file and printWriter for this profile
            File f = new File(this.name + "Export.csv");
            PrintWriter pw = new PrintWriter(new FileWriter(f, false));
            pw.print(this.name + ',' + interests.size() + ',');

            //loop to write each element of interests list to file
            for (int i = 0; i < interests.size(); i++) {
                pw.print(this.interests.get(i) + ',');
            }

            pw.print(this.friends.size());
            pw.print(",");

            //loop to write each element of friends list to file
            for (int x = 0; x < friends.size(); x++) {
                pw.print(friends.get(x) + ',');
            }

            pw.print(this.education + ',' + this.email + ',' + this.phone + ',' + this.aboutMe);
            pw.close();

        } catch (IOException e) { //If the file is open or another error occurs, display this error message
            JOptionPane.showMessageDialog(null, "There was an error exporting your file, please " +
                    "close the file if it is open on your device", "CamsGram", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void run() {
        frame = new JFrame(name);
        frame.setSize(640, 480);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //top panel
        topPanel = new JPanel();
        edit = new JButton("Edit Profile");
        edit.addActionListener(actionListener);
        topPanel.add(edit);
        requests = new JButton("View Friend Requests");
        requests.addActionListener(actionListener);
        topPanel.add(requests);
        frame.add(topPanel, BorderLayout.NORTH);

        //bottom panel
        bottomPanel = new JPanel();
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

        //name panel
        JPanel namePanel = new JPanel();
        nameText = new JLabel("USER: " + name);
        namePanel.add(nameText);

        //phone panel
        JPanel phonePanel = new JPanel();
        phoneText = new JLabel("PHONE: " + this.phone);
        phonePanel.add(phoneText);

        //email panel
        JPanel emailPanel = new JPanel();
        emailText = new JLabel("EMAIL: " + this.email);
        emailPanel.add(emailText);

        //education panel
        JPanel educationPanel = new JPanel();
        educationText = new JLabel("EDUCATION: " + this.education);
        educationPanel.add(educationText);

        //about me panel
        JPanel aboutMePanel = new JPanel();
        aboutMeText = new JLabel("ABOUT ME:\n" + this.aboutMe);
        aboutMePanel.add(aboutMeText);

        //interests panel
        JPanel interestsPanel = new JPanel();
        interestsText = new JLabel("INTERESTS:\n" + interests.toString());
        interestsPanel.add(interestsText);

        //Combines the above three panels in grid layout
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(3, 2));
        profilePanel.add(namePanel);
        profilePanel.add(phonePanel);
        profilePanel.add(emailPanel);
        profilePanel.add(educationPanel);
        profilePanel.add(aboutMePanel);
        profilePanel.add(interestsPanel);
        frame.add(profilePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add I/O aspects to edit
            if(e.getSource() == edit) {
                //Uses the EnterInfoGUI methods to edit the fields of the object
                name = EnterInfoGUI.showNameInputDialog();
                email = EnterInfoGUI.showEmailInputDialog();
                phone = EnterInfoGUI.showPhoneInputDialog();
                education = EnterInfoGUI.showEducationInputDialog();
                aboutMe = EnterInfoGUI.showAboutInputDialog();
                //TODO change showInterests Dialog to an arrayList and implement required changes
                interests.add(EnterInfoGUI.showInterestsInputDialog());

                //Updates the JLabels to the current fields
                nameText.setText(name);
                emailText.setText(email);
                phoneText.setText(phone);
                //TODO Create an about me and interests formatting method.
                educationText.setText(aboutMe);
                interestsText.setText(interests.toString());
            }
            if(e.getSource() == requests) {
                //Opens the Friends List GUI
                new FriendsListGUI().run(); //TODO update this once the friends list GUI is more operational
            }
            //TODO Utilize retrieved strings from bottom panel I/O to open and befriend the requested profiles
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

    public ArrayList<String> getInterests() {
        return this.interests;
    }

    public ArrayList<String> getFriends() {
        return this.friends;
    }

    public String getEducation() {
        return this.education;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAboutMe() {
        return this.aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

}

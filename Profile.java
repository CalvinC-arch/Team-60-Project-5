import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

public class Profile implements Serializable, Runnable {

    //Profile Fields
    private String username;
    private ArrayList<String> interests;
    private ArrayList<String> friends;
    private ArrayList<String> requestsSent;
    private ArrayList<String> requestsReceived;
    private String education;
    private String email;
    private long phoneNumber;
    private String aboutMe;

    //Frames and Panels
    JFrame frame;
    JPanel topPanel;
    JPanel bottomPanel;

    //top elements
    JButton edit; //edit button
    JButton requests; //requests button

    //bottom elements
    JComboBox<String> users; //all the users on the app
    JButton sendFriendRequest; //sends friend request


    //Text Boxes
    JLabel nameText;
    JLabel phoneText;
    JLabel emailText;
    JLabel educationText;
    JLabel aboutMeText;
    JLabel interestsText;

    public Profile(String username, ArrayList<String> interests, ArrayList<String> friends, String education,
                   String email, long phoneNumber, String aboutMe, ArrayList<String> requestsSent,
                   ArrayList<String> requestsReceived) {

        this.username = username;
        this.interests = interests;
        this.friends = friends;
        this.education = education;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;
        this.requestsSent = requestsSent;
        this.requestsReceived = requestsReceived;
    }

    public Profile() {

        this.username = "Will Stonebridge";
        this.interests = new ArrayList<String>() {
            {
                add("Rowing");
                add("Coding");
                add("History");
            }
        };
        this.friends = new ArrayList<String>() {
            {
                add("Calvin Carta");
                add("Jeff Chen");
            }
        };
        this.education = "Purdue";
        this.email = "jwstoneb@purdue.edu";
        this.phoneNumber = 8476360377L;
        this.aboutMe = "Why are we here, Just to suffer?";
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
        this.requestsReceived = new ArrayList<>();
        this.requestsSent = new ArrayList<>();

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

        //set username to first index in array and iterate the index counter
        this.username = profileInfo[index];
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

        this.phoneNumber = Long.parseLong(profileInfo[index]);
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
        //TODO: assign requests sent and requests received
    }
    /*this constructor creates another profile object identical to the one passed to it as an argument, it will be used
    when the server send profiles back to the client to display after logging in*/
    public Profile(Profile profile) {
        this.username = profile.getUsername();
        this.interests = profile.getInterests();
        this.friends = profile.getFriends();
        this.education = profile.getEducation();
        this.email = profile.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.aboutMe = profile.getAboutMe();
        this.requestsSent = profile.getRequestsSent();
        this.requestsReceived = profile.getRequestsReceived();
    }

    public void run() {
        frame = new JFrame(username);
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
        JLabel userLabel = new JLabel("Find Other Users: ");
        bottomPanel.add(userLabel);
        users = new JComboBox<>();
        users.setMaximumRowCount(3);

        //TODO: Add users to JComboBox from Array of all users

        bottomPanel.add(users);
        sendFriendRequest = new JButton("Send Friend Request");
        sendFriendRequest.addActionListener(actionListener); //add action listener to button
        bottomPanel.add(sendFriendRequest);
        JLabel username = new JLabel("Enter Username");
        bottomPanel.add(username);

        frame.add(bottomPanel, BorderLayout.SOUTH); // add panel to frame

        //Everything below is for the center panel which holds user info

        //name panel
        JPanel namePanel = new JPanel();
        nameText = new JLabel("USER: " + username);
        namePanel.add(nameText);

        //phone panel
        JPanel phonePanel = new JPanel();
        phoneText = new JLabel("PHONE: " + this.phoneNumber);
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
        aboutMeText = new JLabel("ABOUT ME:\n" + formatAboutString(aboutMe));
        aboutMePanel.add(aboutMeText);

        //interests panel
        JPanel interestsPanel = new JPanel();
        interestsText = new JLabel("INTERESTS:\n" + formatInterestsString(interests));
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

    public void viewFriendProfile() {
        run();
        frame.remove(topPanel);
        frame.remove(bottomPanel);
    }

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //TODO add I/O aspects to edit
            if(e.getSource() == edit) {
                //Uses the EnterInfoGUI methods to edit the fields of the object
                username = EnterInfoGUI.showNameInputDialog();
                email = EnterInfoGUI.showEmailInputDialog();
                phoneNumber = EnterInfoGUI.showPhoneInputDialog();
                education = EnterInfoGUI.showEducationInputDialog();
                aboutMe = EnterInfoGUI.showAboutInputDialog();
                interests = EnterInfoGUI.showInterestsInputDialog();

                //Updates the JLabels to the current fields
                nameText.setText(username);
                emailText.setText(email);
                phoneText.setText(formatPhoneString(phoneNumber)); //format phone (update enter info gui)
                educationText.setText(formatAboutString(aboutMe));
                interestsText.setText(formatInterestsString(interests));
            }
            if(e.getSource() == requests) {
                //Opens the Friends List GUI
                /*Profile profile = new Profile();
                FriendsListGUI friends = new FriendsListGUI();
                friends.run();*/
            }

            if (e.getSource() == sendFriendRequest) {
                //TODO update user's list of sent friend requests and other user's list of received friend requests
            }
        }
    };



    public void writeExportFile() throws IOException {

        try {
            //creating new file and printWriter for this profile
            File f = new File(this.username + "Export.csv");
            PrintWriter pw = new PrintWriter(new FileWriter(f, false));
            pw.print(this.username + ',' + interests.size() + ',');

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

            pw.print(this.education + ',' + this.email + ',' + this.phoneNumber + ',' + this.aboutMe);
            pw.close();

        } catch (IOException e) { //If the file is open or another error occurs, display this error message
            JOptionPane.showMessageDialog(null, "There was an error exporting your file, please " +
                    "close the file if it is open on your device", "CamsGram", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static String formatAboutString(String about) { //organizes the About information as a small paragraph
        String about2 = ""; //initializes about2 as empty
        String newAbout = about; //duplicates the String about
        boolean checking = false; //checker that notifies become true if while loop runs
        while (newAbout.length() > 30) { //while-loop runs if line is too long
            checking = true; //updates checker
            if (newAbout.charAt(30) == ' ') { //checks if where the text cuts has a space
                newAbout = newAbout.substring(0,30) + newAbout.substring(31); //deletes the extra space
                about = about2 + newAbout.substring(0, 30) + "\n"; //formats the String
            } else {
                about = about2 + newAbout.substring(0, 30) + "-\n"; //formats the String
            } //end if
            about2 = about; //updates about2
            newAbout = newAbout.substring(30); //updates NewAbout
        }
        if (checking) { //checks if while loop ran
            return "\n" + about + newAbout; //returns formatted About Info String
        } else {
            return "\n" + about; //returns normal About Info String
        } //end if
    } //formatAboutString

    public static String formatInterestsString(ArrayList<String> interests) { //converts array to formatted String
        String newInterests =  ""; //initializes String to be empty
        for(int i = 0; i < interests.size(); i++) { //for-loop that updates 'elements' with all the interests
            newInterests = newInterests + "\n    "+ interests.get(i); //formats elements into a vertical list
        } //for-loop
        return newInterests; //returns Interests as formatted String
    } //formatInterestsString

    public static String formatPhoneString(long phone) {
        String phoneS = String.valueOf(phone);
        String phoneFormat = ""; //declares phone number formatted
        phoneFormat = "(" + phoneS.substring(0, 3)+ ") " + phoneS.substring(3,6) + "-" + phoneS.substring(6, 10);
        //formats the phone number correctly
        return phoneFormat; //returns phone String in correct format
    } //formatPoneString

    public String getUsername() {
        return this.username;
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

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAboutMe() {
        return this.aboutMe;
    }

    public ArrayList<String> getRequestsSent() {
        return this.requestsSent;
    }

    public ArrayList<String> getRequestsReceived() {
        return this.requestsReceived;
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

    public void addFriend(String friend) { //TODO change this
        this.friends.add(friend);
    }

    public void removeFriends(String friend) { //TODO change this

        for (int i = 0; i < friends.size(); i++) {

            if (friends.get(i).equals(friend)) {
                friends.remove(i);
            }
        }
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public void addInterest(String interest) {
        this.interests.add(interest);
    }

    public void removeInterest(String interest) {

        for (int i = 0; i < interests.size(); i++) {

            if (interests.get(i).equals(interest)) {
                interests.remove(i);
            }
        }
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRequestsSent(ArrayList<String> requestsSent) {
        this.requestsSent = requestsSent;
    }

    public void addSentRequest(String username) {
        this.requestsSent.add(username);
    }

    public void removeSentRequest(String username) {
        for (int i = 0; i < requestsSent.size(); i++) {

            if (requestsSent.get(i).equals(username)) {
                requestsSent.remove(i);
            }
        }
    }

    public void setRequestsReceived(ArrayList<String> requestsReceived) {
        this.requestsSent = requestsSent;
    }

    public void addReceivedRequest(String username) {
        this.requestsReceived.add(username);
    }

    public void removeReceivedRequest(String username) {
        for (int i = 0; i < requestsReceived.size(); i++) {

            if (requestsReceived.get(i).equals(username)) {
                requestsReceived.remove(i);
            }
        }
    }


}



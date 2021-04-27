import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

public class Profile {

    private String username;
    private ArrayList<String> interests;
    private ArrayList<String> friends;
    private String education;
    private String email;
    private long phoneNumber;
    private String aboutMe;

    public Profile(String username, ArrayList<String> interests, ArrayList<String> friends, String education,
                   String email, long phoneNumber, String aboutMe) {

        this.username = username;
        this.interests = interests;
        this.friends = friends;
        this.education = education;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
    }

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

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

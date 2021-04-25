import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import java.util.Locale;

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

        this.interests = new ArrayList<>();
        this.friends = new ArrayList<>();

        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            profile = bfr.readLine();

            bfr.close();
        } catch (IOException e) {
            //DISPLAY ERROR GUI
            e.printStackTrace();
        }

        profileInfo = profile.split(",");

        this.username = profileInfo[index];
        index++;

        listLength = Integer.parseInt(profileInfo[index]);
        index++;

        tempIndex = index;

        while (index < listLength + tempIndex) {
            interests.add(profileInfo[index]);
            index++;
        }

        listLength = Integer.parseInt(profileInfo[index]);
        index++;

        tempIndex = index;

        while (index < listLength + tempIndex) {
            friends.add(profileInfo[index]);
            index++;
        }

        this.education = profileInfo[index];
        index++;

        this.email = profileInfo[index];
        index++;

        this.phoneNumber = Long.parseLong(profileInfo[index]);
        index++;

        this.aboutMe = profileInfo[index] + ", ";
        index++;

        while (index < profileInfo.length) {
            this.aboutMe = this.aboutMe + profileInfo[index] + ", ";
            index++;
        }

        this.aboutMe = this.aboutMe.substring(0, this.aboutMe.length() - 2);
    }

    public void writeExportFile() throws IOException {

        //creating new file and printwriter for this profile
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
    }

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
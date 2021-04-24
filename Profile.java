import java.util.ArrayList;

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

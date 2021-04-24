import java.util.ArrayList;

public class Account {

    private String email;
    private ArrayList<Profile> profiles;

    //Creates a new account object
    public Account(String email, ArrayList<Profile> profiles) throws NullPointerException {
        if (email == null) {
            throw new NullPointerException();
        } else {
            this.email = email;
        }
        this.profiles = profiles;
    }

    //returns the email of an account
    public String getEmail() {
        return this.email;
    }

    //returns the arraylist of profiles associated with an account
    public ArrayList<Profile> getProfiles() {
        return this.profiles;
    }

    //changes the email of an account
    public void setEmail(String email) {
        this.email = email;
    }

    //sets a new set of profiles to an account
    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }

    //adds a profile to the profile arraylist
    public void addProfiles(Profile profile) {
        this.profiles.add(profile);
    }

    //removes a profile from the account profile arraylist
    public void deleteProfile(String username) {

        for (int i = 0; i < this.profiles.size(); i++) {

            if (profiles.get(i).getUsername().equals(username)) {
                profiles.remove(i);
            }
        }
    }

}

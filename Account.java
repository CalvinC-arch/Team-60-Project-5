import java.util.ArrayList;

public class Account {
    private String email;
    private ArrayList<Profile> profiles;

    public Account(String email, ArrayList<Profile> profiles) throws NullPointerException {
        if (email == null) {
            throw new NullPointerException();
        } else {
            this.email = email;
        }
        this.profiles = profiles;
    }

    public String getEmail() {
        return this.email;
    }

    public ArrayList<Profile> getProfiles() {
        return this.profiles;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfiles(ArrayList<Profile> profiles) {
        this.profiles = profiles;
    }
}

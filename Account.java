import java.util.ArrayList;

public class Account {
    private final String email;
    private final String password;
    private ArrayList<Profile> profiles;

    public Account(String email, String password, ArrayList<Profile> profiles) throws NullPointerException {
        this.email = email;
        this.password = password;
        this.profiles = profiles;
    }

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

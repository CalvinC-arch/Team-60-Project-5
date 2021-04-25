import java.io.IOException;
import java.util.ArrayList;

public class ProfileCSVTest {

    public static void main(String args[]) throws IOException {

        //Testing profile import
        Profile profile = new Profile("testProfile.csv");

        System.out.println(profile.getUsername());

        System.out.println(profile.getPhoneNumber());

        System.out.println(profile.getAboutMe());

        //Testing profile output
        ArrayList<String> interests = new ArrayList<String>();
        interests.add("running");
        interests.add("gaming");
        interests.add("movies");
        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Joe");
        friends.add("Eric");
        friends.add("Naia");
        Profile jeff = new Profile("chen3801", interests, friends, "Purdue University",
                "chen3801@purdue.edu", 5167341551L, "hello, I am Jeff");
        jeff.writeExportFile();

        Profile newProfile = new Profile("chen3801Export.csv");

        System.out.println(newProfile.getUsername());

        System.out.println(newProfile.getPhoneNumber());

        System.out.println(newProfile.getAboutMe());

    }

}
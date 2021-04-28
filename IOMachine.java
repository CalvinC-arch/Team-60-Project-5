import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * IOMachine
 *
 * This class was made to easily execute IO commands. Initialize it with an ObjectOutputStream and an ObjectInputStream
 * and then use commands like findProfile (returns a profile object) and deleteProfile (erases a profile from the
 * server).
 *
 * @author Team 60, Section 11
 * @version 5/03/2021
 *
 */

public class IOMachine extends ObjectOutputStream {

    ObjectOutputStream dos;
    ObjectInputStream dis;

    //Constructs an IO machine from an ObjectOutputStream and an ObjectInputStream
    public IOMachine(ObjectOutputStream dos, ObjectInputStream dis) throws IOException, ClassNotFoundException {
        this.dos = dos;
        this.dis = dis;
    }

    /**
     * Searches for a Profile in the server by username, returns that profile (Or null if it is not found).
     *
     * @param username - the username of a profile to be searched for
     *
     * @return Profile or null
     */

    public Profile findProfile(String username) {
        try {
            //Write command and username to server
            dos.writeObject("SendProfile");
            dos.writeObject(username);

            //read whether the profile was found or not
            String result = (String) dis.readObject();

            if (result.equals("True")) {

                //read and return profile
                return (Profile) dis.readObject();

            }

            //returns null if no profile matching the username is found
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * adds a profile to the server
     *
     * @param profile - the profile to added
     */

    public void addProfile(Profile profile) {

        try {

            //Write command, email, and profile to add to server
            dos.writeObject("AddProfile");
            dos.writeObject(profile.getEmail());
            dos.writeObject(profile);

            //read whether the profile was found or not
            String result = (String) dis.readObject();

            if (result.equals("True")) {
                System.out.println("Profile Added!");
            } else {
                System.out.println("Profile not able to be added");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * removes a profile from the server
     *
     * @param username - username of the profile to be deleted
     *
     * @return a boolean that is true if the profile was found and deleted
     */

    public boolean deleteProfile(String username) {

        //indicates whether the profile was deleted
        boolean deleted = false;

        try {

            //Write command and username to server
            dos.writeObject("DeleteProfile");
            dos.writeObject(username);

            //read whether the profile was found or not
            String result = (String) dis.readObject();

            if (result.equals("True")) {
                deleted = true;
            } else {
                deleted = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return deleted;
        }
    }

    /**
     * adds an account to the server
     *
     * @param account - the profile to added
     */

    public void addAccount(Account account) {
        try {
            dos.writeObject("CreateAccount");
            dos.writeObject(account.getEmail());
            dos.writeObject(account.getPassword());

            ArrayList<Profile> profiles = account.getProfiles();
            dos.writeObject(profiles);

            String result = (String) dis.readObject();

            if (result.equals("True")) {

                System.out.println("Account added!");

            } else {

                System.out.println("Account not able to be added");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for an Account in the server by email, returns that Account (Or null if it is not found).
     *
     * @param email - the email of a account to be searched for
     *
     * @return an Account or null
     */

    //TODO Implement finding accounts

    /**
     * removes an Account from the server
     *
     * @param account - Account to be deleted
     */

    public void deleteAccount(Account account) {

        try {

            dos.writeObject("DeleteAccount");
            dos.writeObject(account.getEmail());

            String result = (String) dis.readObject();

            if (result.equals("True")) {

                System.out.println("Account deleted!");

            } else {

                System.out.println("Account not able to be deleted");

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


/*
    try {

    } catch (Exception e) {
        e.printStackTrace();
    }

    //TEST SECTION
    //This gets the profile passed from the server and reads the aboutMe section, then deletes the profile, then
    //attempts to receive the profile, then adds the profile back, then reads the profile and aboutMe again. Shows
    //that the cases and network IO function. Can leave out the adding to show that profile deletion persists
    //past client disconnect



    //RECEIVING ACCOUNT PROFILES

        dos.writeObject("SendAccountProfiles");
        dos.writeObject("ccarta@purdue.edu");

    result = (String) dis.readObject();

        if (result.equals("True")) {

        ArrayList<Profile> profiles = (ArrayList<Profile>) dis.readObject();
        System.out.println(profiles.size());

    } else {

        System.out.println("No profiles found");

    }

    //DELETING AN ACCOUNT

        dos.writeObject("DeleteAccount");
        dos.writeObject("btac1000@gmail.com");

    result = (String) dis.readObject();

        if (result.equals("True")) {

        System.out.println("Account deleted!");

    } else {

        System.out.println("Account not able to be deleted");

    }

    //END OF TEST SECTION

*/
}

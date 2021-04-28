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
     * @return Profile or null
     */

    public Profile findProfile(String username) {
        try {
            String result; //result returned from server

            //Write command and username to server
            dos.writeObject("SendProfile");
            dos.writeObject(username);

            //read whether the profile was found or not
            result = (String) dis.readObject();

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
     * adds a profile to the server based on the associated account
     *
     * @param profile - the profile to added
     * @return whether the profile was added or not
     */

    public boolean addProfile(Profile profile, String email) {

        try {
            String result; //result returned from server

            //Write command, email, and profile to add to server
            dos.writeObject("AddProfile");
            dos.writeObject(email);
            dos.writeObject(profile);

            //read whether the profile was found or not
            result = (String) dis.readObject();

            return result.equals("True");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * removes a profile from the server
     *
     * @param username - username of the profile to be deleted
     * @return a boolean that is true if the profile was found and deleted
     */

    public boolean deleteProfile(String username) {

        try {
            String result; //result returned from server

            //Write command and username to server
            dos.writeObject("DeleteProfile");
            dos.writeObject(username);

            //read whether the profile was found or not
            result = (String) dis.readObject();

            return result.equals("True");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * adds an account to the server
     *
     * @param account - the account to added
     * @return whether the account was added or not
     */

    public boolean addAccount(Account account) {

        try {
            String result; //result returned from server

            dos.writeObject("CreateAccount");
            dos.writeObject(account.getEmail());
            dos.writeObject(account.getPassword());

            ArrayList<Profile> profiles = account.getProfiles();
            dos.writeObject(profiles);

            result = (String) dis.readObject();

            return result.equals("True");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Searches for an Account in the server by email, returns that Account (Or null if it is not found).
     *
     * @param email - the email of a account to be searched for
     *
     * @return an Account or null
     */

    public Account findAccount(String email) {

        try {
            String result; //result returned from server
            String password; //password returned from server
            ArrayList<Profile> profiles; //profiles returned from the server

            dos.writeObject("SendAccount");
            dos.writeObject(email);

            result = (String) dis.readObject();

            if (result.equals("True")) {
                password = (String) dis.readObject();
                profiles = (ArrayList<Profile>) dis.readObject();
                return (new Account(email, password, profiles));
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * removes an Account from the server
     *
     * @param account - Account to be deleted
     * @return whether the account was deleted or not
     */

    public boolean deleteAccount(Account account) {

        try {
            String result; //result returned from server

            dos.writeObject("DeleteAccount");
            dos.writeObject(account.getEmail());

            result = (String) dis.readObject();

            return result.equals("True");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * edits a non list field for a profile
     *
     * @param username - username of the profile to be edited
     * @param parameter - the name of the field to be edited, capitalized
     * @param parameterValue - the new value of the field
     *
     * @return whether field was edited or not
     */

    public boolean editProfile(String username, String parameter, String parameterValue) {

        try {
            String result; //result returned from server

            dos.writeObject("Edit" + parameter);
            dos.writeObject(username);
            dos.writeObject(parameterValue);

            result = (String) dis.readObject();

            return result.equals("True");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * edits a list field for a profile
     *
     * @param action - whether the list is being added to or removed from. Should either be "Add" or "Remove"
     * @param username - username of the profile to be edited
     * @param parameter - the name of the field to be edited, capitalized
     * @param parameterValue - the new value of the field
     *
     * @return whether field was edited or not
     */

    public boolean editProfileList(String action, String username, String parameter, String parameterValue ) {

        try {
            String result; //result returned from server

            dos.writeObject(action + parameter);
            dos.writeObject(username);
            dos.writeObject(parameterValue);

            result = (String) dis.readObject();

            return result.equals("True");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

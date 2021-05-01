import javax.swing.*;
import java.util.ArrayList;
/**
 * EnterInfoGUI.java
 *
 * This program prompts contains all the input
 * dialogs that asks the user information
 * for the creation of a profile.
 *
 * @author Team 060, Section 11
 * @version May 03, 2021
 * */
public class EnterInfoGUI {
    //declaration of final String arrays of state and university options
    private static final String[] statesOptions = {"Arizona", "Indiana", "Ohio", "Virginia"};

    private static final String[] arizonaOptions = {"Northern Arizona University", "Prescott College"};

    private static final String[] indianaOptions = {"Notre Dame", "Purdue University",
            "Indiana University", "Butler University"};
    private static final String[] ohioOptions = {"Ohio University", "Oberlin College"};

    private static final String[] virginiaOptions = {"University of Richmond", "Virginia Polytechnic",
            "Liberty University", "University of Virginia"};
    public static void main (String[] args) {
        String username; //unique username for the profile
        String name;  //name of the user
        long phone; //phone of the user
        String email; //email of the user
        String birthdate; //birth date of the user
        String education; //the university and state of the user
        String about; //extra information about the user
        String formattedAbout; //formats the About String
        ArrayList<String> interests; //interests of the user
        String formattedInterests; //interests formatted as a vertical list
        String password; //password for the user's account
        String output;   //contains all information of the user in order

        username = showUsernameInputDialog(); //calls the method that proper user to enter username
        name = showNameInputDialog(); //calls the method that prompts user to enter name
        phone = showPhoneInputDialog(); //calls the method that prompts user to enter phone
        email = showEmailInputDialog(); //calls the method that prompts user to enter name
        birthdate = showBirthdateInputDialog(); //calls the method that prompts user to enter birth date
        education = showEducationInputDialog(); //calls the method that prompts user to enter state, university
        about = showAboutInputDialog();  //calls the method that prompts user to enter about
        formattedAbout = formatAboutString(about); //calls the method that formats the About String
        interests = showInterestsInputDialog(); //calls the method that prompts user to enter interests
        formattedInterests = formatInterestsString(interests);
        password = showPasswordInputDialog();   //calls the method that prompts user to enter account's password
        showCreatingDialog(); // shows message indicating the profile is being created

        output = String.format("User Information:\n" + //contains all user information in order
                        "Username: %s\n" +
                        "Name: %s\n" +
                        "Phone: %d\n" +
                        "Email: %s\n" +
                        "Birth Date: %s\n" +
                        "Education: %s\n" +
                        "About: %s\n" +
                        "Interests: %s\n" +
                        "Password: %s\n", username, name, phone, email, birthdate, education, formattedAbout,
                formattedInterests, password);
        System.out.println(output); //prints output
    } //end main

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
        //returns Interests as formatted String
        if (interests == null) {
            newInterests = "User did not include any interests.";
        } else {
            for(int i = 0; i < interests.size(); i++) { //for-loop that updates 'elements' with all the interests
                newInterests = newInterests + "\n  "+ interests.get(i); //formats elements into a vertical list
            } //for-loop
        }
        return newInterests;
    } //formatInterestsString

    public static String showUsernameInputDialog() {
        String username;
        do {
            username = JOptionPane.showInputDialog(null, "Create Username:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to enter username

            if (username == null) {
                JOptionPane.showMessageDialog(null, "Must input a username!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE);
            } else if (username.equals("")) { //checks if username is null
                JOptionPane.showMessageDialog(null, "Username cannot be empty!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } else if (username.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Username can only be a single word!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //end if


        } while (username == null || username.equals("") || username.contains(" ")); //do-while runs while username is null or it has a space
        return username; //returns the username for the profile
    } //showUsernameInputDialog

    public static String showNameInputDialog() {
        String name = ""; //firs and last name of user
        do {

            name = JOptionPane.showInputDialog(null, "Full Name:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to enter name
            if (name == null) {
                name = "Name not Specified";
                break;
            } else if (name.equals("")) { //checks if name is null
                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } else if (!name.contains(" ")) { //checks if name contains first name and last name
                JOptionPane.showMessageDialog(null, "Please enter your full name!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //end if


        } while (name.equals("")|| !name.contains(" ")); //do-while runs while name is null
        return name; //returns String name of the user
    } //showNameInputDialog

    public static long showPhoneInputDialog() {
        String phone; //phone number of user
        String phone2; //phone number duplicate
        long phoneLong = 0; //initializes the phone as 0
        boolean checking = true; //initializes 'checking' as true
        do {
            phone = JOptionPane.showInputDialog(null, "Phone Number:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to input phone number
            phone2 = phone;  //duplicates the phone number to check validity later

            try {
                if (phone == null) {
                    phoneLong = 1111111111;
                    checking = false;
                    break;
                } else if (phone.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
                } else {
                    phoneLong = Long.parseLong(phone); //throws exception is phone number is not a Long
                    checking = false; //makes loop stop if no exception is thrown
                    if (phone.length() != 10) { //checks if phone number has more than 10 digits
                        JOptionPane.showMessageDialog(null, "Please enter a valid phone number!",
                            "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
                        checking = true; //makes loop run again which asks user to input again
                    } //end if
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number!",
                        "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //try-catch block
        } while (checking); //do-while runs while 'checking' is true
        //formats the phone number correctly
        return phoneLong; //returns phone String in correct format
    } //showPhoneInputDialog

    public static String formatPhoneString(long phone) {
        String phoneS = String.valueOf(phone);
        String phoneFormat = ""; //declares phone number formatted
        phoneFormat = "(" + phoneS.substring(0, 3)+ ") " + phoneS.substring(3,6) + "-" + phoneS.substring(6, 10);
        //formats the phone number correctly
        return phoneFormat; //returns phone String in correct format
    } //formatPoneString

    public static String showEmailInputDialog() {
        String email; //email of the user
        boolean checking = true; //initializes 'checking' as true
        do {
            email = JOptionPane.showInputDialog(null, "Email: ",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to input email

            if (email == null) {
                email = "Email Not Specified";
                checking = false;
                break;
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email cannot be empty!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } else if (!email.contains("@") || !email.contains(".")) { //if email does not contain "@" or "."
                JOptionPane.showMessageDialog(null, "Please enter a valid  email!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } else {
                checking = false; //updates checking to false
            }//end if

        } while (checking); //do-while loop runs while 'checking' is true
        return email; //returns String email
    } //showEmailInputDialog

    public static ArrayList<String> showInterestsInputDialog() {
        ArrayList<String> interests = new ArrayList<>(); //List with user interests
        String interest; //personal interests of the user
        String elements = ""; //personal interests formatted
        boolean checking = true; //initializes 'checking' as true
        do {
            try {
                interest = JOptionPane.showInputDialog(null,
                        "Enter personal interests separated by commas: ", "CampsGram",
                        JOptionPane.QUESTION_MESSAGE); //asks user to input interests
                if(interest == null) {
                    checking = false;
                    break;
                } else if (interest.equals("")) { //checks if interest ArrayList is null
                    JOptionPane.showMessageDialog(null, "Please enter your interests!",
                            "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
                } else {
                    do {
                        if (interest.contains(" ")) { //checks if there are any spaces
                            interest = interest.replace(" ", ""); //removes all spaces
                        }//end if

                        int comma = interest.indexOf(","); //finds comma separating interests
                        interests.add(interest.substring(0, comma)); //adds the first interest to the ArrayList
                        interest = interest.substring(comma + 1); //updates the String by removing the first interest
                    } while (interest.contains(",")); //do-while loops runs until there are no commas in the String
                    interests.add(interest); //adds remaining interest to the ArrayList
                    checking = false; //stops loops since interests format is valid
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please format your interests correctly!",
                        "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //try-catch block
        } while (checking); //do-while loops runs while 'checking' is true
        return interests; //returns the String 'elements' will all the interests formatted correctly
    } //showInterestsInputDialog

    public static String showAboutInputDialog() {
        String about; //extra information about the user
        about = JOptionPane.showInputDialog(null, "Information about yourself " +
                        "(Leave empty if you do not wish to add this to your profile) : ", "CampsGram",
                JOptionPane.QUESTION_MESSAGE); //asks user to input information about themselves
        if (about == null) {
            about = "User decided not to share info"; //displays when user leaves input empty
        } else if (about.equals("")) {
            about = "User decided not to share info"; //displays when user leaves input empty
        }
        return about; //returns String 'about'
    } //showAboutInputDialog

    public static String showEducationInputDialog() {
        String state =""; //state of the user
        String university = ""; //university of the user
        do { //do-while

            state = (String) JOptionPane.showInputDialog(null, "Select your U.S. State:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, statesOptions,
                    statesOptions[0]); //makes user select a US State
            if(state == null) {
                state = "State not specified";
                break;
            } else if (state.equals("")) { //checks if state choice is null
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if

        } while (state.equals("")); //do-while loop runs while state is null

        if (state == null) {
            university = "University not Specified";
        } else if (state.equals("Arizona")) { //if user selects Arizona as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, arizonaOptions,
                    arizonaOptions[0]); //makes user choose between Arizona options
            if (university == null) {
                university = "University not Specified";
            } else if (university.equals("")) { //checks if university is empty
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } else if (state.equals("Indiana")) { //if user selects Indiana as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, indianaOptions,
                    indianaOptions[0]); //makes user select between Indiana universities
            if (university == null) {
                university = "University not Specified";
            } else if (university.equals("")) { //checks if university choice is empty
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } else if (state.equals("Ohio")) { //if user selects Ohio as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                "CampsGram", JOptionPane.QUESTION_MESSAGE, null, ohioOptions,
                ohioOptions[0]); //makes user choose between Ohio universities
            if (university == null) {
                university = "University not Specified";
            } else if (university.equals("")) { //checks if university choice is empty
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } else if (state.equals("Virginia")) { //if user selects Virgina as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, virginiaOptions,
                    virginiaOptions[0]); //make user choose between Virgina universities
            if (university == null) {
                university = "University not Specified";
            } else if (university.equals("")) { //checks if university choice is empty
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } //end else if

        return (university + " - " + state); //return String University, State
    } //showEducationInputDialog

    public static String showBirthdateInputDialog() {
        String birthdate; //birthdate of user
        int month; //declares month of birthdate
        int day; //declares day of birth date
        int year; //declares year of birthdate
        boolean checking = true; //initializes 'checking' as true
        do {
            birthdate = JOptionPane.showInputDialog(null, "Birthdate (mm/dd/yyyy): ",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to enter birthdate
            try {
                if (birthdate == null) {
                    birthdate = "Birthdate not Specified";
                    checking = false;
                    break;
                } else if (birthdate.equals("")) { //checks if birth date is empty
                    JOptionPane.showMessageDialog(null, "Birth date cannot be empty!",
                            "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
                } else if (birthdate.indexOf("/") != 2 || (birthdate.substring(3).indexOf("/") != 2)
                        || birthdate.length() != 10) { //checks if birthdate is in the incorrect format
                    JOptionPane.showMessageDialog(null,
                            "Please format your birth date correctly!", "CamsGram",
                            JOptionPane.ERROR_MESSAGE); //shows error
                } else {
                    month = Integer.parseInt(birthdate.substring(0, 2)); //throw exception if month is not Integer
                    day = Integer.parseInt(birthdate.substring(3, 5)); //throw exception if day is not Integer
                    year = Integer.parseInt(birthdate.substring(6, 10)); //throw exception if year is not Integer
                    checking = false;
                } //end if

            } catch (NumberFormatException nE) {
                JOptionPane.showMessageDialog(null,
                        "Please format your birth date correctly!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            }// end try-catch block
        } while (checking); //do-while loop runs while 'checking' is true
        return birthdate; //return string birthdate
    } //showBirthdateInputDialog

    public static String showPasswordInputDialog() {
        String password;  //password of user's account
        boolean checking = true; //initializes 'checking' as true
        do {
            checking = true; //updates 'checking' as true every time the loop runs
            password = JOptionPane.showInputDialog(null, "Create your password:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            //booleans variables below are true when password contains a special character
            boolean char1 = (password.contains("!") || password.contains("@") || password.contains("#"));
            boolean char2 = (password.contains("$") || password.contains("%") || password.contains("^"));
            boolean char3 = (password.contains("&") || password.contains("*") || password.contains("?"));

            if (password.length() < 7) { //checks if password has at least 7 characters
                JOptionPane.showMessageDialog(null,
                        "Password needs to be at least 7 characters!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
                checking = false; //makes the loop run again which asks user to input again
            } else if (!char1 && !char2 && !char3) { //checks if it contains at least 1 special character
                JOptionPane.showMessageDialog(null,
                        "Password needs to contain at least one special character!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
                checking = false; //makes the loop run again which asks user to input again
            } //end if

        } while (!checking); // do-while loop runs while 'checking' is false
        return password;  //returns password string
    } //showPasswordInputDialog

    public static void showCreatingDialog() {
        JOptionPane.showMessageDialog(null, "Creating User Profile...",
                "CampsGram", JOptionPane.INFORMATION_MESSAGE); //displays message
    } //showCreatingDialog
}

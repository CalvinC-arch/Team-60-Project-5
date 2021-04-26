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
        String phone; //phone of the user
        String email; //email of the user
        String birthdate; //birth date of the user
        String education; //the university and state of the user
        String about; //extra information about the user
        ArrayList<String> interests; //interests of the user
        String password; //password for the user's account
        String output;   //contains all information of the user in order

        username = showUsernameInputDialog();
        name = showNameInputDialog(); //calls the method that prompts user to enter name
        phone = showPhoneInputDialog(); //calls the method that prompts user to enter phone
        email = showEmailInputDialog(); //calls the method that prompts user to enter name
        birthdate = showBirthdateInputDialog(); //calls the method that prompts user to enter birth date
        education = showEducationInputDialog(); //calls the method that prompts user to enter state, university
        about = showAboutInputDialog();  //calls the method that prompts user to enter about
        interests = showInterestsInputDialog(); //calls the method that prompts user to enter interests
        password = showPasswordInputDialog();   //calls the method that prompts user to enter account's password
        showCreatingDialog(); // shows message indicating the profile is being created

        output = String.format("User Information:\n" + //contains all user information in order
                "Username: %s\n" +
                "Name: %s\n" +
                "Phone: %s\n" +
                "Email: %s\n" +
                "Birth Date: %s\n" +
                "Education: %s\n" +
                "About: %s\n" +
                "Interests: %s\n" +
                "Password: %s\n", username, name, phone, email, birthdate, education, about, interests, password);
        System.out.println(output); //prints output

    } //end main

    public static String showUsernameInputDialog() {
        String username; //unique username of the profile
        do {
            username = JOptionPane.showInputDialog(null, "Create Username:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to enter username
            if ((username == null) || (username.isBlank())) { //checks if username is null
                JOptionPane.showMessageDialog(null, "Username cannot be empty!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } else if (username.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Username can only be a single word!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //end if 
        } while (username == null || username.contains(" ")); //do-while runs while username is null or it has a space
        return username; //returns the username for the profile
    } //showUsernameInputDialog

    public static String showNameInputDialog() {
        String name; //firs and last name of user
        do {
            name = JOptionPane.showInputDialog(null, "Full Name:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to enter name
            if ((name == null) || (name.isBlank())) { //checks if name is null
                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } else if (!name.contains(" ")) { //checks if name contains first name and last name
                JOptionPane.showMessageDialog(null, "Please enter your full name!",
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //end if

        } while ((name == null) || (name.isBlank()) || !name.contains(" ")); //do-while runs while name is null
        return name; //returns String name of the user
    } //showNameInputDialog

    public static String showPhoneInputDialog() {
        String phone; //phone number of user
        String phone2; //phone number duplicate
        String phoneFormat = ""; //declares phone number formatted
        boolean checking = true; //initializes 'checking' as true
        do {
            phone = JOptionPane.showInputDialog(null, "Phone Number:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to input phone number
            phone2 = phone;  //duplicates the phone number to check validity later
            try {
                long phoneLong = Long.parseLong(phone); //throws exception is phone number is not a Long
                phone2 = phone.substring(0,10); //throws an exception if phone number is less than 10 digits
                checking = false; //makes loop stop if no exception is thrown
                if (phone.length() >= 11) { //checks if phone number has more than 10 digits
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number!",
                            "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
                    checking = true; //makes loop run again which asks user to input again
                } //end if
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number!", 
                        "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //try-catch block
        } while (checking); //do-while runs while 'checking' is true
        phoneFormat = "(" + phone.substring(0, 3)+ ") " + phone.substring(3,6) + "-" + phone.substring(6, 10);
        //formats the phone number correctly
        return phoneFormat; //returns phone String in correct format
    } //showPhoneInputDialog

    public static String showEmailInputDialog() {
        String email; //email of the user
        boolean checking = true; //initializes 'checking' as true
        do {
            email = JOptionPane.showInputDialog(null, "Email: ",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE); //asks user to input email
            try {
                if (!email.contains("@") || !email.contains(".")) { //if email does not contain "@" or "."
                    String problem = email.substring(0,80); //sets an impossible statement to throw an exception
                } //end if
                checking = false; //makes loops stop if no exception is thrown
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid  email!", 
                        "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //try-catch block
        } while (checking); //do-while loop runs while 'checking' is true
        return email; //returns String email
    } //showEmailInputDialog

    public static ArrayList<String> showInterestsInputDialog() {
        ArrayList<String> interests = new ArrayList<>(); //List with user interests
        String interest; //personal interests of the user
        String elements = ""; //personal interests formatted
        boolean checking = true; //initializes 'checking' as true
        do {
            interest = JOptionPane.showInputDialog(null,
                    "Enter personal interests separated by commas: ", "CampsGram", 
                    JOptionPane.QUESTION_MESSAGE); //asks user to input interests
            if ((interest == null) || (interest.isBlank())) { //checks if interest ArrayList is null
                JOptionPane.showMessageDialog(null, "Please enter your interests!", 
                        "CampsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
            try {
                do {
                    int comma = interest.indexOf(","); //finds comma separating interests
                    interests.add(interest.substring(0, comma)); //adds the first interest to the ArrayList
                    interest = interest.substring(comma +  2); //updates the String by removing the first interest
                } while (interest.contains(",")); //do-while loops runs until there are no commas in the String
                interests.add(interest); //adds remaining interest to the ArrayList
                checking = false; //stops loops since interests format is valid
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please format your interests correctly!", 
                        "CamsGram", JOptionPane.ERROR_MESSAGE); //shows error
            } //try-catch block
        } while (checking); //do-while loops runs while 'checking' is true
        
         /*for(int i = 0; i < interests.size(); i++) { //for-loop that updates 'elements' with all the interests
            elements = elements + "\n"+ interests.get(i); //formats 'elements' and adds every item in the array
        }*/
        return interests; //returns the String 'elements' will all the interests formatted correctly
    } //showInterestsInputDialog

    public static String showAboutInputDialog() {
        String about; //extra information about the user
        about = JOptionPane.showInputDialog(null, "Information about yourself " +
                        "(Leave empty if you do not wish to add this to your profile) : ", "CampsGram",
                JOptionPane.QUESTION_MESSAGE); //asks user to input information about themselves
        try {
            String trying = about.substring(0,1);
        } catch (Exception e) {
            about = "User decided not to share extra information."; //displays when user leaves input empty
        } //try-catch block
        return about; //returns String 'about'
    } ////end showAboutInputDialog

    public static String showEducationInputDialog() {
        String state; //state of the user
        String university = null; //university of the user
        do { //do-while
            state = (String) JOptionPane.showInputDialog(null, "Select your U.S. State:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, statesOptions,
                    statesOptions[0]); //makes user select a US State
            if (state == null) { //checks if state choice is null
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } while (state == null); //do-while loop runs while state is null

        if (state.equals("Arizona")) { //if user selects Arizona as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, arizonaOptions,
                    arizonaOptions[0]); //makes user choose between Arizona options
            if (university == null) { //checks if university is null
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } //end if
        if (state.equals("Indiana")) { //if user selects Indiana as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, indianaOptions,
                    indianaOptions[0]); //makes user select between Indiana universities
            if (university == null) { //checks if university choice is null
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } //end if
        if (state.equals("Ohio")) { //if user selects Ohio as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, ohioOptions,
                    ohioOptions[0]); //makes user choose between Ohio universities
            if (university == null) { //checks if university choice is null
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } //end if
        if (state.equals("Virginia")) { //if user selects Virgina as their state
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, virginiaOptions,
                    virginiaOptions[0]); //make user choose between Virgina universities
            if (university == null) { //checks if university choice is null
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE); //shows error
            } //end if
        } //end if
        return (university + "- " + state); //return String University, State
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
                if (birthdate == null) { //checks if birth date is null
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
                    checking = false; //makes loop stop since birthdate is valid
                } //end if
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please format your birthdate correctly!",
                        "CamsGram", JOptionPane.ERROR_MESSAGE);
            } // end try-catch block
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
            } else if(!char1 && !char2 && !char3) { //checks if it contains at least 1 special character
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

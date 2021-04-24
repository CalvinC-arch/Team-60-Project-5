import javax.swing.*;
import java.util.ArrayList;

public class profileInfo {

    private static final String[] statesOptions = {"Arizona", "Indiana", "Ohio", "Virginia"};

    private static final String[] arizonaOptions = {"Northern Arizona University", "Prescott College"};

    private static final String[] indianaOptions = {"Notre Dame", "Purdue University",
            "Indiana University", "Butler University"};
    private static final String[] ohioOptions = {"Ohio University", "Oberlin College"};

    private static final String[] virginiaOptions = {"University of Richmond", "Virginia Polytechnic",
            "Liberty University", "University of Virginia"};

    public static void main (String[] args) {
        String name;
        String phone;
        String email;
        String birthdate;
        String education;
        String about;
        ArrayList<String> interests;
        String password;
        String output;

        name = showNameInputDialog();
        phone = showPhoneInputDialog();
        email = showEmailInputDialog();
        birthdate = showBirthdateInputDialog();
        education = showEducationInputDialog();
        about = showAboutInputDialog();
        interests = showInterestsInputDialog();
        password = showPasswordInputDialog();
        showCreatingDialog();

        output = String.format("User Information:\n" +
                "Name: %s\n" +
                "Phone: %s\n" +
                "Email: %s\n" +
                "Birth Date: %s\n" +
                "Education: %s\n" +
                "About: %s\n" +
                "Interests: %s\n" +
                "Password: %s\n", name, phone, email, birthdate, education, about, interests, password);
        System.out.println(output);

    } //end main

    public static String showNameInputDialog() {
        String name;
        do {
            name = JOptionPane.showInputDialog(null, "Full Name:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            if ((name == null) || (name.isBlank())) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!name.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Please enter your full name!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            }

        } while ((name == null) || (name.isBlank()) || !name.contains(" "));

        return name;
    }

    public static String showPhoneInputDialog() {
        String phone;
        String phone2;
        String phoneFormat = "";
        boolean checking = true;
        do {
            phone = JOptionPane.showInputDialog(null, "Phone Number:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            phone2 = phone;
            try {
                long phoneLong = Long.parseLong(phone);
                phone2 = phone.substring(0,10);
                checking = false;
                if (phone.length() >= 11) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid phone number!", "CamsGram",
                            JOptionPane.ERROR_MESSAGE);
                    checking = true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid phone number!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (checking);
        phoneFormat = "(" + phone.substring(0, 3)+ ") " + phone.substring(3,6) + "-" + phone.substring(6, 10);
        return phoneFormat;
    }

    public static String showEmailInputDialog() {
        String email;
        boolean checking1 = true;
        do {
            email = JOptionPane.showInputDialog(null, "Email: ",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            try {
                if (!email.contains("@") || !email.contains(".")) { //if email does not contain "@" or "."
                    String problem = email.substring(0,80); //sets an impossible statement to produce an exception
                }
                checking1 = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid  email!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (checking1);
        return email;
    }

    public static ArrayList<String> showInterestsInputDialog() {
        ArrayList<String> interests = new ArrayList<>();
        String interest;
        boolean checking2 = true;
        do {
            interest = JOptionPane.showInputDialog(null, "Enter personal interests separated by commas: ",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            if ((interest == null) || (interest.isBlank())) {
                JOptionPane.showMessageDialog(null, "Please enter your interests!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
            try {
                do {
                    int comma = interest.indexOf(",");
                    interests.add(interest.substring(0, comma));
                    interest = interest.substring(comma +  2);
                } while (interest.contains(","));
                interests.add(interest);
                checking2 = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please format your interests correctly!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (checking2);

        for(int i = 0; i < interests.size(); i++) //prints array in separate columns
            System.out.println(interests.get(i));
        return interests;
    }

    public static String showAboutInputDialog(){
        String about;
        about = JOptionPane.showInputDialog(null, "Information about yourself " +
                        "(Leave empty if you do not wish to add this to your profile) : ", "CampsGram",
                JOptionPane.QUESTION_MESSAGE);
        try {
            String trying = about.substring(0,1);
        } catch (Exception e) {
            about = "User decided not to share extra information.";
        }
        return about;
    }

    public static String showEducationInputDialog() {
        String state;
        String university = null;
        do {
            state = (String) JOptionPane.showInputDialog(null, "Select your U.S. State:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, statesOptions,
                    statesOptions[0]);
            if (state == null) {
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            } //end if
        } while (state == null);

        if (state.equals("Arizona")) {
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, arizonaOptions,
                    arizonaOptions[0]);
            if (university == null) {
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (state.equals("Indiana")) {
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, indianaOptions,
                    indianaOptions[0]);
            if (university == null) {
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (state.equals("Ohio")) {
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, ohioOptions,
                    ohioOptions[0]);
            if (university == null) {
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (state.equals("Virginia")) {
            university = (String) JOptionPane.showInputDialog(null, "Select your University:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE, null, virginiaOptions,
                    virginiaOptions[0]);
            if (university == null) {
                JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "CampsGram",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return (university + ", " + state);
    }

    public static String showBirthdateInputDialog() {
        String birthdate;
        int month;
        int day;
        int year;
        boolean checking = true;
        do {
            birthdate = JOptionPane.showInputDialog(null, "Birthdate (mm/dd/yyyy): ",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            try {
                if (birthdate == null){
                    JOptionPane.showMessageDialog(null, "Birth date cannot be empty!",
                            "CamsGram", JOptionPane.ERROR_MESSAGE);
                } else if (birthdate.indexOf("/") != 2 || (birthdate.substring(3).indexOf("/") != 2)
                        || birthdate.length() != 10) {
                    JOptionPane.showMessageDialog(null,
                            "Please format your birth date correctly!", "CamsGram",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    month = Integer.parseInt(birthdate.substring(0, 2));
                    day = Integer.parseInt(birthdate.substring(3, 5));
                    year = Integer.parseInt(birthdate.substring(6, 10));
                    checking = false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please format your birthdate correctly!",
                        "CamsGram", JOptionPane.ERROR_MESSAGE);
            }
        } while (checking);
        return birthdate;
    }

    public static String showPasswordInputDialog() {
        String password;
        boolean checking = true;
        do {
            checking = true;
            password = JOptionPane.showInputDialog(null, "Create your password:",
                    "CampsGram", JOptionPane.QUESTION_MESSAGE);
            boolean char1 = (password.contains("!") || password.contains("@") || password.contains("#"));
            boolean char2 = (password.contains("$") || password.contains("%") || password.contains("^"));
            if(password.length() < 7) {
                JOptionPane.showMessageDialog(null, "Password needs to be at least 7 characters!",
                        "CamsGram", JOptionPane.ERROR_MESSAGE);
                checking = false;
            } else if(!char1 && !char2) {
                JOptionPane.showMessageDialog(null,
                        "Password needs to contain at least one special character!", "CamsGram",
                        JOptionPane.ERROR_MESSAGE);
                checking = false;
            }

        } while (!checking);
        return password;
    }

    public static void showCreatingDialog() {
        JOptionPane.showMessageDialog(null, "Creating User Profile...",
                "CampsGram", JOptionPane.INFORMATION_MESSAGE);
    }
}

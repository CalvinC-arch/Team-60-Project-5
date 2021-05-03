# Team-60-Project-5

How to Compile and Run Team 60 Project 5:

The code can be easily compiled using the IntelliJ IDE utilized by the team for the creation of the code. To run the project on one device, simply download and compile all the
project .java files. First, run ProfileServer.java. Once this is running, run ProfileClient.java. This will autoconnect to the localhost at the right port number and allow access
to the program. To run the project on multiple devices, select one device as the 'server' and one device as the 'client'. The 'server' device should have the following files, at
minimum: Account.java, IOMachine.java, Profile.java, ProfileClientHandler.java, ProfileServer.java, and ViewProfile.java. The 'client' device should have the following files, at
minimum: Account.java, EnterInfoGUI.java, FriendsListGUI.java, IOMachine.java, LoginPageGUI.java, Profile.java, ProfileClient.java, and ViewProfile.java. To start the server, run 
ProfileServer.java. For the client, edit line 26 of ProfileClient.java to contain the IP address of the server. Then, run ProfileClient.java.



Submission Locations:

Naia Echevarria - Submitted Report on Brightspace.
Calvin Carta - Submitted Vocareum Workspace.
Jeff Chen - Submitted Presentation on Brightspace.

Class Descriptions and Testing:

Account Class:  
The account class has two purposes; to allow the creation and modification of account objects, and to run the GUI related to the account object. There are two constructors, one
to...


EnterInfoGUI Class:
This class contains all the JOptionPanes needed to collect enough information to create a profile. There are two types of methods in this class: one type prompts input dialogs, and the other type formats inputted information correctly. The method that prompt the input dialogs ask for the following personal information from the user: username, phone number, email address, education information, “About” section, and lists of interests. To test this class, a main method was created that call every other method and prints out the data once it is done. To handle the potential errors in the methods, try-catch blocks were implemented in most of the cases. In the remaining cases, if statements were added to handle any possible input. A do-while loop is also implemented in methods where inputs are required, such as thein the method showUsernameInputDialog, that will prompt the user to re-enter the information until it is correctly formatted. This class is related to other classes by having its methods called by the Account.java and Profile.java classes; these classes call the methods in EnterInfoGUI.java whenever the user wants to create or edit a profile.

FriendsListGUI Class: FriendsListGUI appears whenever the view requests button in a profile is clicked. The FriendsListGUI has a run method which assembles the FriendsListGUI. The run method makes use of a timer objects, which interacts every 3 seconds with a listener to essentially refresh the screen. This way, if any user interacts with the user’s profile in a friending capacity, it will be updated automatically. The GUI consists of a back button at the top of the screen, one drop-down menu next to a rescind friend request button, another drop-down menu next to an accept button and decline button, and a third drop-down menu next to a view button and unfriend button. If the back button is clicked, the frame is disposed of and the user views the profile GUI again. If the rescind friend request button is selected, the current username selected in the first drop-down menu, along with the username of the user who selected the rescind friend request button, are passed to the ProfileClientHandler through the IOMachine. In the server, the sent friend request and friend request received are removed from the appropriate list in the appropriate profile, and then the screen is automatically updated to show this change. If the accept button is selected, the current username selected in the second drop-down menu, along with the username of the user who clicked the accept button, are passed to the ProfileClientHandler through the IOMachine. In the server, the sent friend request and friend request received are removed from the appropriate list in the appropriate profile, and each user is added to the other’s friends list. The screen then refreshes to display these changes. If the decline button is selected, the current username selected in the second drop-down menu, along with the username of the user who selected the decline button, are passed to the ProfileClientHandler through the IOMachine. In the server, the sent friend request and friend request received are removed from the appropriate list in the appropriate profile, and then the screen is automatically updated to show this change. If the view profile button is selected, the current username selected in the third drop-down menu is passed to the ProfileClientHandler through the IOMachine. The profile corresponding to this username is returned to the FriendsListGUI. As long as it is not null, this profile is then used to create a ViewProfile object, which displays the other selected user’s profile GUI with the interactive elements, such as buttons, removed. Finally, if the unfriend button is selected, the current username selected in the third drop-down menu, along with the username of the user who selected the unfriend button, are passed to the ProfileClientHandler through the IOMachine. Each user is removed from the other’s friends list in the server, and the screen refreshes to display these changes. Testing for the GUI components of FriendsListGUI.java was done by using all the elements that were supposed to be capable of functionality and comparing the result to our expected functionality


IOMachine Class:


LoginPage GUI Class: When a client connects to the server, the login page GUI is the first screen that is run. The LoginPageGUI contains a run method that assembles the login page GUI. This GUI has an email text field, a password field, an enter button, a make account button, and labels instructing the user what to do. This class has action listeners that respond to clicks of the enter button and make account button. If the enter button is clicked, processing is done to make certain that neither the email text field nor the password field is empty. It also checks to make sure the email is in a valid form and the password has at least 8 characters. If not, the text fields are reset for the user to add different inputs, and an error message pops up describing the issue. After ensuring that the inputs are valid, the program sends the inputted email to the ProfileClientHandler through an object output stream in a class called IOMachine. The ProfileClientHandler checks to see if an account with that email exists. If an account with the given email exists, it is sent back to the login page via the IOMachine. Its password is compared to the inputted password. If they match, the program has validated the existence of the account, so it displays the account GUI. If not, an error message is displayed and the process repeats. On the other hand, if the make account button is clicked, the same processing of the inputs is done as before. If they are valid, then the inputted email is sent via the IOMachine to the ProfileClientHandler. If an account with the given email exists, it is once again returned to the login page via the IOMachine; otherwise, null is returned. If there is an account associated with the given email, the program displays an error message and ask the user to enter new inputs. If there is not an account associated with the given email, the program creates a new account, send it to the ProfileClientHandler via the IOMachine, where it will be saved to the server. Because the program has successfully made a new account, it displays the account GUI. Testing for the LoginPageGUI was done by changing the email and password inputs and comparing the result of button clicks to our expected functionality.


Profile Class:
View the ProfileTestCasesClass

ProfileClient Class:


ProfileClientHandler Class: 


ProfileServer Class:


ViewProfile Class:


GUI TESTING ON EACH CLASS
LoginPageGUI.java
Testing for the LoginPageGUI was done by changing the email and password inputs and comparing the result of button clicks to our expected functionality.
•	We first tried leaving one, or both, of the input fields empty. The result was an error message dialog saying to enter both an email and a password and the input fields being reset, so the program failed correctly.
•	Then we tried inputting emails that did not contain a an ‘@’ or a ‘.’ character. Once again, the result was an error message dialog instructing us to enter a valid email and the input fields being reset, so the program failed correctly. 
•	Then we tried inputting emails that had less than 8 characters. The result was an error message dialog saying that the password must be at least 8 characters and the input fields being reset, so the program failed correctly. 
•	Then we tried inputting a valid email and password and clicking enter. The results was an error message dialog saying that no account matches this email and password and the input fields being reset, so the program failed correctly.
•	Then we tried inputting a valid email and password and clicking make new account. The result was an account GUI titled with the inputted email being displayed, so the program worked properly.
•	Then we tried inputting the same information as last time and clicking enter. The result was the account GUI titled with the inputted email being displayed, so the program worked properly.
•	Then we tried inputting the same email but a different password and clicking make new account. The result was an error message dialog saying an account with that email already exists and the input fields being reset, so the program failed correctly.
•	Then we tried inputting the same inputs as above and clicking enter. The result was an error message dialog saying that no account matches this email and password and the input fields being reset, so the program failed correctly. 
•	Then we tried inputting a new valid email and password and clicking make new account. The result was the account GUI titled with the new inputted email being displayed, so the program worked properly.

Account.java
Testing for the GUI components of Account.java was done by using all the elements that were supposed to be capable of functionality and comparing the result to our expected functionality.
•	We first clicked the add profile button. This caused numerous successive input dialogs to appear. If an input was invalid, an error message dialog appeared describing the problem appeared, and we had to submit different until the input was accepted as valid. For all the dialogs excluding the username input dialog, we could cancel or exit without making an input. However, clicking cancel or exit on the username dialog resulted in an error message dialog being displayed; we had to submit a username. Every time we clicked add profile, a profile was created. It appeared on the account GUI screen. The username for the profile appeared in the list of all profiles displayed in the profile GUI. We determined this was suitable functionality.
•	We then clicked the delete button at the bottom of the screen. This caused the account GUI frame to be disposed. The profiles associated with the account were removed from the list of all profiles in the profile GUI and the friends list, friend requests sent list, and friend requests received list in the FriendsListGUI. This was the desired functionality.
•	Then we inputted a nonexistent CSV file name and clicked import. This caused an error message dialog to appear that said that the file was invalid, followed by an input dialog where the user could input a new CSV file name. Thus, the program failed correctly.
•	Then we inputted an existing CSV file name and clicked import. This created a new profile that appeared on the account GUI screen. The username of this profile was added to the list of all profiles appearing in the profile GUI. This was the desired functionality.
•	Then we clicked on the view button for one of the existing profiles. This caused a profile GUI to appear with all the inputted information and titled with the username, which was the desired functionality.
•	Then we clicked on the delete button for one of the existing profiles. The result was that the profile was removed from the account GUI screen and the username associated with the profile was removed from the lists on other screens. This was the desired functionality.


Profile.java
Testing for the GUI components of Profile.java was done by using all the elements that were supposed to be capable of functionality and comparing the result to our expected functionality.
•	We first pressed on export. The result was an information dialog appearing that said the file was successfully exported and a CSV file appearing in my folder. This is the desired functionality.
•	We then pressed on edit. A series of input dialogs appeared. If an input was invalid, an error message dialog appeared describing the problem appeared, and we had to submit different until the input was accepted as valid. Whatever input was accepted appeared in the appropriate text areas of the profile GUI. If cancel or exit was clicked, the corresponding text areas updated to say that the specific input was not specified. This functionality is usable but could still be improved.
•	We then pressed on the view friend requests button. The result was for the Friends List GUI to appear, which is the desired functionality.
•	We then made sure that the JComboBox at the bottom of the screen was automatically updated as the number of existing profiles changed in response to users interacting with the program and changes being saved on the server.
•	We then chose one of the usernames in the JComboBox and pressed the view button. The result was for the Profile GUI for this username to appear, but with the buttons and other interactive elements removed. Since we would not want one user to be able to change another user’s profile, this was the desired functionality.
•	We then pressed the Send Friend Request button. The result was an information dialog appearing saying that the friend request was successfully sent. Additionally, the list of sent friend requests for the user who sent the request was updated to include the username of the user requested, and the list of received friend requests for the user who received the request was updated to include the username of the user who sent the request. This was the desired functionality.


FriendsListGUI.java
Testing for the GUI components of FriendsListGUI.java was done by using all the elements that were supposed to be capable of functionality and comparing the result to our expected functionality.
•	Having sent a friend request from one user to the other, we first checked the rescind friend request button. The result was an information dialog appearing saying the request was rescinded. Additionally, the list of sent friend requests for the user who rescinded the friend request was updated to exclude the username whose friend request he rescinded, and the list of received friend requests for the user whose friend request was rescinded is updated to exclude the username of the user who rescinded the friend request. This is the desired functionality.
•	Next, after sending a new friend request from one user to the other, we clicked the accept button. The result was an information dialog appearing saying the friend was accepted. Additionally, the list of received friend requests for the user who accepted the request was updated to exclude the username of the user whose request he accepted. That user was added to his friends list. Likewise, the list of sent friend requests for the user whose request was accepted was updated to exclude the username of the user who accepted his friend request. That user is added to his friends list. This was the desired functionality.
•	Next, after sending a new friend requests from one user to the other, we clicked the decline button. The result was an information dialog appearing saying the request was declined. Additionally, the list of received friend requests for the user who declined the request was updated to exclude the username of the user whose request he declined, and the list of sent requests for the user whose request was declined was updated to exclude the username of the user who declined his friend request. This is the desired functionality.
•	Next, we clicked on the username of a friend and clicked the view profile button. The result was to display a profile GUI for the user whose username was clicked on. The profile GUI is modified so that none of the interactive elements, specifically buttons and drop-down menus, are included. This is the desired functionality.
•	Next, for the friend who was selected, we clicked the unfriend button. The result was an information dialog appearing saying the user was unfriended. Additionally, the list of friends for each user was updated to exclude the username of the other user. This was desired functionality.
•	Finally, we clicked the back button. This disposed of the GUI, which was the expected functionality.








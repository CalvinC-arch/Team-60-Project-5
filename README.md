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

Class Descriptions and Testing:

Account Class:  
The account class has two purposes; to allow the creation and modification of account objects, and to run the GUI related to the account object. There are two constructors, one
to...


EnterInfoGUI Class:
This class contains all the JOptionPanes needed to collect enough information to create a profile. There are two types of methods in this class: one type prompts input dialogs, and the other type formats inputted information correctly. The method that prompt the input dialogs ask for the following personal information from the user: username, phone number, email address, education information, “About” section, and lists of interests. To test this class, a main method was created that call every other method and prints out the data once it is done. To handle the potential errors in the methods, try-catch blocks were implemented in most of the cases. In the remaining cases, if statements were added to handle any possible input. A do-while loop is also implemented in methods where inputs are required, such as thein the method showUsernameInputDialog, that will prompt the user to re-enter the information until it is correctly formatted. This class is related to other classes by having its methods called by the Account.java and Profile.java classes; these classes call the methods in EnterInfoGUI.java whenever the user wants to create or edit a profile.

FriendsListGUI Class: 


IOMachine Class:


LoginPage GUI Class:


Profile Class:
View the ProfileTestCasesClass

ProfileClient Class:


ProfileClientHandler Class: 


ProfileServer Class:


ViewProfile Class:






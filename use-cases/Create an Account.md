# Make an account


**Primary Actor:** User
**Stakeholders and interests:**
- User: Wants to have an account that they can use in order to play the game

**Preconditions:** User does not have an account
**Postconditions:** User has a valid account with a username and password
**Main Success Scenario:**
1. User runs the game and is prompted with the login screen
2. User chooses the option of making a new account
3. The user is prompted with the account registration screen
4. User provides a username and password to the system
5. System creates an account for the user and informs the user that the account has been created successfully.

**Extensions:**
* *a. The program fails
	*  1. The User runs the game again and starts over      
* 4a. The username is not valid
	* 1. The system informs the user that the username entered is not valid
	* 2. User enters a valid username
* 4b. The password is not valid
	*	1. The system informs the user that the username entered is not valid
	* 2. User enters a valid username
* 5a. Another user with the chosen username already exists.
	* 1. The system informs the user that a user with the chosen username already exists
	* 2. Take the user back to the username
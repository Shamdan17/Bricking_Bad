# Make an account

**Scope:** Game login screen

**Level:** user goal

**Primary Actor:** User
**Stakeholders and interests:**
- User: Wants to have an account that they can use in order to play the game

**Preconditions:** User does not have an account and is on the login screen
**Postconditions:** User has a valid account with a username and password

**Main Success Scenario:**
1. User chooses the option of making a new account
2. The system displays the account registration screen
3. User provides a username and password to the fields
4. System creates an account for the user and informs the user that the account has been created successfully.

**Extensions:**
* *a. The program fails
	*  1. The User runs the game again and starts over      
* 3a. The username is not valid
	* 1. The system informs the user that the username entered is not valid
	* 2. User enters a valid username
* 3b. The password is not valid
	* 1. The system informs the user that the username entered is not valid
	* 2. User enters a valid username
	* 3. System creates an account for the user and informs the user that the account has been created successfully
* 4a. Another user with the chosen username already exists.
	* 1. The system informs the user that a user with the chosen username already exists and displays login screen

**Special Requirements:**
- The password entered by the user should not be visible on the screen

**Technology and data variations:**
- Keyboard of the user might give input in varying languages

**Frequency of occurence:**

Once per user
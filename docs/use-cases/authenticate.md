# Authenticate
**Scope:** Game login screen

**Level:** user goal

**Primary Actor:** User  

**Stakeholders and interests:**  

 -   User: Wants to authenticate himself so that he can access his saved data and play the game
 
**Preconditions:** The game is initialized and on the login screen 

**Postconditions:** The user is authenticated and has access to the game 

**Main Success Scenario:**  

1.  User provides his username and password, chooses to continue
2.  Game authenticates the User and shows a welcome message 
3. 	User presses continue
4.  Game changes the board to the building mode view

**Extensions:**  

-   *2a. The username or password is incorrect
    -   1. The Game informs the user that the login credentials are incorrect
    -   2. User enters the correct information
    -   3. Game authenticates the user and shows a welcome message 
    -   4. User presses continue
    -   5. Game changes the board to the building mode view

**Special Requirements:**
- Password should be hidden from view
- Care should be taken when handling the password for security reasons

**Technology and data variations:**
- Keyboard might have caps lock on or might be in another language

**Frequency of occurence:**

Once per game session
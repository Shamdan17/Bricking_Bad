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
2.  System retrieves the account of the user and authenticates him
3.  System shows a welcome message and takes the user to the building mode

**Extensions:**  

-   *2a. The username or password is incorrect
    -   1. The System informs the user that the login credentials are incorrect
    -   2. User enters the correct information
    -   3. System authenticates the user

**Special Requirements:**
- Password should be hidden from view
- Care should be taken when handling the password for security reasons

**Technology and data variations:**
- Keyboard might have caps lock on or might be in another language

**Frequency of occurence:**

Once per game session
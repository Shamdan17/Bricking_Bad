# Save the game

**Scope:** The game

**Level:** user goal

**Primary Actor:** User  

**Stakeholders and interests:**  

 -   User: Wants to save the state of game to resume at the same state later

**Preconditions:** The game is paused.  
**Postconditions:** The game is saved according to its state at the moment of pausing.

**Main Success Scenario:**  

1.  System shows the user the option menu.
2.  User chooses the save option.
3.	System prompts user to enter a save name identifying the saved state of the game.
4.	User enters a save name to identify the save.
5.	System saves the state of the game and links it with the save name the user entered.
6.	System shows the User that the operation is successful.

**Extensions:**  

-   *a. The program fails
    -   1.  The user runs the game again.
-   *3a. The storage disk is full. 
    -   1.  System shows the user that there is no enough storage after choosing save option.
    -   2.  User frees storage and retries to save steps.
-   *5a. Save name entered by user already exists as a save name for a previously saved game. 
    -   1.  System asks the user if he wants to overwrite this save name or choose another save name.
    -   2.  User either chooses to overwrite, in which case previous save information is lost, or enter another save name.

**Special Requirements:**
- none

**Technology and data variations:**
- none

**Frequency of occurence:** 

At User's demand.
	
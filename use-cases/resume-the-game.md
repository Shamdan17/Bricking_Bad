
# Resume the game
**Scope:** Game

**Level:** user goal

**Primary Actor:** User  

**Stakeholders and interests:**  

 -   User: Wants to continue playing the game after stopping for a period of time
 
**Preconditions:** A game is in progress and is paused

**Postconditions:**  The game is resumed and everything starts functioning normally

**Main Success Scenario:**  

1.  User presses the resume button
2.  System removes the pause menu and changes the resume button back to the pause button
3.  System returns everything back to the same state it was before the game was paused.

**Extensions:**  

* *a. The program fails
	*  1. The User runs the game again and starts over 

**Special Requirements:**
- There should be a short delay after choosing resume in order for the user to get ready. 
- The resume button should either say that it is a resume button or be easily identifiable as a resume button.

**Technology and data variations:**
- Different keyboard layouts, the position of the pause button might differ

**Frequency of occurence:**

Multiple times per game
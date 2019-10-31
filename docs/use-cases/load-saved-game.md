# Load saved game

**Scope:** The Game

**Level:** User goal

**Primary Actor:** User

**Stakeholders and interests:**  
 - User: Wants to continue a previously saved game
 
**Preconditions:** User has an account and at least one game saved by the same User  
**Postconditions:** Game is resumed from the saved state.

**Main Success Scenario:**  
1. User opens the “Load Game” menu
2. User picks one of the previously saved sessions
3. The game loads that session
4. The User can press “Resume” and plays the game. 

**Extensions:**  
* *a. The program fails.
   1. The user runs the game again.
* 3b. Session File is corrupt:
   1. Game refuses to continue loading
   2. Game returns to “Load Game” menu 

**Special Requirements:**
- Access to disk space (Read permission)

**Technology and data variations:**
- Different storage devices (cloud, local, ...)

**Frequency of occurence:**

Whenever the users wishes to

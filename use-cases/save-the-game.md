# Save the game

**Primary Actor:** User
**Stakeholders and interests:**
- User: Wants to save the state of game to resume at the same state later

**Preconditions:** User is playing and the game
**Postconditions:** Game state is saved at the moment of pause
**Main Success Scenario:**
1. User pauses the game: Include pause the game
2. User chooses the save option from the pause screen
3. User types a name to identify the saved state of the game
4. User confirms the save and the game is saved

**Extensions:**
* *a. The program fails.
	* 1. The user runs the game again.
* 2a. The disk is full
	* 1. User should free storage space.
* 3a. User overwrites a previous save
	* 1. User should choose another name for save
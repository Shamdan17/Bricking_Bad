# Save the game

**Primary Actor:** User
**Stakeholders and interests:**
- User: Wants to save the state of game to resume at the same state later

**Preconditions:** User is playing and the game is paused.
**Postconditions:** Game state is saved at the moment of paus
**Main Success Scenario:**
1. User is playing the game
2. User pauses the game
3. User chooses the save option from the pause screen
4. User types a name to identify the saved state of the game
5. User confirms the save and the game is saved

**Extensions:**
* *a. The program fails.
	* 1. The user runs the game again.
* 3a. The disk is full
	* 1. User should free storage space.
* 4a. User overwrites a previous save
	* 1. User should choose another name for save
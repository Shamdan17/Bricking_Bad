# Build a Map

**Scope:** The Building Mode

**Level:** User goal

**Primary Actor:** User     

**Stakeholders and interests:**     
- User: Wants to create a new map that can be loaded in future in order to play the game

**Preconditions:** User has a valid account with a username and password    
**Postconditions:** The user-created map appears in the map list    

**Main Success Scenario:**
1. The user enters building mode by clicking the “building mode” button
2. The user interacts with the Game to specify the number of each brick type
3. The Game creates bricks on random places
4. The user moves the randomly created bricks into new positions if s/he wants
5. The user saves the map if the minimum requirements are met
6. The map is saved

**Extensions:**
* *a. The program fails
    *   1. The User runs the game again and starts over      
* 2a. The numbers don’t meet the requirements
    *   1. Change numbers to satisfy requirements
* 3a. The display cannot contains the number of bricks specified by the user
    *   1. The Game warns the user to decrease the number of bricks.
* 4a. The user tries to place a brick such it overlaps with another brick
	*   1. The Game warns the user to place the brick into another place where it doesn’t overlap with a brick.
* 5a. The minimum requirements are not met
	*   1. The Game warns the user to satisfy minimum requirements
* 6a. The disk is full so the map cannot be saved.
	*   1. Delete another map or increase the disk capacity


**Special Requirements:**
- The buttons and bricks on the screen should be visible
- Write permission for storage access

**Technology and data variations:**
- Provide different color schemes for color blind people
- Different storage devices such as cloud, local etc.

**Frequency of occurence:**

At User's demand.

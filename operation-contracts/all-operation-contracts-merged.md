# Contract CO1: requestHelpScreen

**Operation:** requestHelpScreen()    

**Cross references:** Use Cases: access-help-screen

**Preconditions:**  The Game paused the game or is on the login screen

**Postconditions:** 

* Display access help screen 

---

# Contract CO2: activateBuildingMode

**Operation:** activateBuildingMode     

**Cross references:** Use Cases: Build a Map    

**Preconditions:** The user is logged in    

**Postconditions:** 
* The display switches to the building mode screen
* The cursor of the user is in the simple brick field by default

---

# Contract CO3: moveBrickToPosition

**Operation:** moveBrickToPosition(brick : Brick, position : Position)  

**Cross references:** Use Cases: Build a Map

**Preconditions:** The user is on the building mode screen and there is at least one brick in the map   

**Postconditions:** 
* The brick places in the given position
* Brick object *brick* is get
* brick.position becomes position

---

# Contract CO4: saveMap

**Operation:** saveMap(mapName : String)    

**Cross references:** Use Cases: Build a Map   

**Preconditions:** The user is on the building mode screen  

**Postconditions:** 
* map.name becomes mapName
* The map is created

--- 

# Contract CO5: setBrickNumbers

**Operation:** setBrickNumbers(simple: integer, halfMetal: integer, mine: integer, wrapper: integer)     

**Cross references:** Use Cases: Build a Map    

**Preconditions:** The user is on the building mode screen  

**Postconditions:** 
* A Map instance *map* is created
* map is associated with the current map
* map.numSimpleBrick becomes simple
* map.numHalfMetalBrick becomes halfMetal
* map.numMineBrick becomes mine
* map.numWrapperBrick becomes wrapper

---

# Contract CO6: createAcc

**Operation:** createAcc(user: Username, password: Password)

**Cross references:** Use Cases: Create an Account

**Preconditions:** The user is on the account creation screen

**Postconditions:** 
* An *Account* instance is created 
* Account.user becomes Username
* Account.password becomes Password

---

# Contract CO7: createAcc

**Operation:** createNewAcc

**Cross references:** Use Cases: Create an Account

**Preconditions:** The user is on the login screen

**Postconditions:** 
* The Board displays the account creation screen

---

# Contract CO8: createAcc

**Operation:** createNewAcc

**Cross references:** Use Cases: Create an Account

**Preconditions:** The user is on the login screen

**Postconditions:** 
* The Board displays the account creation screen

---

# Contract CO9: hideAlien

**Operation:** hideAlien()

**Cross references:** Use Cases: Hit-Harmful-Alien

**Preconditions:** Game is not paused and at least a harmful alien is on the board.

**Postconditions:** 
* Harmful alien was removed after it had been hit by the ball.

---

# Contract CO10: movePaddle

**Operation:** movePaddle()

**Cross references:** Use Cases: Hit-Harmful-Alien

**Preconditions:** Game is not paused

**Postconditions:** 
* Paddle's position was changed according to the movement command.

--- 

# Contract CO11: showBallMovement

**Operation:** showBallMovement()

**Cross references:** Use Cases: Hit-Harmful-Alien

**Preconditions:** Game is not paused

**Postconditions:** 
* Ball's new position was shown to user.

---

# Contract CO12: loadGame

**Operation:** loadGame(saved_game: GameMetaData)    

**Cross references:** Use Cases: load-saved-game

**Preconditions:** There are already saved games

**Postconditions:** 
* The Game loads the selected saved game into the board

---

# Contract CO13: requestSavedGamesList

**Operation:** requestSavedGamesList()

**Cross references:** Use Cases: load-saved-game

**Preconditions:** There are already saved games

**Postconditions:**  
* User receives a list of saved games

---

# Contract CO14: requestResumeGame

**Operation:** requestResumeGame()

**Cross references:** Use Cases: resume-the-game

**Preconditions:** There are already a paused games

**Postconditions:** 
* The Game resumes the paused game and hides the pause screen

---

# Contract CO15: enterSavename

**Operation:** enterSaveName()

**Cross references:** Use Cases: save-the-game

**Preconditions:** user clicked save button

**Postconditions:** 
* User was prompted to submit a save name.

---

# Contract CO16: printSaveSuccessful

**Operation:** printSaveSuccessful()

**Cross references:** Use Cases: save-the-game

**Preconditions:** user submitted a save name to Game

**Postconditions:** 
* User saw a message denoting that save operation was successful.

---

# Contract CO17: showPauseMenu

**Operation:** showPauseMenu()

**Cross references:** Use Cases: save-the-game

**Preconditions:** user chooses pause menu option

**Postconditions:** 
* save menu was opened for user

---

# Contract CO18: submitSavename

**Operation:** submitSaveName(saveName)

**Cross references:** Use Cases: save-the-game

**Preconditions:** user is prompted to enter a save name

**Postconditions:** 
* save name entered by user was sent to Game

---
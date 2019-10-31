# Contract CO1: requestHelpScreen

**Operation:** requestHelpScreen()    

**Cross references:** Use Cases: access-help-screen

**Preconditions:**  The Game paused the game or is on the login screen

**Postconditions:** 

* Access help screen was displayed

---

# Contract CO2: activateBuildingMode

**Operation:** activateBuildingMode     

**Cross references:** Use Cases: Build a Map    

**Preconditions:** The user is logged in    

**Postconditions:** 
* The display switched to the building mode screen
* The cursor of the user was placed in the simple brick field by default

---

# Contract CO3: moveBrickToPosition

**Operation:** moveBrickToPosition(brick : Brick, position : Position)  

**Cross references:** Use Cases: Build a Map

**Preconditions:** The user is on the building mode screen and there is at least one brick in the map   

**Postconditions:** 
* The brick was placed in the given position
* Brick object *brick* was gotten
* brick.position becomes position

---

# Contract CO4: saveMap

**Operation:** saveMap(mapName : String)    

**Cross references:** Use Cases: Build a Map   

**Preconditions:** The user is on the building mode screen  

**Postconditions:** 
* map.name became mapName
* The map was created

--- 

# Contract CO5: setBrickNumbers

**Operation:** setBrickNumbers(simple: integer, halfMetal: integer, mine: integer, wrapper: integer)     

**Cross references:** Use Cases: Build a Map    

**Preconditions:** The user is on the building mode screen  

**Postconditions:** 
* A Map instance *map* was created
* map was associated with the current map
* map.numSimpleBrick became simple
* map.numHalfMetalBrick became halfMetal
* map.numMineBrick became mine
* map.numWrapperBrick became wrapper

---

# Contract CO6: createAcc

**Operation:** createAcc(user: Username, password: Password)

**Cross references:** Use Cases: Create an Account

**Preconditions:** The user is on the account creation screen

**Postconditions:** 
* An *Account* instance was created 
* Account.user became Username
* Account.password became Password

---

# Contract CO7: createNewAcc

**Operation:** createNewAcc

**Cross references:** Use Cases: Create an Account

**Preconditions:** The user is on the login screen

**Postconditions:** 
* The Board displayed the account creation screen

---

# Contract CO8: showAccountCreationSuccess

**Operation:** showAccountCreationSuccess

**Cross references:** Use Cases: Create an Account

**Preconditions:** The user creates an account

**Postconditions:** 
* The Board displayed account creation was successful

---

# Contract CO9: hideAlien

**Operation:** hideAlien()

**Cross references:** Use Cases: Hit-Harmful-Alien

**Preconditions:** Game is not paused and at least a harmful alien is on the board.

**Postconditions:** 
* Harmful alien instance was removed.

---

# Contract CO10: movePaddle

**Operation:** movePaddle()

**Cross references:** Use Cases: Hit-Harmful-Alien

**Preconditions:** Game is not paused

**Postconditions:** 
* Paddle.position was changed.

--- 

# Contract CO11: showBallMovement

**Operation:** showBallMovement()

**Cross references:** Use Cases: Hit-Harmful-Alien

**Preconditions:** Game is not paused

**Postconditions:** 
* Ball.position was shown to user.

---

# Contract CO12: loadGame

**Operation:** loadGame(saved_game: GameMetaData)    

**Cross references:** Use Cases: load-saved-game

**Preconditions:** There are already saved games

**Postconditions:** 
* The Game loaded the selected saved game into the board

---

# Contract CO13: requestSavedGamesList

**Operation:** requestSavedGamesList()

**Cross references:** Use Cases: load-saved-game

**Preconditions:** There are already saved games

**Postconditions:**  
* User received a list of saved games

---

# Contract CO14: requestResumeGame

**Operation:** requestResumeGame()

**Cross references:** Use Cases: resume-the-game

**Preconditions:** There are already a paused games

**Postconditions:** 
* The Game resumed the paused game and hided the pause screen

---

# Contract CO15: enterSavename

**Operation:** enterSaveName()

**Cross references:** Use Cases: save-the-game

**Preconditions:** user clicked save button

**Postconditions:** 
* Box appeared to user prompting him to enter a save name.

---

# Contract CO16: printSaveSuccessful

**Operation:** printSaveSuccessful()

**Cross references:** Use Cases: save-the-game

**Preconditions:** user submitted a save name to Game

**Postconditions:** 
* Message was shown to user notifying of successful operation.

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
* savename instance was created.
* savename instance associated with The Game. 

---
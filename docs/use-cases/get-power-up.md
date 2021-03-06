
# Get a Power-Up
**Scope:** Gameplay

**Level:** user goal

**Primary Actor:** User

**Stakeholders and interests:**  

 -   User:  Wants to acquire the power-up to be able to use it later in the game
 
**Preconditions:** User is playing the game  
**Postconditions:** User has a power-up in his inventory

**Main Success Scenario:**  

1.  User breaks a wrapper brick that contains a power-up
2.  Game releases power-up from broken wrapper brick and makes it fall.
3.  User moves the paddle to be directly below the power up: include Move The Paddle
4.  Power-up touches the paddle.
5.  Game removes the power-up and gives it to the User


**Extensions:**  

-   *a. The program fails
    -   1.  The user runs the game again.
-   *3a. The paddle misses the power-up
    -   1.  The player doesn't get the power-up
    -   2.  The game continues
-   *5a. The power-up user gets is a manual power-up
    -   1.  The power-up is added to the user's inventory
    -   2.  The power-up is automatically activated.	
-   *5b. The automatic power-up is Destructive Laser Gun
	-   1.  The power-up is automatically activated.
    -   2.  A laser gun appears at the both ends of the paddle
	-	3.	The	Destructive Laser Gun power-up is active.
-   *5c. The automatic power-up is Fireball
	-   1.  The power-up is automatically activated.
    -   2.  The ball changes to a fireball
	-	3.	The fireball damages also the bricks next to one it hits
	-	4.	The fireball can destroy metal sides of bricks in two hits.
	-	5.	The fireball return to normal when the user loses it.
-   *5d. The automatic power-up is Gang-of-balls	
	-   1.  The power-up is automatically activated.
	-   2.  After the ball hits the paddle, it multiplies by 10.
	
**Special Requirements:**
-	Any acquired manual power-ups are displayed at the inventory using icons. 
-	If the 10 balls created by the Gang-of-balls power-up move with the same speed, but with an angle equals to the ball index multiplied by 360 and divided by 10.

**Technology and data variations:**
- A keyboard is used to move the paddle.

**Frequency of occurence:**
-	In a game session, a possibility every time a wrapper brick is broken.
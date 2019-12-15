## PhysicsEngine:
Public Methods:
-	calculateNewVelocity 			
	-	Side hit
	-	Corner hit
-	calculateCollisionSlope
	-	Side hit
	-	Corner hit
-	isCollided 				
	-	Collided balls
	-	Not collided balls
	-	Collided ball with rectangle
	-	Not collided ball with rectangle
	-	Collided rectangle with rectangle
	-	Not collided rectangle with rectangle
	-	Self collision
-	relativeXDirection 			
	-	Other object to the left
	-	Other object to the right
-	relativeYDirection 			
	-	Other object to the top
	-	Other object to the bottom
-	CalculatePostCollisionVelocity
	-	Horizontal wall
	-	Vertical wall
	-	Slanted walls:
		-	Y = -x
		-	Y = x

Account Manager
Public Methods: 
-	Register
	-	Register a user that does not exist in the account manager
-	Authenticate	 			
	-	Authenticate a user that exists in the account manager
	-	Fail to Authenticate a user that does not exist in the account manager


## Storage Manager
Public Methods:
-	put	
	-	put throws illegalArgumentException when provided with null key or value		
	-	put inserts data correctly to the storage
-	get					
	-	get throws illegalArgumentException when provided with null key
	-	get returns the correct data for the given keys if it exists
-	contains				
	-	contains throws illegalArgumentException when provided with null key
	-	contains returns true for data that exists inside the storage
	-	contains returns false for data that does NOT exist inside the storage
-	Constructor				
	-	Constructor works properly when given proper name
	-	Constructor throws illegalArgumentException when provided with null name


## Board
Public Methods:					
-	Board(GameData)			
	- Constructor works properly when given proper GameData
	- Constructor throws null when provided with null GameData
-	moveAllMovables			
	- Objects on board move properly according to their move function
-	removeDestroyedMovables		
	- Objects that are marked as destroyed are removed from the board
-	movePaddleLeft			
	- Paddle moves to left on function call
-	movePaddleRight			
	- Paddle moves to Right on function call
-	rotatePaddleLeft			
	- Paddle rotates left on function call
-	rotatePaddleRight			
	- Paddle rotates right on function call
-	getData 
	- Data is properly wrapped inside a GameData instance and returned


## MapEditor  (MapBuildSession)
Public Methods:

-	addBrick	
	-  Not supported brick returns false
	- Add to negative position should return false
	- Add to appropriate position should return true
-	removeBrick				
	- Removing existing brick returns true
	- Removing non-existing brick returns false
-	moveBrick				
	- Moving Brick causing collision should return false
	- Moving Brick to empty place should return true		
-	getData				
	- Data is properly wrapped and returned as GameData instance


## Map 
Public Methods:
-	Map			
	-	Map constructor should initialize object container
-	Add			
	-	Movable shape should be added if there is no collision
	-	Movable shape should not be added if there is no collision
-	Remove		
	-	Existing movable shape should be removed
-	Move			
	-	Non-existing movable shape should not change movables container
	-	Movable shape should be moved if there is no collision
	-	Movable shape should not be moved if there is collision
-	getMovables		
	-	Test get movables
-	getData		
	-	Test get data




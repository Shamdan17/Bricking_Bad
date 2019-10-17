# Contract: setBrickNumbers

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

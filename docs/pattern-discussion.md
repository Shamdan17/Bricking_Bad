# Different Design Patterns

 ## Strategy pattern
 Strategy pattern is a way to organise common behaviour (algorithm) that can be passed to some objects at runtime, rather than using inheritance and hard coding different algorithms in each inheriting class. In our project we identified two places where the strategy pattern can be applied:
1. Colliding Objects: We will have a set of objects that can collide with each other (Alien and Ball, Paddle and Ball, etc). Some collision algorithms will cause the object  to delete itself, might cause the object to reflect, etc. Also, these algorithms are not constant in run time. A ball might have a specific collision behaviour, but after activating a power up, the ball's collision behaviour might change. This requires runtime change of behaviour, which was the main indication that we need a strategy pattern for collisions.
2. Moving Objects: Multiple objects in the map will sometimes need to move. A ball for example has to move in a straight line, an Alien might move in a different way, a mine brick will move in circles, etc... These moving behaviors can change at runtime as well. The ball will move it's path once it collides with the paddle, a moving brick will change it's direction once it collides with the wall. This goes on to show that we require runtime change of our movement behaviour. 


## Controller Pattern
In the controller pattern, a class is assigned the responsibility of receiving and handling system events. The advantage of having a controller is that it can hide the internal implementation of the system away from the user and provide them with a single access point to the system. In BrickingBad, we have a lot of user inputs such as arrow keys, keyboard short-cuts, and by having a single controller in the system, the UI can simply forward all these events to the controller for handling. Moreover, since the single controller might become bloated, we will create more controllers to which the controller delegate the work to.

## Adapter Pattern
Adapter Pattern is a way to make classes which expect a specific interface capable of communicating with another class which does not implement that specific interface via an adapter. In our project we indication one place where the adapter pattern can be applied.
1. Persistent Storage: Persistent storage can be achieved in multiple ways, using a database/flat files/etc.. We will have persistent storage adapters which will implement the StorageManager interface. This is so when we incorporate external databases (which do not implement our interface), we have no control over their implementation. So we will develop adapters for them which will allow us to “plug” them into our application. 

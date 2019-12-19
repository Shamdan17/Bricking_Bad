package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;

public class RepairingAlienBehavior implements AlienBehavior, Serializable {

    // Reference to the alien containing this behavior for specific operations
    private Alien self;
    private MovementBehavior movBeh;
    // The time stamp of the last brick added in order to know when to add a brick
    private long lastBrickAddedStamp;
    // To track the last added brick
    private Brick lastAddedBrick;
    // To track whether we added a brick in the last tick
    private boolean addedBrickLastTick = false;


    public RepairingAlienBehavior(Position pos) {
        lastBrickAddedStamp = 0;
        this.movBeh = new LinearMovement(pos, new Velocity(Constants.Protecting_Alien_Speed, 0));
    }

    @Override
    public synchronized void behave() {
        System.out.println((System.currentTimeMillis() - lastBrickAddedStamp < Constants.Repairing_Alien_Brick_Period));
        if (System.currentTimeMillis() - lastBrickAddedStamp < Constants.Repairing_Alien_Brick_Period) {
            if (!addedBrickLastTick || lastAddedBrick.isDestroyed()) {
                addedBrickLastTick = false;
                return;
            }
        }
        addedBrickLastTick = true;
        lastBrickAddedStamp = System.currentTimeMillis();
        BrickFactory bf = new BrickFactory();
        Brick br = bf.get(SpecificType.SimpleBrick, getRandomBrickPosition());
        lastAddedBrick = br;
        self.addToQueue(br);
    }

    public void setSelf(Alien self) {
        this.self = self;
        self.initializeMovementBehavior(movBeh);
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball) {
            self.setDestroyed(true);
        }
    }

    @Override
    public void setMovementBehavior(MovementBehavior movBeh) {
        this.movBeh = movBeh;
    }

    private Position getRandomBrickPosition() {
        double y = Math.random() * 0.7 * Constants.maxY;
        double x = Math.random() * 0.7 * Constants.maxX;
        return new Position(x, y);
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.RepairingAlien;
    }

    @Override
    public void move() {
        movBeh.getNextPosition();
    }

    @Override
    public String toString() {
        return "Repairing Alien at " + movBeh.getCurrentPosition() + " with velocity " + movBeh.getCurrentVelocity();
    }
}

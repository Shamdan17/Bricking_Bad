package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.movement.LinearMovement;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import static utils.Constants.Protecting_Alien_Speed;

public class RepairingAlienBehavior extends AbstractBehavior {

    // The time stamp of the last brick added in order to know when to add a brick
    private long lastBrickAddedStamp;
    // To track the last added brick
    private Brick lastAddedBrick;
    // To track whether we added a brick in the last tick
    private boolean addedBrickLastTick = false;
    // To track the number of added bricks
    private long firstBrickTimeStamp = -1;


    public RepairingAlienBehavior(Position pos) {
        super(new LinearMovement(pos, new Velocity(Protecting_Alien_Speed, 0)));
        lastBrickAddedStamp = 0;
    }

    @Override
    public void behave() {
        if (System.currentTimeMillis() - lastBrickAddedStamp < Constants.Repairing_Alien_Brick_Period) {
            if (!addedBrickLastTick || !lastAddedBrick.isDestroyed()) {
                addedBrickLastTick = false;
                return;
            }
        }
        addedBrickLastTick = true;
        if (firstBrickTimeStamp == -1) {
            firstBrickTimeStamp = System.currentTimeMillis();
        }
        lastBrickAddedStamp = System.currentTimeMillis();
        BrickFactory bf = new BrickFactory();
        Brick br = bf.get(SpecificType.SimpleBrick, getRandomBrickPosition());
        lastAddedBrick = br;
        self.addToQueue(br);
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball) {
            self.setDestroyed(true);
        }
    }

    public long getFirstBrickTimeStamp() {
        return firstBrickTimeStamp;
    }

    @Override
    public void setPosition(Position pos) {
        movBeh = new LinearMovement(pos, new Velocity(Protecting_Alien_Speed, 0));
        self.initializeMovementBehavior(movBeh);
    }

    private Position getRandomBrickPosition() {
        double y = Math.random() * 0.7 * Constants.GAME_HEIGHT;
        double x = Math.random() * Constants.GAME_WIDTH;
        return new Position(x, y);
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.RepairingAlien;
    }

    @Override
    public String toString() {
        return "Repairing Alien at " + movBeh.getCurrentPosition() + " with velocity " + movBeh.getCurrentVelocity();
    }
}

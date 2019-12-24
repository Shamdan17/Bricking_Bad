package domain.model.alien;

import domain.model.BrickPercentageListener;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.alien.behavior.AlienBehavior;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;

public class Alien extends MovableShape implements BrickPercentageListener {

    // An alien has alienBehavior
    private AlienBehavior alienBehavior;
    // How long is an alien invincible after spawning
    private int invincibilityCounter;

    private double brickPercentage = 0.01;

    public Alien(AlienBehavior beh, int length, int width) {
        super(null, length, width);
        this.alienBehavior = beh;
        beh.setSelf(this);
        this.invincibilityCounter = 50;
    }

    public void behave() {
        alienBehavior.behave();
    }

    @Override
    public void setVelocity(Velocity v) {
        getMovementBehavior().inverse();
    }

    public int getInvincibilityCounter() {
        return invincibilityCounter == 0 ? invincibilityCounter : invincibilityCounter--;
    }

    @Override
    // move and behave
    public void move() {
        alienBehavior.move();
        behave();
    }

    public void updateBrickPercentage(double brickPercentage) {
        System.out.println("Brick Percentage: " + brickPercentage);
        this.brickPercentage = brickPercentage;
    }

    public double getCurrentPercentage() {
        brickPercentage += 0.0001;
        return brickPercentage;
    }

    @Override
    public void setMovementBehavior(MovementBehavior movBeh) {
        alienBehavior.setMovementBehavior(movBeh);
    }

    public void initializeMovementBehavior(MovementBehavior movBeh) {
        super.setMovementBehavior(movBeh);
    }

    @Override
    public void addToQueue(MovableShape ms) {
        super.addToQueue(ms);
    }


    @Override
    public MovableShape copy() {
        return SerializationUtils.clone(this);
    }

    @Override
    public void collide(MovableShape obj) {
        alienBehavior.collide(obj);
        invincibilityCounter += 10;
    }

    @Override
    public Type getType() {
        return Type.Alien;
    }

    @Override
    public Shape getShape() {
        return Shape.Rectangle;
    }

    @Override
    public SpecificType getSpecificType() {
        return alienBehavior.getSpecificType();
    }


    public MovementBehavior getMovBeh() {
        return getMovementBehavior();
    }


    @Override
    public String toString() {
        return "Alien of type " + alienBehavior.getSpecificType() + " at position " + getPosition();
    }

    @Override
    public void setPosition(Position pos) {
        alienBehavior.setSelf(this);
        alienBehavior.setPosition(pos);
    }
}

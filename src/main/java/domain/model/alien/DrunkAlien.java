package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.util.ArrayList;
import java.util.List;

public class DrunkAlien extends Alien{

    private List<Alien> aliens = new ArrayList<>();

    public DrunkAlien(MovementBehavior movementBehavior, int length, int width) {
        super(movementBehavior, length, width);
    }

    public DrunkAlien(Position position, int length, int width) {
        //TODO check movement ??
        super(new LinearMovement(position, new Velocity(3 * Constants.L / 150, 0)), length, width);
    }

    @Override
    public void behave() {

    }

    @Override
    public void collide(MovableShape obj) {

    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.DrunkAlien;
    }

    @Override
    public String toString() {
        return "Drunk Alien at " + super.getPosition() + " with velocity " + super.getVelocity();
    }

    public void addAlien(Alien alien){
        aliens.add(alien);
    }

    public void removeAlien(Alien alien) {
        aliens.remove(alien);
    }
}

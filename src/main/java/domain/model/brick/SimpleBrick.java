package domain.model.brick;

import domain.Movable;
import domain.model.Ball;
import utils.Position;
import utils.Velocity;
import utils.physics.PhysicsEngine;

public class SimpleBrick extends Brick {

    public Shape getShape(){
        return Shape.Rectangle;
    }

    //flag
    private boolean destroyed = false;

    //Position and velocity
    private Position position;
    private Velocity velocity;

    public SimpleBrick(int length, int width, Position pos) {
        super(length, width);
        this.position = pos;
    }

    @Override
    public void move() {
        return;
    }

    @Override
    public Velocity getVelocity() {
        return null;
    }

    @Override
    // Since simple bricks don't move the method is not used
    public void setVelocity(Velocity ps) {
        return;
    }

    @Override
    public void collide(Movable obj) {
        if(obj instanceof Ball){
            if(PhysicsEngine.isCollided(this, obj)){
                destroyed = true;
            }
        }
    }
}

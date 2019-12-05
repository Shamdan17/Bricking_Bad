package domain.model.alien;

import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;

//TODO: Implement fully
public abstract class Alien extends MovableShape {
    private Position position;

    public Alien(MovementBehavior movBeh, int length, int width){
        super(movBeh,length,width);
    }

    public abstract void behave();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public MovableShape copy(){
        Alien copyAlien = AlienFactory.get(this.getSpecificType(),this.getPosition());
        return copyAlien;
    }
}

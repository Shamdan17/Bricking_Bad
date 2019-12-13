package domain.model.alien;

import domain.model.SpecificType;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class AlienFactory {

    public static Alien get(SpecificType type, Position pos){
        MovementBehavior movBeh = new LinearMovement(pos, new Velocity(3 * Constants.L / 150, 0));
        switch(type){
            case CooperativeAlien:
                return new CooperativeAlien(movBeh, Constants.LENGTH,Constants.WIDTH);
            case ProtectingAlien:
                return new ProtectingAlien(movBeh, Constants.LENGTH, Constants.WIDTH);
            case RepairingAlien:
                return new RepairingAlien(movBeh, Constants.LENGTH, Constants.WIDTH);
            default:
                throw new IllegalArgumentException("not alien type supplied");
        }
    }
}

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
        AlienBehavior beh;
        Alien res;
        switch(type){
//            case CooperativeAlien:
//                AlienBehavior beh = ne
//                return new CooperativeAlienBehavior(movBeh, Constants.LENGTH,Constants.WIDTH);
            case ProtectingAlien:
                beh = new ProtectingAlienBehavior(pos);
                res = new Alien(beh, Constants.LENGTH, Constants.LENGTH);
                return res;
            case RepairingAlien:
                beh = new RepairingAlienBehavior(pos);
                res = new Alien(beh, Constants.LENGTH, Constants.LENGTH);
                return res;
                //return new RepairingAlienBehavior(movBeh, Constants.LENGTH, Constants.WIDTH);
            default:
                throw new IllegalArgumentException("not alien type supplied");
        }
    }
}

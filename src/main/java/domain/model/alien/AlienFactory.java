package domain.model.alien;

import domain.model.SpecificType;
import domain.model.alien.behavior.*;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class AlienFactory {

    public static Alien get(SpecificType type, Position pos) {
        MovementBehavior movBeh = new LinearMovement(pos, new Velocity(3 * Constants.L / 150, 0));
        AlienBehavior beh;
        Alien res;
        switch (type) {
            case DrunkAlien:
                beh = new DrunkAlienBehavior(new NoMovement(pos));
                initializeDrunkAlienBehaviors(pos, beh);
                return new Alien(beh, Constants.LENGTH, Constants.LENGTH);
            case ProtectingAlien:
                beh = new ProtectingAlienBehavior(pos);
                res = new Alien(beh, Constants.LENGTH, Constants.LENGTH);
                return res;
            case RepairingAlien:
                beh = new RepairingAlienBehavior(pos);
                res = new Alien(beh, Constants.LENGTH, Constants.LENGTH);
                return res;
            case CooperativeAlien:
                beh = new CooperativeAlienBehavior(pos);
                res = new Alien(beh, Constants.LENGTH, Constants.LENGTH);
                return res;
            default:
                throw new IllegalArgumentException("not alien type supplied");
        }
    }

    private static void initializeDrunkAlienBehaviors(Position pos, AlienBehavior beh) {
        DrunkAlienBehavior dbeh = (DrunkAlienBehavior) beh;
        dbeh.addBehavior(new DualAlienBehavior(pos), 0, 0.3);
        dbeh.addBehavior(new ConfusedBehavior(pos), 0.3, 0.4);
        dbeh.addBehavior(new ProtectingAlienBehavior(pos), 0.4, 0.5);
        dbeh.addBehavior(new RepairingAlienBehavior(pos), 0.5, 0.6);
        dbeh.addBehavior(new ConfusedBehavior(pos), 0.6, 0.7);
        dbeh.addBehavior(new CooperativeAlienBehavior(pos), 0.7, 1);
    }
}

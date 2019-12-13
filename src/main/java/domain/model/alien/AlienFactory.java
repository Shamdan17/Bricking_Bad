package domain.model.alien;

import domain.model.SpecificType;
import utils.Constants;
import utils.Position;

public class AlienFactory {

    public static Alien get(SpecificType type, Position pos){
        switch(type){
            case CooperativeAlien:
        return new CooperativeAlien(pos, Constants.LENGTH,Constants.WIDTH);
            case ProtectingAlien:
                return new ProtectingAlien(pos, Constants.LENGTH, Constants.WIDTH);
            default:
                throw new IllegalArgumentException("not alien type supplied");
        }
    }
}

package domain.model.powerup;

import domain.game.Board;
import domain.model.SpecificType;

public interface Activatable {
    // activate the activatable. Only activates if it matches the provided type
    // returns true if its activated. All effects are on the given instance of
    // the board
    public boolean activate(SpecificType type, Board board);
}

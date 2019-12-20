package domain.game;

import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {

    // This class wraps all information that can be saved or sent to UI
    private final List<MovableShape> movables;

    public GameData(List<MovableShape> ms) {
        this.movables = ms;
    }

    public List<MovableShape> getMovables() {
        List<MovableShape> list = new ArrayList<>();
        for (MovableShape ms : movables) list.add(ms.copy());
        return list;
    }

    public Paddle getPaddle() {
        for (MovableShape ms : movables) {
            if (ms.getSpecificType() == SpecificType.Paddle) {
                return (Paddle) ms;
            }
        }
        return null;
    }

    public boolean equals(GameData data) {
        if (movables.size() != data.getMovables().size())
            return false;
        for (int i = 0; i < movables.size(); ++i)
            if (!movables.get(i).equals(data.getMovables().get(i)))
                return false;
        return true;
    }
}

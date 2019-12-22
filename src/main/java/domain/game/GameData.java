package domain.game;

import domain.model.Paddle;
import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {

    // This class wraps all information that can be saved or sent to UI
    private final List<MovableShape> movables;
    boolean isCopy;
    public GameData(List<MovableShape> ms, boolean isCopy) {
        this.movables = ms;
        this.isCopy = isCopy;
 }

    public List<MovableShape> getMovables() {
        if(!isCopy) return movables;
        List<MovableShape> list = new ArrayList<>();
        for (MovableShape ms : movables) list.add(ms.copy());
        return list;
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

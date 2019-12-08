package domain.mapbuild;

import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MapBuildData implements Serializable {

    List<MovableShape> movables;

    public MapBuildData(List<MovableShape> movables){
        this.movables = movables;
    }
    // TODO: return copy
    public List<MovableShape> getMovables() {
        return movables;
    }
}

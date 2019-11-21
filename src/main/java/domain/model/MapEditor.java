package domain.model;

import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.shape.MovableShape;
import utils.Position;

import java.util.List;


public class MapEditor extends Board{

    private Map map;

    public MapEditor(){
        map = new Map();
    }

    public boolean addBrick(String type, Position pos){

        Brick brick = BrickFactory.get(type,pos);
        boolean isAdded = map.add(brick,pos);

        return isAdded;
    }

    public boolean removeBrick(Position pos){
        boolean isRemoved = map.remove(pos);
        return isRemoved;
    }

    public boolean moveBrick(Position from, Position to){
        boolean isMoved = map.move(from,to);
        return isMoved;
    }

    public List<MovableShape> getMovables(){
        return map.getMovables();
    }

}

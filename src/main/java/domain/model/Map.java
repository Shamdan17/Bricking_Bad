package domain.model;

import domain.model.shape.MovableShape;
import utils.Position;
import utils.physics.PhysicsEngine;

import java.util.List;
import java.util.ArrayList;

public class Map {

    // TODO: seek adding IDs to objects

    List<MovableShape> objects;
    PhysicsEngine pEngine = PhysicsEngine.getInstance();

    public Map(){
        objects = new ArrayList();
    }

    public boolean add(MovableShape msh , Position pos){

        for(int i = 0 ; i<objects.size() ; ++i){
            if(pEngine.isCollided(msh,objects.get(i)))
                return false;
        }
        objects.add(msh);

        return true;
    }

    public boolean remove(Position pos){
        for(int i = 0 ; i < objects.size() ; ++i){
            if(pos.equals(objects.get(i).getPosition())){
                objects.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean move(Position from , Position to){

        MovableShape msh = null;

        for(int i = 0 ; i < objects.size() ; ++i){
            if(from.equals(objects.get(i).getPosition())){
                msh = objects.remove(i);
                break;
            }
        }

        if(msh == null)
            return false;

        MovableShape newMsh = msh;
        newMsh.setPosition(to);

        for(int i = 0 ; i < objects.size() ; ++i){
            if(pEngine.isCollided(newMsh,objects.get(i))){
                objects.add(msh);
                return false;
            }
        }

        objects.add(newMsh);

        return true;
    }

    public List<MovableShape> getMovables(){
        return objects;
    }


}

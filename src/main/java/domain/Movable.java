package domain;

import utils.Position;

import java.util.List;

public interface Movable {

    final static int pointRatio = 20;

    public void collide();
    public void move();
    public Position getPosition();
    public void setPosition(Position ps);
    public List<Position> getPerimeterPoints();

}

package utils.physics.math;

import utils.Position;

// Vector defines a vector struct, which has an x magnitude and a y magnitude.
public class Vector {
    int x, y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    // creates a vector pointing from a to b
    public Vector(Position a, Position b){
        this.x = b.getX()-a.getX();
        this.y = b.getY()-a.getY();
    }

    // Reverse the direction of the vector
    public static Vector invert(Vector v){
        return new Vector(-v.x, -v.y);
    }

    // Returns a normal to the direction of the vector
    public static Vector normal(Vector v){
        return new Vector(v.y, -v.x);
    }

    public boolean pointsDown(){
        return this.y < 0;
    }
    public boolean pointsUp(){
        return this.y > 0;
    }
    public boolean pointsLeft(){
        return this.x < 0;
    }
    public boolean pointsRight(){
        return this.x > 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

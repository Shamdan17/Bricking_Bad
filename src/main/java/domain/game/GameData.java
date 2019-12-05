package domain.game;

import domain.model.Ball;
import domain.model.Paddle;
import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.List;

public class GameData implements Serializable {

    // This class wraps all information that can be saved or sent to UI

    private final List<MovableShape> movables;
    private final Paddle paddle;
    private final Ball ball;

    private int livesLeft;

    public GameData(Paddle p, Ball b, List<MovableShape> ms) {
        this.paddle = p;
        this.ball = b;
        this.movables = ms;
    }

    public void setLivesLeft(int newlivesLeft){

        livesLeft = newlivesLeft;
    }

    public int getLivesLeft(){
        return livesLeft;
    }
    public List<MovableShape> getMovables(){
        return movables;
    }

    public Paddle getPaddle(){
        return (Paddle)paddle.copy();
    }

    public Ball getBall(){
        return (Ball)ball.copy();
    }

}

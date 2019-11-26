package utils;

public final class Constants {
    //paddleWidth (thickness) should be 20 pixels
    public static final int PADDLE_WIDTH = 30;
    //todo width should be %10 of the screen width and not a constant
    public static final int PADDLE_LENGTH = 200;
    public static final long SLEEP_TIME = 10;


    public static final int BALL_INITIAL_VX = 0;
    public static final int BALL_INITIAL_VY = 4;
    public static final int BALL_DIAMETER = 17;



    public static final Position defaultPosition = new Position(0,0);
    public static final Velocity defaultVelocity = new Velocity(0,0);
    public static final Velocity defaultRespawnVelocity = new Velocity(0,BALL_INITIAL_VY);

    public static final String SimpleBrick = "SimpleBrick";


    public static int maxX = 800;
    public static int maxY = 800;
    //public static final defaultBoard
}

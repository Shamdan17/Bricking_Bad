package utils;

public final class Constants {
    public static int maxX = 800;
    public static int maxY = 800;
    //paddleWidth (thickness) should be 20 pixels
    public static final int PADDLE_WIDTH = 30;
    //todo width should be %10 of the screen width and not a constant
    public static final int L = maxX/6;
    public static final long SLEEP_TIME = 5;


    public static final int BALL_INITIAL_VX = 0;
    public static final int BALL_INITIAL_VY = 2;
    public static final int BALL_DIAMETER = 17;

    public static final double PADDLE_TURNING_SPEED = 2;
    public static final double PADDLE_MOVING_SPEED = 10;
    public static final double PADDLE_RESTORING_SPEED = 0.2;


    public static final int STEP_BACK_THRESHOLD = 5;


    public static final Position defaultPosition = new Position(0,0);
    public static final Velocity defaultVelocity = new Velocity(0,0);
    public static final Velocity defaultRespawnVelocity = new Velocity(0,BALL_INITIAL_VY);

    public static final double Brick_Velocity = L /4;

    public static final String SimpleBrick = "SimpleBrick";
    //public static final defaultBoard
}

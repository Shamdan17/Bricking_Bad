package utils;

public final class Constants {
  public static final int FRAME_HEIGHT = 1000;
  public static final int FRAME_WIDTH = 1500;
  public static final int PADDLE_WIDTH = 20;
  public static final double L = FRAME_WIDTH / 10.0;
  public static final long SLEEP_TIME = 10;
  // TODO: reorganize this file
  public static final int BRICK_LENGTH = 60;
  public static final int BRICK_WIDTH = 20;
  public static final int ALIEN_LENGTH = 60;
  public static final int ALIEN_WIDTH = 20;
  public static final int RADIUS = 10;
  public static final int explosion_radius_factor = 2;
  public static final int BALL_INITIAL_VX = 0;
  public static final int BALL_INITIAL_VY = 2;
  public static final int BALL_DIAMETER = 17;
  public static final double PADDLE_TURNING_SPEED = 2;
  public static final double PADDLE_MOVING_SPEED = 10;
  public static final double PADDLE_RESTORING_SPEED = 0.2;
  public static final int STEP_BACK_THRESHOLD = 5;
  public static final Position defaultPosition = new Position(0, 0);
  public static final Velocity defaultVelocity = new Velocity(0, 0);
  public static final Velocity defaultRespawnVelocity = new Velocity(0, BALL_INITIAL_VY);
  public static final double Brick_Velocity = L / (4 * (1000 / SLEEP_TIME));
  public static final double movingProbability = 0.9;
  public static final String SimpleBrick = "SimpleBrick";
  public static final String MineBrick = "MineBrick";
  public static final int maxX = 800;
  public static final int maxY = 800;
  public static final int MIN_SIMPLE_BRICK = 75;
  public static final int MIN_HALF_METAL_BRICK = 10;
  public static final int MIN_MINE_BRICK = 10;
  public static final int MIN_WRAPPER_BRICK = 10;
  public static final int BRICK_OFFSET_X = BRICK_LENGTH + (BRICK_LENGTH / 3);
  public static final int BRICK_OFFSET_Y = BRICK_WIDTH  + (BRICK_WIDTH / 2);
  public static final int BRICK_START_X = 20;
  public static final int BRICK_START_Y = 80;
  public static final int SPACE_BETWEEN_PADDLE_BRICKS = (FRAME_HEIGHT / 4);
  public static final int BRICK_LOWER_BOUND = FRAME_HEIGHT - SPACE_BETWEEN_PADDLE_BRICKS;
  public static final int BRICK_UPPER_BOUND = 100;
  public static final int BRICK_RIGHT_BOUND = FRAME_WIDTH - BRICK_LENGTH - BRICK_OFFSET_X;
  public static final int HALF_METAL_BRICK_YLIMIT = FRAME_HEIGHT / 2;
  // public static final defaultBoard
}

package utils;

public final class Constants {

      public static int maxX = 800;
    public static int maxY = 800;
    //paddleWidth (thickness) should be 20 pixels
    public static final int PADDLE_WIDTH = 30;
    //todo width should be %10 of the screen width and not a constant
    public static final double L = maxX / 6;
    public static final long SLEEP_TIME = 10;

    // TODO: reorganize this file
    public static final int LENGTH = 60;
    public static final int WIDTH = 20;
    public static final int RADIUS = 10;

    public static final int PowerupSize = 10;

    public static final int explosion_radius_factor = 2;

    public static final int laser_ammo_count = 5;
    public static final int gangofballs_multiplier = 10;


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

  public static final int FRAME_HEIGHT = 1000;
  public static final int FRAME_WIDTH = 1500;
  // TODO: reorganize this file
  public static final int BRICK_LENGTH = 60;
  public static final int BRICK_WIDTH = 20;
  public static final int ALIEN_LENGTH = 60;
  public static final int ALIEN_WIDTH = 20;
  public static final int MIN_SIMPLE_BRICK = 75;
  public static final int MIN_HALF_METAL_BRICK = 10;
  public static final int MIN_MINE_BRICK = 10;
  public static final int MIN_WRAPPER_BRICK = 10;

  public static final int SPACE_BETWEEN_PADDLE_BRICKS = (FRAME_HEIGHT / 4);
  public static final int BRICK_LOWER_BOUND = FRAME_HEIGHT - SPACE_BETWEEN_PADDLE_BRICKS;
  public static final int BRICK_UPPER_BOUND = 100;
  public static final int BRICK_RIGHT_BOUND = FRAME_WIDTH - BRICK_LENGTH;
  public static final int HALF_METAL_BRICK_YLIMIT = 4 * FRAME_HEIGHT / 10;


    /**
     * Constants for UI
     */

    public static final String MENU_LABEL = "main";
    public static final String MAP_BUILD_LABEL = "mapbuild";
    public static final String GAME_LABEL = "game";
    public static final String LOAD_LABEL = "load";
    public static final String SAVE_LABEL = "save";
    public static final String HELP_LABEL = "help";

    public static final String SAVE_BUTTON = "Save Button";
    public static final String LOAD_BUTTON = "Load Button";
    public static final String MENU_BUTTON = "Menu Button";
    public static final String START_GAME_BUTTON = "Start Game Button";
    public static final String ADD_BRICKS_BUTTON = "Add Bricks Button";
    public static final String HELP_BUTTON = "Help Button";
    public static final String EXIT_BUTTON = "Exit Button";
    public static final String DELETE_BY_CLICK_LABEL = "Delete By Click";


    public static final String SIMPLE_BRICK_LABEL = "Simple Brick Count";
    public static final String HALF_METAL_BRICK_LABEL = "Half Metal Brick Count";
    public static final String MINE_BRICK_LABEL = "Mine Brick Count";
    public static final String WRAPPER_BRICK_LABEL = "Wrapper Brick Count";


    public static final String EMPTY_SIMPLE_BRICK_FIELD_WARNING = "Simple Brick field is empty";
    public static final String EMPTY_HALF_METAL_BRICK_FIELD_WARNING = "Half Metal brick field is empty";
    public static final String EMPTY_MINE_BRICK_FIELD_WARNING = "Mine Brick field is empty";
    public static final String EMPTY_WRAPPER_BRICK_FIELD_WARNING = "Wrapper Brick field is empty";
    public static final String BRICK_NUMBER_WARNING = "Number of Bricks does not satisfy constraints";


    public static final String PASSWORD_LABEL = "Password Label";
    public static final String USERNAME_LABEL = "Username Label";
    public static final String LOGIN_BUTTON = "Login Button";

}

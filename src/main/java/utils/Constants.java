package utils;

public final class Constants {

    private static PropertyManager propertyManager = PropertyManager.getInstance();

    private static String getProperty(String key, String def) {
        return propertyManager.getProperty(key, def);
    }

    /**
     * General
     */
    public static final int FRAME_HEIGHT = Integer.parseInt(getProperty("FRAME_HEIGHT", "1000"));
    public static final int FRAME_WIDTH = Integer.parseInt(getProperty("FRAME_WIDTH", "1500"));
    public static final int STEP_BACK_THRESHOLD = Integer.parseInt(getProperty("STEP_BACK_THRESHOLD", "5"));
    public static final double MOVING_PROBABILITY = Double.parseDouble(getProperty("MOVING_PROBABILITY", "0.9"));
    public static boolean COPY_MODE = Boolean.parseBoolean(getProperty("COPY_MODE", "true"));
    public static boolean REFERENCE_MODE = Boolean.parseBoolean(getProperty("REFERENCE_MODE", "false"));

    /**
     * Gameplay
     */

    public static final int MAX_LIVES = Integer.parseInt(getProperty("MAX_LIVES", "3"));
    public static final int MAX_GAME_TIME = Integer.parseInt(getProperty("MAX_GAME_TIME", "120"));


    /**
     * Paddle
     */
    public static final double PADDLE_MOVING_SPEED = Double.parseDouble(getProperty("PADDLE_MOVING_SPEED", "10"));
    public static final double PADDLE_RESTORING_SPEED = Double.parseDouble(getProperty("PADDLE_RESTORING_SPEED", "0.2"));
    public static final double PADDLE_TURNING_SPEED = Double.parseDouble(getProperty("PADDLE_TURNING_SPEED", "2"));
    public static final int PADDLE_WIDTH = Integer.parseInt(getProperty("PADDLE_WIDTH", "20"));
    public static final double PADDLE_LENGTH = FRAME_WIDTH / 10;
    public static final double L = FRAME_WIDTH / 10;

    /**
     * Bricks
     */
    public static final String SIMPLE_BRICK = "SimpleBrick";
    public static final String MINE_BRICK = "MineBrick";
    public static final int BRICK_LENGTH = Integer.parseInt(getProperty("BRICK_LENGTH", "60"));
    public static final int BRICK_WIDTH = Integer.parseInt(getProperty("BRICK_WIDTH", "20"));
    public static final int MINE_BRICK_DIAMETER = Integer.parseInt(getProperty("MINE_BRICK_DIAMETER", "20"));
    ;
    public static final int MIN_SIMPLE_BRICK = Integer.parseInt(getProperty("MIN_SIMPLE_BRICK", "75"));
    public static final int MIN_HALF_METAL_BRICK = Integer.parseInt(getProperty("MIN_HALF_METAL_BRICK", "10"));
    public static final int MIN_MINE_BRICK = Integer.parseInt(getProperty("MIN_MINE_BRICK", "10"));
    public static final int MIN_WRAPPER_BRICK = Integer.parseInt(getProperty("MIN_WRAPPER_BRICK", "10"));

    /**
     * Bounds
     */
    public static final int SPACE_BETWEEN_PADDLE_BRICKS = (FRAME_HEIGHT / 3);
    public static final int BRICK_LOWER_BOUND = FRAME_HEIGHT - SPACE_BETWEEN_PADDLE_BRICKS;
    public static final int BRICK_UPPER_BOUND = Integer.parseInt(getProperty("BRICK_UPPER_BOUND", "100"));
    public static final int BRICK_RIGHT_BOUND = FRAME_WIDTH - BRICK_LENGTH;
    public static final int HALF_METAL_BRICK_YLIMIT = 5 * FRAME_HEIGHT / 10;
    public static final long SLEEP_TIME = Integer.parseInt(getProperty("SLEEP_TIME", "10"));
    public static final double BRICK_VELOCITY = L / (4 * (1000 / SLEEP_TIME));

    /**
     * Ball
     */
    public static final int BALL_INITIAL_VX = Integer.parseInt(getProperty("BALL_INITIAL_VX", "0"));
    public static final int BALL_INITIAL_VY = Integer.parseInt(getProperty("BALL_INITIAL_VY", "4"));
    public static final int BALL_DIAMETER = Integer.parseInt(getProperty("BALL_DIAMETER", "17"));
    public static final Velocity DEFAULT_RESPAWN_VELOCITY = new Velocity(0, BALL_INITIAL_VY);
    public static final int BALL_RADIUS = BALL_DIAMETER / 2;
    public static final int BALL_SPAWNING_HEIGHT = Integer.parseInt(getProperty("BALL_SPAWNING_HEIGHT", "170"));

    /**
     * Powerups
     */
    public static final int EXPLOSION_RADIUS_FACTOR = Integer.parseInt(getProperty("EXPLOSION_RADIUS_FACTOR", "2"));
    public static final int POWERUP_SIZE = Integer.parseInt(getProperty("POWERUP_SIZE", "10"));
    public static final int FIREBALL_EXPLOSION_RADIUS_FACTOR = Integer.parseInt(getProperty("FIREBALL_EXPLOSION_RADIUS_FACTOR", "5"));
    public static final int LASER_AMMO_COUNT = Integer.parseInt(getProperty("LASER_AMMO_COUNT", "5"));
    public static final int GANG_OF_BALLS_MULTIPLIER = Integer.parseInt(getProperty("GANG_OF_BALLS_MULTIPLIER", "10"));
    public static final int POWERUP_TALLER_PADDLE_TIME = Integer.parseInt(getProperty("POWERUP_TALLER_PADDLE_TIME", "30"));
    public static final int POWERUP_CHEMICAL_BALL_TIME = Integer.parseInt(getProperty("POWERUP_CHEMICAL_BALL_TIME", "60"));


    /**
     * Aliens
     */
    public static final int ALIEN_LENGTH = Integer.parseInt(getProperty("ALIEN_LENGTH", "60"));
    public static final int ALIEN_WIDTH = Integer.parseInt(getProperty("ALIEN_WIDTH", "20"));


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
    public static final String EMPTY_HALF_METAL_BRICK_FIELD_WARNING =
            "Half Metal brick field is empty";
    public static final String EMPTY_MINE_BRICK_FIELD_WARNING = "Mine Brick field is empty";
    public static final String EMPTY_WRAPPER_BRICK_FIELD_WARNING = "Wrapper Brick field is empty";
    public static final String BRICK_NUMBER_WARNING = "Number of Bricks does not satisfy constraints";

    public static final String PASSWORD_LABEL = "Password Label";
    public static final String USERNAME_LABEL = "Username Label";
    public static final String LOGIN_BUTTON = "Login Button";
    // TODO: reorganize this file
    public static final int LENGTH = Integer.parseInt(getProperty("LENGTH", "60"));
    public static final int WIDTH = Integer.parseInt(getProperty("WIDTH", "20"));
    public static final int RADIUS = Integer.parseInt(getProperty("RADIUS", "10"));

    // Aliens
    public static final double Protecting_Alien_Speed = 3 * L * (SLEEP_TIME / 1000.0);
    public static final long Repairing_Alien_Brick_Period = Integer.parseInt(getProperty("Repairing_Alien_Brick_Period", "5000")); // in milliseconds


    public static final Position defaultPosition = new Position(0, 0);
    public static final Velocity defaultVelocity = new Velocity(0, 0);
    public static final Velocity defaultRespawnVelocity = new Velocity(0, BALL_INITIAL_VY);

    //public static final defaultBoard
}

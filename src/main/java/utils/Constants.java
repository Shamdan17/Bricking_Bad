package utils;

import java.awt.*;

public final class Constants {

    private static PropertyManager propertyManager = PropertyManager.getInstance();

    private static String getProperty(String key, String def) {
        return propertyManager.getProperty(key, def);
    }

    /**
     * General
     */
    public static final int GAME_HEIGHT = Integer.parseInt(getProperty("GAME_HEIGHT", "1000"));
    public static final int GAME_WIDTH = Integer.parseInt(getProperty("GAME_WIDTH", "1440"));
    public static final int MAX_X = GAME_WIDTH + 260;
    public static final int MAX_Y = GAME_HEIGHT;
    public static final int SIDE_BAR_WIDTH = Integer.parseInt(getProperty("SIDE_BAR_WIDTH", "200"));
    public static final int SIDE_BAR_X = GAME_WIDTH + 60;
    public static final int SIDE_BAR_LENGTH = GAME_HEIGHT;

    public static final int POWERUP_LABEL_LENGTH = Integer.parseInt(getProperty("POWERUP_LABEL_LENGTH", "30"));
    public static final int POWERUP_LABEL_WIDTH = Integer.parseInt(getProperty("POWERUP_LABEL_WIDTH", "60"));
    public static final int POWERUP_LENGTH = Integer.parseInt(getProperty("POWERUP_LENGTH", "150"));
    public static final int POWERUP_WIDTH = Integer.parseInt(getProperty("POWERUP_WIDTH", "180"));
    public static final int POWERUP_X = Integer.parseInt(getProperty("POWERUP_X", "10"));
    public static final int POWERUP_LEFT_X = POWERUP_X + 20;

    public static final int MAGNET_POWERUP_Y = Integer.parseInt(getProperty("MAGNET_POWERUP_Y", "0"));
    public static final int MAGNET_LEFT_Y = Integer.parseInt(getProperty("MAGNET_LEFT_Y", "100"));

    public static final int TALLER_POWERUP_Y = Integer.parseInt(getProperty("TALLER_POWERUP_Y", "200"));
    public static final int TALLER_LEFT_Y = Integer.parseInt(getProperty("TALLER_LEFT_Y", "300"));

    public static final int CHEMICAL_POWERUP_Y = Integer.parseInt(getProperty("CHEMICAL_POWERUP_Y", "400"));
    public static final int CHEMICAL_LEFT_Y = Integer.parseInt(getProperty("CHEMICAL_LEFT_Y", "500"));
    public static final int LASER_POWERUP_Y = Integer.parseInt(getProperty("LASER_POWERUP_Y", "600"));
    public static final int LASER_LEFT_Y = Integer.parseInt(getProperty("LASER_LEFT_Y", "700"));

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
    public static final double PADDLE_LENGTH = GAME_WIDTH / 10;
    public static final double L = GAME_WIDTH / 10;

    /**
     * Bricks
     */
    public static final String SIMPLE_BRICK = "SimpleBrick";
    public static final String MINE_BRICK = "MineBrick";
    public static final int BRICK_LENGTH = Integer.parseInt(getProperty("BRICK_LENGTH", "60"));
    public static final int BRICK_WIDTH = Integer.parseInt(getProperty("BRICK_WIDTH", "20"));
    public static final int MINE_BRICK_DIAMETER = Integer.parseInt(getProperty("MINE_BRICK_DIAMETER", "20"));
    public static final int MIN_SIMPLE_BRICK = Integer.parseInt(getProperty("MIN_SIMPLE_BRICK", "75"));
    public static final int MIN_HALF_METAL_BRICK = Integer.parseInt(getProperty("MIN_HALF_METAL_BRICK", "10"));
    public static final int MIN_MINE_BRICK = Integer.parseInt(getProperty("MIN_MINE_BRICK", "10"));
    public static final int MIN_WRAPPER_BRICK = Integer.parseInt(getProperty("MIN_WRAPPER_BRICK", "10"));

    /**
     * Bounds
     */
    public static final int SPACE_BETWEEN_PADDLE_BRICKS = (GAME_HEIGHT / 3);
    public static final int BRICK_LOWER_BOUND = GAME_HEIGHT - SPACE_BETWEEN_PADDLE_BRICKS;
    public static final int BRICK_UPPER_BOUND = Integer.parseInt(getProperty("BRICK_UPPER_BOUND", "50"));
    public static final int BRICK_RIGHT_BOUND = GAME_WIDTH;
    public static final int HALF_METAL_BRICK_YLIMIT = 5 * GAME_HEIGHT / 10;
    public static final long SLEEP_TIME = Integer.parseInt(getProperty("SLEEP_TIME", "10"));
    public static final double BRICK_VELOCITY = L / (4 * (1000 / SLEEP_TIME));

    /**
     * Ball
     */
    public static final int BALL_INITIAL_VX = Integer.parseInt(getProperty("BALL_INITIAL_VX", "0"));
    public static final int BALL_INITIAL_VY = Integer.parseInt(getProperty("BALL_INITIAL_VY", "4"));
    public static final int BALL_DIAMETER = Integer.parseInt(getProperty("BALL_DIAMETER", "17"));
    public static final int BALL_RADIUS = BALL_DIAMETER / 2;
    public static final int BALL_SPAWNING_HEIGHT = Integer.parseInt(getProperty("BALL_SPAWNING_HEIGHT", "170"));
    public static final Velocity DEFAULT_RESPAWN_VELOCITY = new Velocity(0, BALL_INITIAL_VY);

    /**
     * Powerups
     */
    public static final int EXPLOSION_RADIUS_FACTOR = Integer.parseInt(getProperty("EXPLOSION_RADIUS_FACTOR", "2"));
    public static final int POWERUP_SIZE = Integer.parseInt(getProperty("POWERUP_SIZE", "40"));
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
     * Storage provider
     */
    public static final String STORAGE_PROVIDER = getProperty("STORAGE_PROVIDER", "DEFAULT");


    /**
     * Constants for UI
     */
    public static final String MENU_LABEL = "main";

    public static final String MAP_BUILD_LABEL = "mapbuild";
    public static final String GAME_LABEL = "game";
    public static final String GAME_BUTTON = "Return to Game";
    public static final String GAME_LOAD_LABEL = "game load";
    public static final String GAME_SAVE_LABEL = "game save";
    public static final String MAP_LOAD_LABEL = "map load";
    public static final String MAP_SAVE_LABEL = "map save";
    public static final String HELP_LABEL = "help";
    public static final String PAUSE_BUTTON = "Pause";
    public static final String PAUSE_LABEL = "pause";
    public static final String CANCEL_BUTTON = "Cancel";

    public static final String ENTER_SAVE_NAME = "Enter Save Name";

    public static final String SAVE_BUTTON = "Save";
    public static final String LOAD_BUTTON = "Load";
    public static final String MENU_BUTTON = "Menu";
    public static final String START_GAME_BUTTON = "Start Game";
    public static final String ADD_BRICKS_BUTTON = "Add Bricks";
    public static final String HELP_BUTTON = "Help";
    public static final String EXIT_BUTTON = "Exit";
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
    public static final String NO_PREVIOUS_SAVE_WARNING = "NO Previous Saves Available";
    public static final String NOT_VALID_MAP_WARNING = "Cannot start Game without valid number of bricks";

    public static final int MAIN_MENU_BUTTON_LENGTH = Integer.parseInt(getProperty("MAIN_MENU_BUTTON_LENGTH", "60"));
    public static final int MAIN_MENU_BUTTON_WIDTH = Integer.parseInt(getProperty("MAIN_MENU_BUTTON_WIDTH", "600"));
    public static final int TEXT_INPUT_LENGTH = Integer.parseInt(getProperty("TEXT_INPUT_LENGTH", "50"));
    public static final int TEXT_INPUT_WIDTH = Integer.parseInt(getProperty("TEXT_INPUT_WIDTH", "200"));
    public static final int BUTTON_LENGTH = Integer.parseInt(getProperty("BUTTON_LENGTH", "50"));
    public static final int BUTTON_WIDTH = Integer.parseInt(getProperty("BUTTON_WIDTH", "200"));
    public static final int PAUSE_BUTTON_WIDTH = Integer.parseInt(getProperty("PAUSE_BUTTON_WIDTH", "80"));
    public static final int PAUSE_BUTTON_LENGTH = Integer.parseInt(getProperty("PAUSE_BUTTON_LENGTH", "40"));

    public static final int LOAD_MENU_WIDTH = Integer.parseInt(getProperty("LOAD_MENU_WIDTH", "500"));
    public static final int LOAD_MENU_LENGTH = Integer.parseInt(getProperty("LOAD_MENU_LENGTH", "500"));
    public static final int LOAD_MENU_FONT_SIZE = Integer.parseInt(getProperty("LOAD_MENU_FONT_SIZE", "30"));
    public static final String LOAD_MENU_FONT = "consolas";

    public static final String TALLER_POWERUP_BUTTON = "Taller Paddle";
    public static final String MAGNET_POWERUP_BUTTON = "Magnetic Paddle";
    public static final String CHEMICAL_POWERUP_BUTTON = "Chemical Ball";
    public static final String LASER_POWERUP_BUTTON = "Laser";
    public static final Font POWERUP_FONT = new Font("consolas", Font.BOLD, 30);
    public static final String POWERUP_LEFT_DEFAULT = "0 Left";

    public static final int SCORE_X = Integer.parseInt(getProperty("SCORE_X", "100"));
    public static final int TIME_LEFT_X = Integer.parseInt(getProperty("TIME_LEFT_X", "700"));
    public static final int LIVES_LEFT_X = Integer.parseInt(getProperty("LIVES_LEFT_X", "1300"));
    public static final int DEFAULT_WIDTH = Integer.parseInt(getProperty("DEFAULT_WIDTH", "300"));
    public static final int DEFAULT_LENGTH = Integer.parseInt(getProperty("DEFAULT_LENGTH", "40"));
    public static final Font DEFAULT_FONT = new Font("monospaced", Font.PLAIN, 20);
    public static final Font VERY_BIG_FONT = new Font("monospaced", Font.PLAIN, 120);

    public static final int VERDICT_X = Integer.parseInt(getProperty("VERDICT_X", "400"));
    public static final int VERDICT_Y = Integer.parseInt(getProperty("VERDICT_Y", "50"));
    public static final int VERDICT_WIDTH = Integer.parseInt(getProperty("VERDICT_WIDTH", "800"));
    public static final int VERDICT_LENGTH = Integer.parseInt(getProperty("VERDICT_LENGTH", "800"));

    public static final String PASSWORD_LABEL = "Password:";
    public static final String USERNAME_LABEL = "Username:";
    public static final String LOGIN_BUTTON = "Login";
    public static final String CREATE_ACCOUNT_LABEL = "create account";
    public static final String LOGIN_LABEL = "login";
    public static final String CREATE_ACCOUNT_BUTTON = "create account";
    public static final String LOGOUT_BUTTON = "Log Out";
    public static final int LOGIN_INPUT_LENGTH = Integer.parseInt(getProperty("LOGIN_INPUT_LENGTH", "30"));
    public static final int LOGIN_INPUT_WIDTH = Integer.parseInt(getProperty("LOGIN_INPUT_WIDTH", "200"));

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

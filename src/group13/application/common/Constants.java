package group13.application.common;

import javafx.event.EventType;

/**
 * @author longyu
 * @date 2022/3/25 23:30
 */
public class Constants {
    public static final int GAME_LEVEL_START = 1;

    public static final int GAME_LEVEL_MAX = 3;

    public static final int DEFAULT_NUMBER_OF_LIVES = 20;

    public static final int SCENE_WIDTH = 800;

    public static final int SCENE_HEIGHT = 600;

    public static final int BASE_NUMBER_OF_ASTEROIDS = 3;

    public static final int BASE_FREQ_OF_ALIEN_SHIP = 20;

    public static final int MULTIPLIER_OF_ASTEROIDS = 2;

    public static final int FREQ_MULTIPLIER_OF_ALIEN_SHIP = 2;

    public static final EventType COLLISION = new EventType("collision");

    public static final EventType PLAYER_SHIP_DESTROYED = new EventType("player ship destroyed");
}

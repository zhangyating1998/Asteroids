package group13.application.common;

/**
 * @author longyu
 * @date 2022/3/26 14:19
 */
public enum Events {
    // control logic should check the crash and destroy objects accordingly
    CRASH,

    // control logic should destroy the current play ship, decrement the player lives
    // and create a new player ship
    PLAYER_SHIP_CRASH,

    // control logic should raise the game level and recreate the objects
    // then start the new level
    PASS_LEVEL,

    // control logic should show the 'GAME OVER' screen
    // enemy ship should not be created again
    GAME_OVER
}

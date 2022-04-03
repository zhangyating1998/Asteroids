package group13.application.game.scene;

import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.ship.PlayerShip;
import group13.application.game.events.handlers.CollisionEventHandler;
import group13.application.game.events.handlers.PlayerKeyEventHandler;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Random;

import static group13.application.common.Constants.*;

/**
 * This scene manages the life cycle of a game.
 */
public class PlayScene extends BaseScene {
    private int numberOfLives = DEFAULT_NUMBER_OF_LIVES;
    private int gameLevel = GAME_LEVEL_START;
    private EventHandler playerKeyHandler;
    private final static Random random = new Random();

    @Override
    public void createScene() {
//        // enemy ship should be able to move randomly, and shoot towards player ship
//        // TODO enemy ship should be add into scene after a period of time
//        Ship enemyShip = new EnemyShip(100, 500);

        createSceneByGameLevel(PlaySceneParams.getConfig(this.gameLevel));
        this.addEventFilter(COLLISION, new CollisionEventHandler(this));
    }

    private void createSceneByGameLevel(PlaySceneParams config) {
        for (int i = 0; i < config.getNumberOfAsteroids(); i++) {
            LargeAsteroid largeAsteroid = new LargeAsteroid();
            largeAsteroid.Translate(random.nextInt(SCENE_WIDTH), random.nextInt(SCENE_HEIGHT),
                    random.nextInt(SCENE_WIDTH), random.nextInt(SCENE_HEIGHT), 80);
            getPane().getChildren().add(largeAsteroid);
        }

        createNewPlayerShip();
    }

    private void createNewPlayerShip() {
        // TODO the location of the player ship should be calculated based on the other objects in the scene
        // set the location of the player ship

        // player ship should be able to move by keyboard, and can shoot bullets.
        PlayerShip playerShip = new PlayerShip(250, 200);
        this.getPane().getChildren().addAll(playerShip);

        this.playerKeyHandler = new PlayerKeyEventHandler(playerShip);
        this.addEventFilter(KeyEvent.KEY_PRESSED, this.playerKeyHandler);
    }

    public void upgrade() {
        resetScene();
        System.out.println("Current game level: " + this.gameLevel);
        this.gameLevel++;
        System.out.println("Upgraded game level: " + this.gameLevel);
        // create a new scene of the next level
        createSceneByGameLevel(PlaySceneParams.getConfig(this.gameLevel));
    }

    public void end() {
        System.out.println("GAME OVER!");
    }

    public void decrementLives() {
        System.out.println("Current number of lives: " + numberOfLives);
        if (numberOfLives > 1) {
            removePlayerKeyListener();
            numberOfLives--;
            System.out.println("Number of lives after decrement: " + numberOfLives);
            createNewPlayerShip();
        } else {
            System.err.println("Number of lives is 0. Game over!");
        }
    }

    /**
     * Reset the current scene including:
     * 1. remove the KeyEvent listener for the player from the scene to avoid duplicate listeners
     * 2. create a new pane for the scene (and disable the current pane)
     */
    private void resetScene() {
        removePlayerKeyListener();
        this.newPane();
    }

    /**
     * Remove the player key handler from event filter
     */
    private void removePlayerKeyListener() {
        this.removeEventFilter(KeyEvent.KEY_PRESSED, playerKeyHandler);
    }

}

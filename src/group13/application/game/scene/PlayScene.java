package group13.application.game.scene;

import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.ship.EnemyShip;
import group13.application.characters.ship.PlayerShip;
import group13.application.game.events.handlers.CollisionEventHandler;
import group13.application.game.events.handlers.PlayerKeyEventHandler;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static group13.application.common.Constants.*;

/**
 * This scene manages the life cycle of a game.
 */
public class PlayScene extends BaseScene {
    private int numberOfLives = DEFAULT_NUMBER_OF_LIVES;
    private int gameLevel = GAME_LEVEL_START;
    private EventHandler playerKeyHandler;
    private final static Random random = new Random();
    private long lastSecondsAlienShipAdded = 0;
    private PlaySceneParams playSceneParams;
    private boolean isGameContinue = true;

    @Override
    public void createScene() {
        this.playSceneParams = PlaySceneParams.getConfig(this.gameLevel);
        createSceneByGameLevel();
        this.addEventFilter(COLLISION, new CollisionEventHandler(this));
    }

    private void createSceneByGameLevel() {
        for (int i = 0; i < this.playSceneParams.getNumberOfAsteroids(); i++) {
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

    private void createNewAlienShip() {
        EnemyShip alienShip = new EnemyShip(random.nextInt(SCENE_WIDTH), random.nextInt(SCENE_HEIGHT));
        this.getPane().getChildren().addAll(alienShip);
    }

    public void upgrade() {
        // some events may arrive later after game is over
        if (this.isGameContinue) {
            if (this.gameLevel == GAME_LEVEL_MAX) {
                pass();
                return;
            }

            resetScene();
            System.out.println("Current game level: " + this.gameLevel);
            this.gameLevel++;
            this.playSceneParams = PlaySceneParams.getConfig(this.gameLevel);
            System.out.println("Upgraded game level: " + this.gameLevel);
            // create a new scene of the next level
            createSceneByGameLevel();
        }
    }

    public void end() {
        System.out.println("GAME OVER!");
        this.isGameContinue = false;
    }

    public void pass() {
        System.err.println("Congratulation! You have passed all the levels!");
        this.isGameContinue = false;
    }

    public void decrementLives() {
        if (this.isGameContinue) {
            System.out.println("Current number of lives: " + numberOfLives);
            if (numberOfLives > 1) {
                removePlayerKeyListener();
                numberOfLives--;
                System.out.println("Number of lives after decrement: " + numberOfLives);
                createNewPlayerShip();
            } else {
                end();
            }
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
        this.lastSecondsAlienShipAdded = 0;
    }

    /**
     * Remove the player key handler from event filter
     */
    private void removePlayerKeyListener() {
        this.removeEventFilter(KeyEvent.KEY_PRESSED, playerKeyHandler);
    }

    /**
     * Check in every frame if an alien ship should be added into the scene
     *
     * @param timeInNanoSeconds
     */
    public void addAlienShips(long timeInNanoSeconds) {
        long currentTimeInSeconds = TimeUnit.SECONDS.convert(timeInNanoSeconds, TimeUnit.NANOSECONDS);
        if (this.lastSecondsAlienShipAdded == 0) {
            this.lastSecondsAlienShipAdded = currentTimeInSeconds;
        } else {
            if (this.isGameContinue
                    && this.lastSecondsAlienShipAdded < currentTimeInSeconds
                    && this.lastSecondsAlienShipAdded + this.playSceneParams.getFrequencyOfAlienShip()
                        == currentTimeInSeconds) {
                System.out.println("Adding new alien ship.");
                createNewAlienShip();
                this.lastSecondsAlienShipAdded = currentTimeInSeconds;
            }
        }
    }
}

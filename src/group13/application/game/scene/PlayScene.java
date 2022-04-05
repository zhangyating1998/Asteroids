package group13.application.game.scene;

import group13.application.characters.Bullet;
import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.ship.EnemyShip;
import group13.application.characters.ship.PlayerShip;
import group13.application.characters.ship.Ship;
import group13.application.game.events.handlers.CollisionEventHandler;
import group13.application.game.events.handlers.DummyGameEventHandler;
import group13.application.game.events.handlers.PlayerKeyEventHandler;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import static group13.application.common.Constants.COLLISION;
import static group13.application.common.Constants.DEFAULT_NUMBER_OF_LIVES;

/**
 * This scene manages the life cycle of a game.
 */
public class PlayScene extends BaseScene {
    private int numberOfLives = DEFAULT_NUMBER_OF_LIVES;
    private int gameLevel = DEFAULT_NUMBER_OF_LIVES;
    List<Bullet> bullets = new ArrayList<>();


    @Override
    public void createScene() {
        // Asteroids should be able to move randomly, and split when crashes
        LargeAsteroid largeAsteroid = new LargeAsteroid();
        largeAsteroid.Translate(0, 0, 700, 500, 80);

        // enemy ship should be able to move randomly, and shoot towards player ship
        // TODO enemy ship should be add into scene after a period of time
        Ship enemyShip = new EnemyShip(100, 500);

        createNewPlayerShip();

        this.getPane().getChildren().addAll(
                largeAsteroid,
                enemyShip
        );

        this.addEventFilter(COLLISION, new CollisionEventHandler(this));
        this.addEventFilter(KeyEvent.KEY_PRESSED, new DummyGameEventHandler(getPane()));
    }

    private void createNewPlayerShip() {
        // TODO the location of the player ship should be calculated based on the other objects in the scene
        // set the location of the player ship

        // player ship should be able to move by keyboard, and can shoot bullets.
        PlayerShip playerShip = new PlayerShip(250, 200);
        this.getPane().getChildren().addAll(playerShip);

        this.addEventFilter(KeyEvent.KEY_PRESSED, new PlayerKeyEventHandler(playerShip));
    }

    public void upgrade() {
        this.gameLevel++;
        // create a new scene of the next level
    }

    public void end() {
        System.out.println("GAME OVER!");
    }

    public void decrementLives() {
        System.out.println("Current number of lives: " + numberOfLives);
        if (numberOfLives > 1) {
            numberOfLives--;
            System.out.println("Number of lives after decrement: " + numberOfLives);

            createNewPlayerShip();
        } else {
            System.err.println("Number of lives is 0. Game over!");
        }
    }

}

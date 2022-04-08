package group13.application.game;

import group13.application.characters.ship.PlayerShip;
import group13.application.game.scene.PlayScene;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

/**
 * GameEngine includes all the functions of this game application, for instance, welcome page, game scenes and
 * rankings, etc.
 *
 * @author longyu
 * @date 2022/3/28 23:53
 */
public class GameEngine {
    private Stage stage;
    private static int HIGH_SCORE;

    public GameEngine(Stage stage) {
        this.stage = stage;
        stage.setTitle("Asteroids");
        stage.show();

        // Start the game Timeline, which is running forever
        // This is used to detect game events such as collision
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.INDEFINITE);
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void welcome() {
        // TODO
//        createStartScene();
        this.newGame();
    }

    public void newGame() {
        PlayScene scene = new PlayScene();
        stage.setScene(scene);

        AnimationTimer timer = new PlaySceneController(scene);
        timer.start();
    }

}

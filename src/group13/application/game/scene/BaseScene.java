package group13.application.game.scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static group13.application.common.Constants.SCENE_HEIGHT;
import static group13.application.common.Constants.SCENE_WIDTH;

/**
 * The BaseScene creates the scene with default parameters, and creates the root node of the scene.
 * In the constructor, an abstract method createScene() is called.
 */
public abstract class BaseScene extends Scene {
    private Pane pane;

    /**
     * Convenient constructor for subclasses.
     */
    public BaseScene() {
        // create the default scene
        super(new Pane(), SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);
        this.pane = (Pane) this.getRoot();
        this.createScene();
    }

    /**
     * Create the characters and add them to the root node.
     * The concrete implementation depends on the subclass.
     */
    public abstract void createScene();

    public Pane getPane() {
        return pane;
    }
}

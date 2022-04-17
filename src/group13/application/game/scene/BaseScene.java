package group13.application.game.scene;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import static group13.application.common.Constants.SCENE_HEIGHT;
import static group13.application.common.Constants.SCENE_WIDTH;

/**
 * The BaseScene creates the scene with default parameters, and creates the root node of the scene.
 * In the constructor, an abstract method createScene() is called.
 */
public abstract class BaseScene extends Scene {
    private static Pane pane;

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

    public static Pane getPane() {
        return pane;
    }

    /**
     * Disable the current pane and create a new pane for the scene
     */
    public void newPane() {
        this.pane.setDisable(true);
        this.pane = new Pane();
        this.setRoot(this.pane);
    }

    public Button getButton(Point2D position, String text) {
        String hoverStyle = "-fx-background_color:white; -fx-text-fill: black";
        String exitStyle = "-fx-background-color: black; -fx-border-color: white; -fx-text-fill: white";
        Button button = new Button(text);
        button.setLayoutX(position.getX());
        button.setLayoutY(position.getY());
        button.setWrapText(false);
        button.setShape(new Polygon(100.0, 250.0, 700.0, 250.0, 600.0, 350.0, 0.0, 350.0));
        button.setStyle(exitStyle);
        button.setPadding(new Insets(5, 30, 5, 30));
        button.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
        button.setOnMouseEntered(e-> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(exitStyle));
        return button;
    }
}

package group13.application.game.scene;

import group13.application.game.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

/**
 * @author Yating
 * @date 2022/4/09 14:19
 */
public class InstructionScene extends BaseScene{
    GameEngine gameEngine;

    public InstructionScene(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void createScene() {
        disPlayInstruction();
        backButton();
        this.getPane().setStyle("-fx-background-color: black");
    }

    private void disPlayInstruction() {
        Text title = new Text(250, 50,"Player Instructions");
        title.setTextAlignment(TextAlignment.JUSTIFY);
        title.setFill(Color.WHITE);
        title.setLineSpacing(10.0);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 30));
        getPane().getChildren().addAll(title);

        Text controlShip = new Text( 10, 150,"How to control the ship?");
        controlShip.setTextAlignment(TextAlignment.LEFT);
        controlShip.setLineSpacing(10.0);
        controlShip.setFill(Color.GREEN);
        controlShip.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        getPane().getChildren().addAll(controlShip);

        Text LifeScore = new Text(10, 250, "Lives and Scores");
        LifeScore.setTextAlignment(TextAlignment.LEFT);
        LifeScore.setFill(Color.GREEN);
        LifeScore.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        getPane().getChildren().addAll(LifeScore);

    }
    private void backButton() {
        Button button = getButton(new Point2D(330, 450), "Back");
        getPane().getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.welcome();
            }
        });
    }
}

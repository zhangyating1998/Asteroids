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
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 30));
        getPane().getChildren().addAll(title);

        Text controlShipTitle = new Text( 20, 100,"1. Control the ship ");
        Text controlShipContent = new Text(25, 140, "Press up arrow key to speed up. \nPress Left arrow key to left-rotate the ship. \nPress Left arrow key to left-rotate the ship. \nPress the WhiteSpace key to shoot a bullet ");
        controlShipTitle.setFill(Color.WHITE);
        controlShipTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        controlShipContent.setFill(Color.GREEN);
        controlShipContent.setFont(Font.font("Verdana", FontPosture.ITALIC, 18));
        controlShipContent.setTextAlignment(TextAlignment.LEFT);
        controlShipContent.setLineSpacing(7);
        getPane().getChildren().addAll(controlShipTitle, controlShipContent);

        Text LifeScore = new Text(20, 300, "2. Lives and Scores");
        Text lifeScoreContent = new Text(25, 340, "You have three initial total lives, but if your ship collides with any of the other\nelements (bullets, asteroids or the alien ship), you will lose one life. " +
                "The score \nyou can gain after shooting an asteroid is based on the size of it :\nBig One ------------------------- 10 points\nMedium one -------------------- 15 points\nSmall one ---------------------- 20 points\n" +
                "The 10 best scores are recorded and you can check if your name is on the list!");
        LifeScore.setFill(Color.WHITE);
        lifeScoreContent.setFill(Color.GREEN);
        lifeScoreContent.setTextAlignment(TextAlignment.LEFT);
        lifeScoreContent.setFont(Font.font("Verdana", FontPosture.ITALIC, 18));
        lifeScoreContent.setLineSpacing(7);
        LifeScore.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        getPane().getChildren().addAll(LifeScore, lifeScoreContent);

    }
    private void backButton() {
        Button button = getButton(new Point2D(330, 540), "Back");
        getPane().getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.welcome();
            }
        });
    }
}

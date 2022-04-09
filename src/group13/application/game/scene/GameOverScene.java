package group13.application.game.scene;

import group13.application.game.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOverScene extends BaseScene{
    GameEngine gameEngine;
    Button returnMainHall;

    public GameOverScene(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        setReturnButton();
    }

    @Override
    public void createScene() {
        setGameOver();
        this.getPane().setStyle("-fx-background-color: black");
    }

    private void setGameOver() {
        Text t = new Text(130, 280, "Game Over !");
        t.setFill(Color.RED);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 80);
        t.setFont(font);
        this.getPane().getChildren().addAll(t);
    }

    private void setReturnButton () {
        this.returnMainHall = getButton(new Point2D(310, 350), "Main Hall");
        this.getPane().getChildren().add(returnMainHall);
        returnMainHall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.welcome();
            }
        });
    }
}

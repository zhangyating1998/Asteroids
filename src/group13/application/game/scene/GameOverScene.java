package group13.application.game.scene;

import group13.application.game.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOverScene extends BaseScene{
    GameEngine gameEngine;
    Button returnMainHall;
    int score_;
    public GameOverScene(GameEngine gameEngine, int score_) {
        this.gameEngine = gameEngine;
        this.score_ = score_;
    }

    @Override
    public void createScene() {
        setGameOver();
        disPlayScore();
        setReturnButton();
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
        this.returnMainHall = getButton(new Point2D(310, 430), "Main Hall");
        this.getPane().getChildren().add(returnMainHall);
        returnMainHall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.welcome();
            }
        });
    }
    private void disPlayScore() {
        System.out.println("display"+score_);
        Text t = new Text(220, 350, "Your Score Is: "+ Integer.toString(score_));
        t.setFill(Color.WHITE);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 40);
        t.setFont(font);
        float age = 0.4f;
        this.getPane().getChildren().addAll(t);
    }

}

package group13.application.game.scene;

import group13.application.game.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Yating
 * @date 2022/4/09 14:19
 */
public class ScoreRecordScene extends BaseScene{
    GameEngine gameEngine;

    public ScoreRecordScene(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void createScene() {
        displayScore();
        backButton();
        this.getPane().setStyle("-fx-background-color: black");
    }

    private void displayScore() {
        URL url = PlayScene.class.getClassLoader().getResource("Score.txt");
        File file = new File(url.getPath());
        try {
            Scanner scanner = new Scanner(file);
            int start = -10;
            int rank = 0;
            while(scanner.hasNextLine()){
                Text text = new Text(10, start, Integer.toString(rank)+". "+scanner.nextLine());
                text.setFill(Color.GREEN);
                Font font = Font.font("Verdana", FontPosture.ITALIC, 20);
                text.setTextAlignment(TextAlignment.LEFT);
                text.setFont(font);
                rank+=1;
                start+=50;
                this.getPane().getChildren().addAll(text);
            }
        }catch(IOException e){
            System.out.println("fail to read score");
        }
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

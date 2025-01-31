package group13.application.game.scene;

import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.asteroid.MediumAsteroid;
import group13.application.characters.asteroid.SmallAsteroid;
import group13.application.game.GameEngine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Yating
 * @date 2022/4/09 14:19
 */
public class WelcomeScene extends BaseScene {
    Button NewGame;
    Button Rank;
    Button Instruction;
    GameEngine gameEngine;

    public WelcomeScene(GameEngine gameEngine){
        this.gameEngine = gameEngine;
    }

    @Override
    public void createScene() {
        addBackgroundAsteroids(new Integer[]{3,4,5});
        setGameName();
        setNewGameButton();
        setRantingListButton();
        setInstructionButton();
        this.getPane().setStyle("-fx-background-color: black");
    }

    // put an 'Asteroid' on the scene
    private void setGameName() {
        Text t = new Text(230, 180, "Asteroids");
        t.setFill(Color.WHITE);
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 60);
        t.setFont(font);
        this.getPane().getChildren().addAll(t);
    }

    private void addBackgroundAsteroids(Integer[] numberList) {
        for(int i = 0; i < numberList[0]; i++){
            this.getPane().getChildren().addAll(new LargeAsteroid());
        }
        for(int i = 0; i < numberList[1]; i++){
            this.getPane().getChildren().addAll(new MediumAsteroid());
        }
        for(int i = 0; i < numberList[2]; i++){
            this.getPane().getChildren().addAll(new SmallAsteroid());
        }
    }

    private void setNewGameButton() {
        NewGame = getButton(new Point2D(310, 300), "New Game");
        NewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.newGame();
            }
        });
        this.getPane().getChildren().add(NewGame);
    }


    // read score from the Score.text and rank them and display in a text
    private void setRantingListButton() {
        Rank = getButton(new Point2D(290, 380), "Ranting List");
        Rank.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.scoreDisplay();
            }
        });
        this.getPane().getChildren().add(Rank);
    }

    private void setInstructionButton() {
        Instruction = getButton(new Point2D(290, 460), "Instruction");
        this.getPane().getChildren().add(Instruction);
        Instruction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameEngine.instruction();
            }
        });
    }

}

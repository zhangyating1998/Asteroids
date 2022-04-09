package group13.application.game.scene;

import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.asteroid.MediumAsteroid;
import group13.application.characters.asteroid.SmallAsteroid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WelcomeScene extends BaseScene {
    Button NewGame;
    Button Rank;

    @Override
    public void createScene() {
        System.out.println("Welcome! Press any key to start.");
        setGameName();
        addBackgroundAsteroids(new Integer[]{3,4,5});
        setNewGameButton();
        setRantingListButton();
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

            }
        });
        this.getPane().getChildren().add(NewGame);
    }

    private void setRantingListButton() {
        Rank = getButton(new Point2D(290, 380), "Ranting List");
        this.getPane().getChildren().add(Rank);
    }

    private Button getButton(Point2D position, String text) {
        Button button = new Button(text);
        button.setLayoutX(position.getX());
        button.setLayoutY(position.getY());
        button.setWrapText(false);
        button.setShape(new Polygon(100.0, 250.0, 700.0, 250.0, 600.0, 350.0, 0.0, 350.0));
        button.setStyle("-fx-background-color: transparent; -fx-border-color: white; -fx-text-fill: white");
        button.setPadding(new Insets(5, 30, 5, 30));
        button.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
        return button;
    }


}

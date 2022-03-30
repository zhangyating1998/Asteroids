package group13.application.characters.asteroid;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class test extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();

        LargeAsteroid largeAs = new LargeAsteroid();
        largeAs.Translate(0,0,800, 600, 80);

        MediumAsteroid mediAs = new MediumAsteroid();
        mediAs.Translate(100, 0, 800, 400, 100);

        SmallAsteroid smallAs = new SmallAsteroid();
        smallAs.Translate(0, 300, 500, 0, 200 );

        root.getChildren().addAll(mediAs, largeAs, smallAs);
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

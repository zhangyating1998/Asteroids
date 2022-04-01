package group13.application.characters.asteroid;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class test extends Application {
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Polygon p1 = new Polygon(8.0, 0.0, 15.0, 3.0, 13.0, 13.0, 9.0, 15.0, 0.0, 10.0);
        Polygon p2 = new Polygon(7.0, 0.0, 9.0, 3.0, 12.0, 0.0, 15.0, 10.0, 13.0, 14.0, 0.0, 12.0, 5.0, 8.0, 0.0, 3.0 );
        Polygon p3 = new Polygon(0.0, 8.0, 10.0, 0.0, 15.0, 4.0, 12.0, 7.0, 15.0, 13.0, 9.0, 15.0 );
        Polygon p4 = new Polygon(3.0, 0.0, 10.0, 3.0, 10.0, 8.0, 15.0, 9.0, 13.0, 15.0, 2.0, 13.0, 0.0, 8.0);

        p1.setLayoutX(0);
        p1.setLayoutY(10);

        p2.setLayoutX(50);
        p2.setLayoutY(10);

        p3.setLayoutX(100);
        p3.setLayoutY(10);

        p4.setLayoutX(150);
        p4.setLayoutY(10);

        Scale s1 = new Scale();
        s1.setX(4);
        s1.setY(4);
        p3.getTransforms().add(s1);

        Scale s2 = new Scale();
        s2.setX(1);
        s2.setY(1);
        p2.getTransforms().add(s2);

        LargeAsteroid l1 = new LargeAsteroid();
        MediumAsteroid m1 = new MediumAsteroid();
        SmallAsteroid s = new SmallAsteroid();
        root.getChildren().addAll(l1, m1, s);
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

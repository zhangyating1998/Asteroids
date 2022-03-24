package group13.application;

import group13.application.common.GameLevel;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AsteroidsGame extends Application {
    private GameLevel gameLevel;

    @Override
    public void start(Stage stage) throws IOException {
        Polygon ship = new Polygon();
        ship.setFill(Color.WHITE);
        ship.getPoints().addAll(new Double[]{
                0.0, 0.0,
                20.0, 0.0,
                10.0, -20.0
        });

        Polygon ship2 = new Polygon();
        ship2.setFill(Color.WHITE);
        ship2.getPoints().addAll(new Double[]{
                -50.0, -50.0,
                -70.0, -50.0,
                -80.0, -70.0
        });
        final Rectangle rectPath = new Rectangle (0, 0, 40, 40);
        rectPath.setArcHeight(10);
        rectPath.setArcWidth(10);
        rectPath.setFill(Color.ORANGE);
        Path path = new Path();
        path.getElements().add(new MoveTo(500,300));
        path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(rectPath);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        Rectangle rectParallel = new Rectangle(10,200,50, 50);
        rectParallel.setArcHeight(15);
        rectParallel.setArcWidth(15);
        rectParallel.setFill(Color.DARKBLUE);
        rectParallel.setTranslateX(50);
        rectParallel.setTranslateY(75);

        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(3000), rectParallel);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(2);
        fadeTransition.setAutoReverse(true);
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(2000), rectParallel);
        translateTransition.setFromX(50);
        translateTransition.setToX(350);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        RotateTransition rotateTransition =
                new RotateTransition(Duration.millis(3000), rectParallel);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(2000), rectParallel);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                fadeTransition,
                translateTransition,
                rotateTransition,
                scaleTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();

        final Rectangle rectBasicTimeline = new Rectangle(100, 50, 100, 50);
        rectBasicTimeline.setFill(Color.RED);

        StackPane pane = new StackPane(
                new Rectangle(800, 600, Color.BLACK),
                ship,
                ship2,
                rectPath,
                rectParallel,
                rectBasicTimeline
        );

        // detect collision
//        Polygon.intersect(ship, ship2);
        stage.setScene(new Scene(pane, 800, 600));

        ship.fireEvent(new Event(EventType.ROOT));

        pane.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                event.consume();
            }
        });

        stage.show();

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(rectBasicTimeline.xProperty(), 300);
        final KeyFrame kf = new KeyFrame(Duration.millis(3500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }
}
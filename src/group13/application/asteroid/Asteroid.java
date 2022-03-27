package group13.application.asteroid;

import group13.application.Splittable;
import group13.application.common.GameLevel;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Asteroid implements Splittable {
    Polygon asteroid;
    double speed;
    public Asteroid(Double[] coors){
        this.asteroid = new Polygon();
        asteroid.getPoints().addAll(coors);
        asteroid.setFill(Color.BLACK);
        asteroid.setStroke(Color.WHITE);
    }
    public double getSpeed(){
        return this.speed;
    }
    public void Translate (double startX, double startY, double stopX, double stopY, double speed ){
        TranslateTransition translate = new TranslateTransition();
        Point2D startPoint = new Point2D(startX, startY);
        Point2D endPoint = new Point2D(stopX, stopY);
        double distance = startPoint.distance(endPoint);
        double duration = (distance/speed) * 1000;
        translate.setDuration(Duration.millis(duration));
        translate.setFromX(startX);
        translate.setFromY(startY);
        translate.setToX(stopX);
        translate.setToY(stopY);
        translate.setNode(this.asteroid);
        translate.play();
    }

    @Override
    public void split() {

    }
}

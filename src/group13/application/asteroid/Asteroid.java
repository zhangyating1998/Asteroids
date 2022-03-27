package group13.application.asteroid;
import group13.application.Destroyable;
import group13.application.Splittable;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Asteroid extends Polygon implements Destroyable, Splittable  {

    public Asteroid(Double[] coors){
        super();
        this.getPoints().addAll(coors);
        this.setFill(Color.BLACK);
        this.setStroke(Color.WHITE);
    }

    /* This method defends how an asteroid moves by given a start position and an end position and the translation speed
    * the asteroid will move from the start point to the end point with this speed. the startX is the x coordinate of the start position*/
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
        translate.setNode(this);
        translate.play();
    }

    @Override
    public void split() {

    }

    @Override
    public void destroy() {

    }
}

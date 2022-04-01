package group13.application.characters.asteroid;
import group13.application.characters.Character;
import group13.application.characters.Splittable;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

import java.util.Random;

import static group13.application.common.Constants.SCENE_HEIGHT;
import static group13.application.common.Constants.SCENE_WIDTH;

public class Asteroid extends Character implements Splittable  {

    public Asteroid(){
        super();
        this.getPoints().addAll(generateShape());
        this.getTransforms().add(resize());
        this.setFill(Color.BLACK);
        this.setStroke(Color.WHITE);
    }

    // randomly generate the start position of an asteroid
    public Point2D start(){
        Random r = new Random();
        return new Point2D(r.nextInt(SCENE_WIDTH), r.nextInt(SCENE_HEIGHT));
    }

    // randomly generate the coordinates of a polygon, return a Double[] as a coordinate
    public Double[] generateShape(){
        Random r = new Random();
        int shape = r.nextInt(4);
        return switch (shape) {
            case 0 -> new Double[]{8.0, 0.0, 15.0, 3.0, 13.0, 13.0, 9.0, 15.0, 0.0, 10.0};
            case 1 -> new Double[]{7.0, 0.0, 9.0, 3.0, 12.0, 0.0, 15.0, 10.0, 13.0, 14.0, 0.0, 12.0, 5.0, 8.0, 0.0, 3.0};
            case 2 -> new Double[]{0.0, 8.0, 10.0, 0.0, 15.0, 4.0, 12.0, 7.0, 15.0, 13.0, 9.0, 15.0};
            default -> new Double[]{3.0, 0.0, 10.0, 3.0, 10.0, 8.0, 15.0, 9.0, 13.0, 15.0, 2.0, 13.0, 0.0, 8.0};
        };
    }

    // resize an asteroid based on the type of it
    public Scale resize(){
        Scale s = new Scale();
        if(this instanceof LargeAsteroid){
            s.setX(4);
            s.setY(4);
            return s;
        }
        else if(this instanceof MediumAsteroid){
            s.setX(2);
            s.setY(2);
            return s;
        }
        else{
            s.setX(1);
            s.setY(1);
            return s;
        }
    }

    @Override
    public Asteroid[] split() {
        if(this instanceof LargeAsteroid){
            return new MediumAsteroid[]{ new MediumAsteroid(), new MediumAsteroid()};
        }
        else if(this instanceof MediumAsteroid){
            return new SmallAsteroid[]{new SmallAsteroid(), new SmallAsteroid()};
        }
        else{
            return null;
        }
    }
}

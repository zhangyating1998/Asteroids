package group13.application.characters.asteroid;

import javafx.geometry.Point2D;
import javafx.scene.transform.Scale;

public class MediumAsteroid extends Asteroid {
    public MediumAsteroid(Point2D position, double speed){
        super(speed, position);
    }

    public MediumAsteroid() {
        super(0.8);
    }



    @Override
    public Scale resize(){
        Scale s = new Scale();
        s.setX(2);
        s.setY(2);
        return s;
    }
}

package group13.application.characters.asteroid;

import javafx.geometry.Point2D;

public class SmallAsteroid extends Asteroid {
    public SmallAsteroid(){
        super(2.0);
    }

    public SmallAsteroid(Point2D position, double speed){
        super(speed, position);
    }

}

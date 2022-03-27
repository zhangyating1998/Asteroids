package group13.application.asteroid;

import javafx.scene.Group;

public class SmallAsteroid extends Asteroid {
    Double[] coors;
    double speed;
    public SmallAsteroid(){
        super(new Double[]{8.0, 0.0, 15.0, 3.0, 13.0, 13.0, 9.0, 15.0, 0.0, 10.0});
        this.speed = 300;
    }

}

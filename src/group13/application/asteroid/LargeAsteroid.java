package group13.application.asteroid;

import javafx.scene.Group;

public class LargeAsteroid extends Asteroid {
    Double[] coors ;
    double speed;
    public LargeAsteroid(){
        super(new Double[]{20.0, 0.0, 60.0, 40.0, 35.0, 60.0, 0.0,45.0, 4.0, 20.0});
        this.speed = 80;
    }

    public LargeAsteroid(Double[] coors){
        super(coors);
        this.coors = coors;

    }

}

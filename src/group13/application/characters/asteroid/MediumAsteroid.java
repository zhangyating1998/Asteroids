package group13.application.characters.asteroid;

import javafx.scene.transform.Scale;

public class MediumAsteroid extends Asteroid {
    double speed;
    Double[] coors ;
    public MediumAsteroid(){
        super(1.0);
        this.speed = 100;
    }

    @Override
    public Scale resize(){
        Scale s = new Scale();
        s.setX(2);
        s.setY(2);
        return s;
    }
}

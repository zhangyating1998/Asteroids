package group13.application.characters.asteroid;

public class MediumAsteroid extends Asteroid {
    double speed;
    Double[] coors ;
    public MediumAsteroid(){
        super(new Double[]{17.0, 0.0, 30.0, 13.0, 16.0, 18.0, 22.0, 30.0, 0.0, 25.0, 0.0, 10.0});
        this.speed = 100;
    }
}

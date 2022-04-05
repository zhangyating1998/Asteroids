package group13.application.characters.ship;

import javafx.geometry.Point2D;
import java.util.Random;


public class EnemyShip extends Ship {
    private Point2D AlienSpeed;


    public EnemyShip(int x, int y) {
        super(x, y, -10, -10, -20, 20, 10, 10, 20, -20);


        Random rnd = new Random();

        super.setRotate(rnd.nextInt(360));
        this.AlienSpeed = new Point2D(0, 0);
        AlienPropulsion();

    }
    public void move() {
        setTranslateX(getTranslateX() + this.AlienSpeed.getX());
        setTranslateY(getTranslateY() + this.AlienSpeed.getY());
    }
    public void AlienPropulsion() {
        double changeX = Math.cos(Math.toRadians(getRotate()));
        double changeY = Math.sin(Math.toRadians(getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.AlienSpeed = this.AlienSpeed.add(changeX, changeY);


    }}



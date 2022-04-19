package group13.application.characters.ship;

import group13.application.common.Constants;
import javafx.geometry.Point2D;
import java.util.Random;


public class EnemyShip extends Ship {
    private Point2D AlienSpeed;


    public EnemyShip(int x, int y) {
        super(x, y, -10, -10, -20, 20, 10, 10, 20, -20);

        // 1. generate random x, y
        // 1.1 x = 0 or WIDTH
        // 1.2 y = random ( HEIGHT )
        Random rnd = new Random();

        // 2. change the angle based on x
//        super.setRotate(rnd.nextInt(360));
        //speed can be adjusted for game Level
        this.AlienSpeed = new Point2D(1, 2);
       // AlienPropulsion();



    }

    @Override
    public void fire() {
        super.fire();
        // 1. create Bullet instance
        // 2. fire an event, pass in the instance
    }

    public void move() {
        // 1. check if it reaches the edge
        // 2. change the speed of the ship
        setTranslateX(getTranslateX() + this.AlienSpeed.getX());
        setTranslateY(getTranslateY() + this.AlienSpeed.getY());

        //set visable
//        if (this.getTranslateX() + this.getLayoutBounds().getWidth() < 0)
//
        // 2. fire an event
        // print('...')
//            //add eventlistener
//            this.setTranslateX(Constants.SCENE_WIDTH);
//
//        if (this.getTranslateX() > Constants.SCENE_WIDTH)
//            this.setTranslateX(-this.getLayoutBounds().getWidth());
//
//        if (this.getTranslateY() + this.getLayoutBounds().getHeight() < 0)
//            this.setTranslateY(Constants.SCENE_HEIGHT);
//
//        if (this.getTranslateY() > Constants.SCENE_HEIGHT)
//            this.setTranslateY(-this.getLayoutBounds().getHeight());
            }

    public void AlienPropulsion() {
        double changeX = Math.cos(Math.toRadians(getRotate()));
        double changeY = Math.sin(Math.toRadians(getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.AlienSpeed = this.AlienSpeed.add(changeX, changeY);


    }}



package group13.application.characters.ship;

import group13.application.characters.Bullet;
import group13.application.characters.Character;
import group13.application.characters.Movable;
import group13.application.characters.Shootable;

public class Ship extends Character implements Movable, Shootable {

    public Ship(int x, int y, double... points) {
        super(x, y, points);
    }

    @Override
    public void rotateRight() {

    }

    @Override
    public void rotateLeft() {

    }

    @Override
    public void thrust() {

    }

    @Override
    public void hyperspaceJump() {

    }

    @Override
    public void fire() {
        Bullet bullet = new Bullet((int) getTranslateX(), (int) getTranslateY());
        bullet.getCharacter().setRotate(getRotate());
//        bullets.add(bullet);

        //TODO Make based on ship speed
        bullet.accelerate(5);

//        pane.getChildren().add(bullet.getCharacter());
    }

}

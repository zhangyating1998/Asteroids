package group13.application;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Character {

    protected Polygon Character;
    private Point2D velocity;

    public Character(Polygon shape, int x, int y) {
        this.Character = shape;
        this.Character.setTranslateX(x);
        this.Character.setTranslateY(y);

        this.velocity = new Point2D(0, 0);
    }

    public Polygon getCharacter() {
        return Character;
    }

    public void turnLeft() {
        this.Character.setRotate(this.Character.getRotate() - 5);
    }

    public void turnRight() {
        this.Character.setRotate(this.Character.getRotate() + 5);
    }

    public void move() {
        this.Character.setTranslateX(this.Character.getTranslateX() + this.velocity.getX());
        this.Character.setTranslateY(this.Character.getTranslateY() + this.velocity.getY());
    }

    public void accelerate(double m) {
        double changeX = Math.cos(Math.toRadians(this.Character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.Character.getRotate()));

        changeX *= m;
        changeY *= m;

        this.velocity = this.velocity.add(changeX, changeY);
    }
}
package group13.application.characters;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Character extends Polygon {
    private Point2D velocity;

    public Character(double x, double y, double... points) {
        super(points);
        setTranslateX(x);
        setTranslateY(y);
        setFill(Color.WHITE);
        this.velocity = new Point2D(0, 0);
    }

    public Character(){
        super();
        this.velocity = new Point2D(0, 0);
    }

    public void setVelocity(Point2D v){
        this.velocity = v;
    }

    public void turnLeft() {
        setRotate(getRotate() - 5);
    }

    public void turnRight() {
        setRotate(getRotate() + 5);
    }

    //make movement
    public void move() {
        setTranslateX(getTranslateX() + this.velocity.getX());
        setTranslateY(getTranslateY() + this.velocity.getY());
    }

    private void checkEdge() {
        // todo
    }


    public void accelerate(double m) {
        double changeX = Math.cos(Math.toRadians(this.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getRotate()));

        changeX *= m;
        changeY *= m;

        this.velocity = this.velocity.add(changeX, changeY);
    }
}

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
        setRotate(getRotate() - 15);
    }

    public void turnRight() {
        setRotate(getRotate() + 15);
    }

    //make movement
    public void move() {
        setTranslateX(getTranslateX() + this.velocity.getX());
        setTranslateY(getTranslateY() + this.velocity.getY());
    }

    private void checkEdge() {
        // todo
    }

    // change velocity
    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(getRotate()));
        double changeY = Math.sin(Math.toRadians(getRotate()));

        changeX *= 0.15;
        changeY *= 0.15;

        this.velocity = this.velocity.add(changeX, changeY);
    }

}

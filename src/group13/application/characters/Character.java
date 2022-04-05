package group13.application.characters;

import group13.application.common.Constants;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Character extends Polygon {

    protected Polygon Character;
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

    public Polygon getCharacter() {
        return Character;
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
        this.setTranslateX(this.getTranslateX() + this.velocity.getX());
        this.setTranslateY(this.getTranslateY() + this.velocity.getY());

        if (this.getTranslateX() + this.getLayoutBounds().getWidth() < 0)
            this.setTranslateX(Constants.SCENE_WIDTH);

        if (this.getTranslateX() > Constants.SCENE_WIDTH)
            this.setTranslateX(-this.getLayoutBounds().getWidth());

        if (this.getTranslateY() + this.getLayoutBounds().getHeight() < 0)
            this.setTranslateY(Constants.SCENE_HEIGHT);

        if (this.getTranslateY() > Constants.SCENE_HEIGHT)
            this.setTranslateY(-this.getLayoutBounds().getHeight());
    }

    public void accelerate(double m) {
        double changeX = Math.cos(Math.toRadians(this.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getRotate()));

        changeX *= m;
        changeY *= m;

        this.velocity = this.velocity.add(changeX, changeY);
    }
}

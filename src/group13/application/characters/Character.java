package group13.application.characters;

import group13.application.common.Constants;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Character extends Polygon {
    private Point2D velocity;

    public Character(int x, int y, double... points) {
        super(points);
        setTranslateX(x);
        setTranslateY(y);
        setFill(Color.WHITE);

        this.velocity = new Point2D(0, 0);
    }

    public void turnLeft() {
        setRotate(getRotate() - 5);
    }

    public void turnRight() {
        setRotate(getRotate() + 5);
    }

    public void move() {
        setTranslateX(getTranslateX() + this.velocity.getX());
        setTranslateY(getTranslateY() + this.velocity.getY());

        if (this.getTranslateX() + this.getLayoutBounds().getWidth() < 0)
            this.setTranslateX(Constants.SCENE_WIDTH);

        if (this.getTranslateX() > Constants.SCENE_WIDTH)
            this.setTranslateX(-this.getLayoutBounds().getWidth());

        if (this.getTranslateY() + this.getLayoutBounds().getHeight() < 0)
            this.setTranslateY(Constants.SCENE_HEIGHT);

        if (this.getTranslateY() > Constants.SCENE_HEIGHT)
            this.setTranslateY(-this.getLayoutBounds().getHeight());

    }

    private void checkEdge() {
        // todo
    }

    public void accelerate() {
        double changeX = Math.cos(Math.toRadians(getRotate()));
        double changeY = Math.sin(Math.toRadians(getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.velocity = this.velocity.add(changeX, changeY);
    }

}

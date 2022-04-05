package group13.application.characters;

import group13.application.common.Constants;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class Character extends Polygon {
    protected Polygon Character;
    private Point2D velocity;
    private double counter;
    private boolean isTimeOut = false;
    private double timeToLive;

    public Character(int x, int y, double... points) {
        super(points);
        setTranslateX(x);
        setTranslateY(y);
        setFill(Color.WHITE);

        this.velocity = new Point2D(0, 0);
    }

    public Character(int x, int y, boolean isTimeOut, double timeToLive, double... points) {
        this(x, y, points);
        this.isTimeOut = isTimeOut;
        this.timeToLive = timeToLive;
    }

    public Polygon getCharacter() {
        return Character;
    }

    public void turnLeft() {
        setRotate(getRotate() - 5);
    }

    public void turnRight() {
        setRotate(getRotate() + 5);
    }

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

        if (isTimeOut) {
            this.counter += 0.01666;
            if (this.counter > timeToLive) {
                //TODO Add event handler to remove
//            t.remove?(b);
//            pane.getChildren().remove(b.getCharacter());
            }
        }
    }

    public void accelerate(double m) {
        double changeX = Math.cos(Math.toRadians(this.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getRotate()));

        changeX *= m;
        changeY *= m;

        this.velocity = this.velocity.add(changeX, changeY);
    }

}

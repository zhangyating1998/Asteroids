package group13.application.characters.ship;

import group13.application.characters.Bullet;
import group13.application.characters.EnemyBullet;
import group13.application.common.Constants;
import group13.application.game.scene.PlayScene;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

import static group13.application.common.Constants.SCENE_HEIGHT;
import static group13.application.common.Constants.SCENE_WIDTH;
import static group13.application.game.PlaySceneController.playScene;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class EnemyShip extends Ship {
    private Point2D AlienSpeed;
    private final static Random random = new Random();

//    private void createNewAlienShip() {
//        EnemyShip alienShip = new EnemyShip(0, random.nextInt(SCENE_HEIGHT));
//        this.getPane().getChildren().addAll(alienShip);
//    }


    public EnemyShip(int x, int y) {
        super(x, y, true, 1000, -10, -10, -20, 20, 10, 10, 20, -20);
//        Random random = new Random();
//        Pane pane;
//        Random rnd = new Random();
        Random speed = new Random();
        double heightMin = 0 + this.getLayoutBounds().getHeight();
        double heightMax = SCENE_HEIGHT - this.getLayoutBounds().getHeight();

        int speedAndDirection = (speed.ints(-4, 4).findFirst().getAsInt());
        if (speedAndDirection == 0) ;
//        System.out.println("Removed ALien Ship due speed = 0");
        this.AlienSpeed = new Point2D(speedAndDirection, 0);

        if (speedAndDirection < 0){
            Point2D start = new Point2D(SCENE_WIDTH, random.nextInt((int) heightMax - (int) heightMin));

            //set the initial position of the alienShip
            this.setTranslateX(start.getX());
            this.setTranslateY(start.getY());
            setRotate(new Random().nextInt(270));
    }
        else {
            Point2D start = new Point2D(0, random.nextInt(SCENE_HEIGHT));
            this.setTranslateX(start.getX());
            this.setTranslateY(start.getY());
            setRotate(new Random().nextInt(90));
        }
        Random ra = new Random(0);
        double randomALienSpeed = .35+(1.75 -.35) * ra.nextDouble();
        accelerate(randomALienSpeed);

//        this.getLayoutBounds().getWidth()





    }

    public void move() {
        this.setTranslateX(this.getTranslateX() + this.AlienSpeed.getX());
        this.setTranslateY(this.getTranslateY() + this.AlienSpeed.getY());

//        if (this.getTranslateX() + this.getLayoutBounds().getWidth() < 0)
//            this.setTranslateX(Constants.SCENE_WIDTH);
//
//
//        if (this.getTranslateX() > 820 && this.getTranslateX() < 825) {
////            System.out.println("Remove from Pane Eastbound");

//
//        }
//
//        if (this.getTranslateX() < -20 && this.getTranslateX() < -21) {
////            System.out.println("Remove from Pane Westbound");
//
//        }
    }

    //        public void alienBulletFire() {
//
//        if ((this.getCounter() == (0.016 * 180)) || (this.getCounter() < (0.016 * 210))) {
//            this.fire();
//
//
//        }
//    }
    public void fire() {
        float angle = (float) Math.toDegrees(Math.atan2(playScene.playerShip.getTranslateY() - getTranslateY(),
                playScene.playerShip.getTranslateX() - getTranslateX()));
        Random r = new Random();
        EnemyBullet enemyBullet = new EnemyBullet((int) (playScene.alienShip.getTranslateX()), (int) (playScene.alienShip.getTranslateY()));
        enemyBullet.setRotate(angle);
        double speed = 5 + Math.abs(Math.max(this.getVelocity().getX(), this.getVelocity().getX()));
        enemyBullet.accelerate(speed);
        playScene.getPane().getChildren().add(enemyBullet);
    }

}





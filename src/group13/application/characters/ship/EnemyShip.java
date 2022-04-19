package group13.application.characters.ship;

import group13.application.common.Constants;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

import static group13.application.common.Constants.SCENE_HEIGHT;
import static group13.application.common.Constants.SCENE_WIDTH;
import javafx.scene.layout.Pane;



public class EnemyShip extends Ship {
    private Point2D AlienSpeed;
    private final static Random random = new Random();

//    private void createNewAlienShip() {
//        EnemyShip alienShip = new EnemyShip(0, random.nextInt(SCENE_HEIGHT));
//        this.getPane().getChildren().addAll(alienShip);
//    }



    public EnemyShip(int x, int y) {
        super(x, y, -10, -10, -20, 20, 10, 10, 20, -20);
        Random random = new Random();
        Pane pane;
//        EnemyShip alienShip = new EnemyShip(0, random.nextInt(SCENE_HEIGHT));

//        EnemyShip alienShip = new EnemyShip(random.nextInt(SCENE_WIDTH), random.nextInt(SCENE_HEIGHT));

        // 1. generate random x, y
        // 1.1 x = 0 or WIDTH
        // 1.2 y = random ( HEIGHT )
        Random rnd = new Random();




//         new EnemyShip(0, random.nextInt(SCENE_HEIGHT));
//        this.getPane().getChildren().addAll(alienShip);

        // 2. change the angle based on x
//        super.setRotate(rnd.nextInt(360));
        //speed can be adjusted for game Level

        Random speed = new Random();
        int speedAndDirection = (speed.ints(-2, 4).findFirst().getAsInt());
//        int min = 1;
//        int max = 4;

//        Random ran = new Random();

//        int speedAndDirection = ran.nextInt(max + min) + min;

        if (speedAndDirection == 0);
            System.out.println("Removed ALien Ship due speed = 0");
            this.AlienSpeed = new Point2D(speedAndDirection, 0);
       // AlienPropulsion();

//        if (this.getTranslateX()  == 899) {
////            this.setTranslateX(Constants.SCENE_WIDTH);
//            System.out.println("Test1");
//        }

//        if (this.getTranslateX() == Constants.SCENE_WIDTH) {
////            this.setTranslateX(-this.getLayoutBounds().getWidth());
//            System.out.println("Test2");
//        }
//
//        if (this.getTranslateY() + this.getLayoutBounds().getHeight() < 0) {
//            this.setTranslateY(Constants.SCENE_HEIGHT);
//            System.out.println("Test3");
//        }
//
//        if (this.getTranslateY() > Constants.SCENE_HEIGHT) {
//            this.setTranslateY(-this.getLayoutBounds().getHeight());
//            System.out.println("Test4");
//        }
    }
//    public class generate_random_between_two_number_jdk_8 {
//        public void main(String[] args) {
//            Random randomObj = new Random();
//            System.out.println(randomObj.ints(10, 50).findFirst().getAsInt()); ;
//        }
//    }

    public void move() {
        this.setTranslateX(this.getTranslateX() + this.AlienSpeed.getX());
        this.setTranslateY(this.getTranslateY() + this.AlienSpeed.getY());

        if (this.getTranslateX() + this.getLayoutBounds().getWidth() < 0)
            this.setTranslateX(Constants.SCENE_WIDTH);


        if (this.getTranslateX() > 820 && this.getTranslateX() < 825) {
            System.out.println("Remove from Pane Eastbound");


        }

        if (this.getTranslateX() < -20 && this.getTranslateX() < -21) {
            System.out.println("Remove from Pane Westbound");

        }

    }
    @Override
    public void fire() {
        super.fire();
        // 1. create Bullet instance
        // 2. fire an event, pass in the instance
    }

//    public void move() {
//        // 1. check if it reaches the edge
//
//        if (this.getTranslateX()  == 899) {
////            this.setTranslateX(Constants.SCENE_WIDTH);
//            System.out.println("Test1");
//        }
//
//        if (this.getTranslateX() == Constants.SCENE_WIDTH) {
////            this.setTranslateX(-this.getLayoutBounds().getWidth());
//            System.out.println("Test2");
//        }
//        // 2. change the speed of the ship
//        setTranslateX(getTranslateX() + this.AlienSpeed.getX());
//        setTranslateY(getTranslateY() + this.AlienSpeed.getY());
//
//
////        if (this.getTranslateX() + this.getLayoutBounds().getWidth() == 899);
//
////
//        // 2. fire an event
//        // print('...')
////            //add eventlistener
////            this.setTranslateX(Constants.SCENE_WIDTH);
////
//        if (this.getTranslateX() > Constants.SCENE_WIDTH)
//            System.out.println("Test");
//            this.setTranslateX(-this.getLayoutBounds().getWidth());
////
////        if (this.getTranslateY() + this.getLayoutBounds().getHeight() < 0)
////            this.setTranslateY(Constants.SCENE_HEIGHT);
////
////        if (this.getTranslateY() > Constants.SCENE_HEIGHT)
////            this.setTranslateY(-this.getLayoutBounds().getHeight());
//            }

    public void AlienPropulsion() {
        double changeX = Math.cos(Math.toRadians(getRotate()));
        double changeY = Math.sin(Math.toRadians(getRotate()));

        changeX *= 0.05;
        changeY *= 0.05;

        this.AlienSpeed = this.AlienSpeed.add(changeX, changeY);


    }}



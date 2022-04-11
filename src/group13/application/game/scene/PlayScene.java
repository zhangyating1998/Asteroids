package group13.application.game.scene;

import group13.application.characters.Character;
import group13.application.characters.asteroid.Asteroid;
import group13.application.characters.asteroid.LargeAsteroid;
import group13.application.characters.asteroid.MediumAsteroid;
import group13.application.characters.ship.EnemyShip;
import group13.application.characters.ship.PlayerShip;
import group13.application.game.GameEngine;
import group13.application.game.events.handlers.CollisionEventHandler;
import group13.application.game.events.handlers.PlayerKeyEventHandler;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static group13.application.common.Constants.*;

/**
 * This scene manages the life cycle of a game.
 */
public class PlayScene extends BaseScene {
    private int numberOfLives = DEFAULT_NUMBER_OF_LIVES;
    private int gameLevel = GAME_LEVEL_START;
    private EventHandler playerKeyHandler;
    private final static Random random = new Random();
    private long lastSecondsAlienShipAdded = 0;
    private PlaySceneParams playSceneParams;
    private boolean isGameContinue = true;
    private int score=0;
    private Label scoreLabel;
    private Label lifeLabel;
    final private GameEngine gameEngine;
    private ArrayList<Integer> scores;
    private HashMap<Integer, String> score_time;
    private int TopScores = 10;

    public PlayScene(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.scores = new ArrayList<>();
        this.score_time = new HashMap<>();
    }

    @Override
    public void createScene() {
        this.playSceneParams = PlaySceneParams.getConfig(this.gameLevel);
        createSceneByGameLevel();
        this.addEventFilter(COLLISION, new CollisionEventHandler(this));
        displayScore();
        displayLife();
        this.getPane().setStyle("-fx-background-color: black");
    }

    public void displayScore(){
        scoreLabel = new Label();
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setText("SCORES : "+ score);
        scoreLabel.setAlignment(Pos.TOP_LEFT);
        scoreLabel.setPadding(new Insets(10));
        scoreLabel.setFont(Font.font(20));
        this.getPane().getChildren().addAll(scoreLabel);
    }

    public int getScore() {
        return score;
    }

    public void displayLife() {
        lifeLabel = new Label();
        lifeLabel.setTextFill(Color.WHITE);
        lifeLabel.setText("LIVES : "+ Integer.toString(3));
        lifeLabel.setAlignment(Pos.TOP_LEFT);
        lifeLabel.setPadding(new Insets(35));
        lifeLabel.setFont(Font.font(20));
        this.getPane().getChildren().addAll(lifeLabel);
    }

    private void createSceneByGameLevel() {
        for (int i = 0; i < this.playSceneParams.getNumberOfAsteroids(); i++) {
            LargeAsteroid largeAsteroid = new LargeAsteroid();
            getPane().getChildren().add(largeAsteroid);
        }

        createNewPlayerShip();
    }

    private void createNewPlayerShip() {
        // TODO the location of the player ship should be calculated based on the other objects in the scene
        // set the location of the player ship

        // player ship should be able to move by keyboard, and can shoot bullets.
        PlayerShip playerShip = new PlayerShip(SCENE_WIDTH / 2, SCENE_HEIGHT / 2);
        findASafePoint(playerShip);
        this.getPane().getChildren().addAll(playerShip);

        this.playerKeyHandler = new PlayerKeyEventHandler(playerShip);
        this.addEventFilter(KeyEvent.KEY_PRESSED, this.playerKeyHandler);
    }

    private void createNewAlienShip() {
        EnemyShip alienShip = new EnemyShip(random.nextInt(SCENE_WIDTH), random.nextInt(SCENE_HEIGHT));
        this.getPane().getChildren().addAll(alienShip);
    }

    public void upgrade() {
        // some events may arrive later after game is over
        if (this.isGameContinue) {
            if (this.gameLevel == GAME_LEVEL_MAX) {
                pass();
                return;
            }
            resetScene();
            System.out.println("Current game level: " + this.gameLevel);
            this.gameLevel++;
            this.playSceneParams = PlaySceneParams.getConfig(this.gameLevel);
            System.out.println("Upgraded game level: " + this.gameLevel);
            // create a new scene of the next level
            createSceneByGameLevel();
        }
    }

    public void end() {
        System.out.println("GAME OVER!");
        addRecord();
        gameEngine.gameOver(getScore());
        this.isGameContinue = false;
    }

    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void AddAndMaintain(){
        // read all the scores and add new score, and rank them
        URL url = PlayScene.class.getClassLoader().getResource("Score.txt");
        File file = new File(url.getPath());
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String nextLIne = scanner.nextLine();
                if(! nextLIne.equals("")){
                    String[] line = nextLIne.split(" ");
                    score_time.put(Integer.parseInt(line[0]), " "+line[1] + " " + line[2]+" "+line[3]);
                    System.out.println(line[0]+line[1]);
                }
            }
        }
        catch(IOException e){
            System.out.println("failed to read in");
        }
        score_time.put(getScore(), " Date: "+getCurrentTime());
        System.out.println("scores in the map:"+score_time.size());
        scores.addAll(score_time.keySet());
        scores.sort(Collections.reverseOrder());
    }

    private void writeMapToFile() {
        URL url = PlayScene.class.getClassLoader().getResource("Score.txt");
        File file = new File(url.getPath());
        try {
            // clear the previous records
            FileWriter fwClean = new FileWriter(file, false);
            fwClean.write("");
            fwClean.close();

            // write the map to the file
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < scores.size(); i++) {
                bw.write(scores.get(i) + score_time.get(scores.get(i))+"\n");
            }
            bw.close();
            fw.close();

        } catch(IOException e){
            System.out.println("failed to write");
        }
    }

    private void addRecord() {
        AddAndMaintain();
        if(score_time.size() != scores.size()){
            System.out.println("big error!");
        }
        else {
            if(score_time.size() == TopScores+1){
                int remove = scores.remove(TopScores);
                score_time.remove(remove);
            }
            writeMapToFile();
        }
    }

    public void pass() {
        System.err.println("Congratulation! You have passed all the levels!");
        this.isGameContinue = false;
    }

    public void setLifeLabel() {
        lifeLabel.setText("LIVES : "+Integer.toString(numberOfLives));
    }

    public void setScoreLabel() {
        scoreLabel.setText("SCORES : "+Integer.toString(score));
    }


    // add score according to the level of the asteroid that the user has destroyed
    public void AddScore(Asteroid as) {
        if (this.isGameContinue) {
            System.out.println("Current total score: " + score);
            if (as instanceof LargeAsteroid){
                score += 10;
            } else if (as instanceof MediumAsteroid){
                score += 15;
            } else{
                score += 20;
            }
        }
    }


    public void decrementLives() {
        if (this.isGameContinue) {
            if (numberOfLives == 1) {
                end();
            }
            else if (numberOfLives > 1) {
                removePlayerKeyListener();
                numberOfLives-=1;
                createNewPlayerShip();
            }
        }
    }

    /**
     * Reset the current scene including:
     * 1. remove the KeyEvent listener for the player from the scene to avoid duplicate listeners
     * 2. create a new pane for the scene (and disable the current pane)
     */
    private void resetScene() {
        removePlayerKeyListener();
        this.newPane();
        this.lastSecondsAlienShipAdded = 0;
    }

    /**
     * Remove the player key handler from event filter
     */
    private void removePlayerKeyListener() {
        this.removeEventFilter(KeyEvent.KEY_PRESSED, playerKeyHandler);
    }

    /**
     * Check in every frame if an alien ship should be added into the scene
     *
     * @param timeInNanoSeconds
     */
    public void addAlienShips(long timeInNanoSeconds) {
        long currentTimeInSeconds = TimeUnit.SECONDS.convert(timeInNanoSeconds, TimeUnit.NANOSECONDS);
        if (this.lastSecondsAlienShipAdded == 0) {
            this.lastSecondsAlienShipAdded = currentTimeInSeconds;
        } else {
            if (this.isGameContinue
                    && this.lastSecondsAlienShipAdded < currentTimeInSeconds
                    && this.lastSecondsAlienShipAdded + this.playSceneParams.getFrequencyOfAlienShip()
                        == currentTimeInSeconds) {
                System.out.println("Adding new alien ship.");
                createNewAlienShip();
                this.lastSecondsAlienShipAdded = currentTimeInSeconds;
            }
        }
    }

    public void findASafePoint(Character target) {
        for (int y = (int) SCENE_HEIGHT - 50; y > 0; y--) {
            boolean found = true;
            for (Node node : getPane().getChildren()) {
                if (node instanceof Character) {
                    Character character = (Character) node;
                    Point2D futurePosition = character.getFuturePosition();
//                    System.out.format("Current position:, %s\n", character.getCurrentPosition());
//                    System.out.format("Future position:, %s\n", futurePosition);

                    boolean currentOverlap = target.getBoundsInParent().intersects(character.getBoundsInParent());
                    boolean futureOverlap = target.getBoundsInParent().intersects(
                            futurePosition.getX(),
                            futurePosition.getY(),
                            character.getBoundsInParent().getWidth() + 50,
                            character.getBoundsInParent().getHeight() + 50);

                    if (currentOverlap || futureOverlap) {
                        System.out.println("overlapped");
                        target.setTranslateY(y);
                        found = false;
                        break;
                    }
                }
            }
            if (found) {
                System.out.println("found a safe place");
                return;
            }
        }
        System.out.println("Error: Didn't find a safe place!!!");
    }
}

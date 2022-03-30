package group13.application.game;

import static group13.application.common.Constants.DEFAULT_NUMBER_OF_LIVES;

/**
 * @author longyu
 * @date 2022/3/28 23:53
 */
public class GameEngine {
    private int numberOfLives = DEFAULT_NUMBER_OF_LIVES;
    private int gameLevel = DEFAULT_NUMBER_OF_LIVES;
    private static int HIGH_SCORE;

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void decrementLives() {
        if (numberOfLives > 0) {
            numberOfLives--;
        } else {
            System.out.println("ERROR: could not decrement number of lives: " + numberOfLives);
        }
    }

    public void start() {
//        createStartScene();
    }

    public void newGame() {
//        createBattleScene();
//        showScene();
    }

    public void end() {
//        createGameOverScene();
    }

    public void upgrade() {
        this.gameLevel++;
        // reset objects and player
//        createBattleScene();
//        showScene();
    }
}

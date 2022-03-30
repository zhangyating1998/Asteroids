package group13.application.game.eventhandlers;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * @author longyu
 * @date 2022/3/28 23:12
 */
public class PlayerShipDestroyedEventHandler implements EventHandler {
    @Override
    public void handle(Event event) {
        System.out.println("PlayerShipDestroyedEventHandler");
        // check the number of lives of player ship
        // lives > 0, create new ship and put into the pane

        // lives == 0, end the game
        endGame();
    }

    private void endGame() {
        // 1. display "Game Over"
        // 2. stop producing new alien ships
        // 3. ask the player to enter his name
        // 4. create a new game
    }
}

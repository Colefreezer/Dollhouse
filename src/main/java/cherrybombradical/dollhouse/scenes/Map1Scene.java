package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
// Class for the Map1Scene
public class Map1Scene extends Scene {
    public Map1Scene(){
        // Create a new Map1Pane and set the size of the scene
        super(new Map1Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);
        this.getRoot().requestFocus();

        // Set the map coordinates
        GameManager.mapX = 750;
        GameManager.mapY = 158;

        // Set the key pressed event
        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A -> Map1Pane.player.moveLeft();
                case D -> Map1Pane.player.moveRight();
                case TAB -> {
                    // Toggle the map and set the Maria image
                    GameManager.toggleMap((Pane) this.getRoot());
                    Map1Pane.hud.setMariaImage("Map");
                }
            }
            event.consume();
        });

        // Set the key released event
        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A, D -> Map1Pane.player.stopMoving();
            }
            event.consume();
        });
    }
}
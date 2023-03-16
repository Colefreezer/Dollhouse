package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map2Pane;
import cherrybombradical.dollhouse.panes.Map3Pane;
import cherrybombradical.dollhouse.panes.Map4Pane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Map4Scene extends Scene {
    public Map4Scene(){
        super(new Map4Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();
        GameManager.mapX = 457;
        GameManager.mapY = 103;


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case LEFT -> Map4Pane.player.moveLeft();
                case RIGHT -> Map4Pane.player.moveRight();
                case TAB -> GameManager.toggleMap((Pane) this.getRoot());
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case LEFT, RIGHT -> Map4Pane.player.stopMoving();
            }
            event.consume();
        });
    }
}

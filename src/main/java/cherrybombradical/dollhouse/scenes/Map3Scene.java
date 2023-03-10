package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map2Pane;
import cherrybombradical.dollhouse.panes.Map3Pane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Map3Scene extends Scene {
    public Map3Scene(){
        super(new Map3Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case LEFT -> Map3Pane.player.moveLeft();
                case RIGHT -> Map3Pane.player.moveRight();
                case TAB -> GameManager.toggleMap((Pane) this.getRoot());
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case LEFT, RIGHT -> Map3Pane.player.stopMoving();
            }
            event.consume();
        });
    }
}

package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import cherrybombradical.dollhouse.panes.Map2Pane;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Map2Scene extends Scene {
    public Map2Scene(){
        super(new Map2Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case LEFT -> Map2Pane.player.moveLeft();
                case RIGHT -> Map2Pane.player.moveRight();
                case TAB -> GameManager.toggleMap((Pane) this.getRoot());
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case LEFT, RIGHT -> Map2Pane.player.stopMoving();
            }
            event.consume();
        });
    }
}

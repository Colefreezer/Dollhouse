package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import cherrybombradical.dollhouse.panes.Map5Pane;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Map;

public class Map5Scene extends Scene {
    public Map5Scene(){
        super(new Map5Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();
        GameManager.mapX = 750;
        GameManager.mapY = 105;


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A -> Map5Pane.player.moveLeft();
                case D -> Map5Pane.player.moveRight();
                case TAB -> GameManager.toggleMap((Pane) this.getRoot());
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A, D -> Map5Pane.player.stopMoving();
            }
            event.consume();
        });


    }
}

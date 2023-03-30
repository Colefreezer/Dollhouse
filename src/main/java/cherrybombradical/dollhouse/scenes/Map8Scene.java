package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.*;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Map;

public class Map8Scene extends Scene {
    public Map8Scene(){
        super(new Map8Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();
        GameManager.mapX = 900;
        GameManager.mapY = 215;


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A -> Map8Pane.player.moveLeft();
                case D -> Map8Pane.player.moveRight();
                case TAB -> GameManager.toggleMap((Pane) this.getRoot());
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A, D -> Map8Pane.player.stopMoving();
            }
            event.consume();
        });


    }
}

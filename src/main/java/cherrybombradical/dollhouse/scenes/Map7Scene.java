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

public class Map7Scene extends Scene {
    public Map7Scene(){
        super(new Map7Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();
        GameManager.mapX = 900;
        GameManager.mapY = 158;


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A -> Map7Pane.player.moveLeft();
                case D -> Map7Pane.player.moveRight();
                case TAB -> {
                    GameManager.toggleMap((Pane) this.getRoot());
                    Map7Pane.hud.setMariaImage("Map");
                }
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A, D -> Map7Pane.player.stopMoving();
            }
            event.consume();
        });


    }
}

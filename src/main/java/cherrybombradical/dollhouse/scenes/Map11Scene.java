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

public class Map11Scene extends Scene {
    public Map11Scene(){
        super(new Map11Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();
        GameManager.mapX = 750;
        GameManager.mapY = 215;


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A -> Map11Pane.player.moveLeft();
                case D -> Map11Pane.player.moveRight();
                case TAB -> {
                    GameManager.toggleMap((Pane) this.getRoot());
                    Map11Pane.hud.setMariaImage("Map");
                }
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A, D -> Map11Pane.player.stopMoving();
            }
            event.consume();
        });


    }
}

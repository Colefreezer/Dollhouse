package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map2Pane;
import cherrybombradical.dollhouse.panes.Map3Pane;
import cherrybombradical.dollhouse.panes.Map8Pane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Map3Scene extends Scene {
    public Map3Scene(){
        super(new Map3Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();
            GameManager.mapX = 457;
            GameManager.mapY = 158;


        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case A -> Map3Pane.player.moveLeft();
                case D -> Map3Pane.player.moveRight();
                case TAB -> {
                    GameManager.toggleMap((Pane) this.getRoot());
                    Map3Pane.hud.setMariaImage("Map");
                }
            }
            event.consume();
        });

        this.getRoot().setOnKeyReleased(event -> {
            switch (event.getCode()){
                case A, D -> Map3Pane.player.stopMoving();
            }
            event.consume();
        });
    }
}

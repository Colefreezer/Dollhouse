package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Map1Scene extends Scene {
    public Map1Scene(){
        super(new Map1Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);
        Image cursor = new Image("misc/Cursor.png");
        this.setCursor(new ImageCursor(cursor));
        this.getRoot().requestFocus();
        this.getRoot().setOnKeyPressed(event -> {
            switch (event.getCode()){
                case LEFT -> Map1Pane.player.moveLeft();
                case RIGHT -> Map1Pane.player.moveRight();
            }
            event.consume();
        }
        );

        
    }
}

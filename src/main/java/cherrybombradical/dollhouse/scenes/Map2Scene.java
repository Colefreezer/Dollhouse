package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map2Pane;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Map2Scene extends Scene {
    public Map2Scene(){
        super(new Map2Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        Image cursor = new Image("misc/Cursor.png");
        this.setCursor(new ImageCursor(cursor));


    }
}

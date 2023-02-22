package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Map1Scene extends Scene {
    public Map1Scene(){
        super(new Map1Pane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        Image cursor = new Image("misc/Cursor.png");
        this.setCursor(new ImageCursor(cursor));

        
    }
}

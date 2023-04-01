package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.EndPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class EndScene extends Scene {
    public EndScene(){
        super(new EndPane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);

        this.getRoot().requestFocus();


    }
}

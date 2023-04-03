package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.CreditsPane;
import cherrybombradical.dollhouse.panes.EndPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class CreditsScene extends Scene {
    public CreditsScene(){
        super(new CreditsPane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);
        this.getRoot().requestFocus();
    }
}

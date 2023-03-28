package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.mainmenupanes.MainMenuPane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class MainMenuScene extends Scene {

    /**
     * Constructor for the main menu scene.
     * Creates a new scene with a MainMenuPane as its root, and sets the scene dimensions to the game screen size.
     */
    public MainMenuScene(){
        super(new MainMenuPane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);
        this.getStylesheets().add(getClass().getResource("/misc/listStyles.css").toExternalForm());
    }
}

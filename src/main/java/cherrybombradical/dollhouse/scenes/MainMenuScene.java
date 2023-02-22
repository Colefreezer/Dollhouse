package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.MainMenuPane;
import cherrybombradical.dollhouse.panes.Map1Pane;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class MainMenuScene extends Scene {

    /**
     * Constructor for the main menu scene.
     * Creates a new scene with a MainMenuPane as its root, and sets the scene dimensions to the game screen size.
     * Also sets a custom image cursor for the scene.
     */
    public MainMenuScene(){
        super(new MainMenuPane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);

        // Set a custom cursor for the scene
        Image cursor = new Image("misc/Cursor.png");
        this.setCursor(new ImageCursor(cursor));
    }
}

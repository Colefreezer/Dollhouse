package cherrybombradical.dollhouse;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * GameManager holds constant variables used throughout the game.
 */
public class GameManager {
    // Screen width and height of the game window
    public static final int SCREEN_WIDTH = 1449;
    public static final int SCREEN_HEIGHT = 814;

    //Map Names
    public static final String[] MAP_NAME = {"Main Room", "West Hallway", "Backyard", "Upstairs, Basement Main"};

    //Map Toggle
    public static boolean mapToggle = false;
    public static ImageView mapLayer1 = new ImageView("sprites/ui/ui_mapLayer1.png");
    public static ImageView mapLayer2 = new ImageView("sprites/ui/ui_mapLayer2.png");
    public static ImageView mapPointer = new ImageView("sprites/ui/ui_mapPointer.png");

    public static int mapX = 0;
    public static int mapY = 0;

    public static Media mapSound = new Media(new File("Audio/Sounds/SFX_MapOut.mp3").toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(mapSound);



    public static void toggleMap(Pane pane){
        if (!mapToggle){
            pane.getChildren().addAll(mapLayer2, mapLayer1, mapPointer);
            GameManager.mapToggle = true;
            mediaPlayer.play();
            Animations.mapIntro(mapLayer1, mapLayer2, mapPointer, mapX, mapY).play();
            System.out.println(GameManager.mapToggle);
        }
        else{

            pane.getChildren().removeAll(mapLayer2, mapLayer1, mapPointer);
            GameManager.mapToggle = false;
            mediaPlayer.stop();
            System.out.println(GameManager.mapToggle);
        }
    }
}

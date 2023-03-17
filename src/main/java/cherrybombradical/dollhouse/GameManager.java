package cherrybombradical.dollhouse;

import cherrybombradical.dollhouse.panes.SafePane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;

/**
 * GameManager holds constant variables used throughout the game.
 */
public class GameManager {

    // Constants for screen size and default player location
    public static final int SCREEN_WIDTH = 1449;
    public static final int SCREEN_HEIGHT = 814;
    public static int NEW_LOCATION = 300;

    // Constants for map names
    public static final String[] MAP_NAMES = {"Main Room", "West Hallway", "Backyard", "Upstairs, Basement Main"};

    // Variables for map toggle and related assets
    public static boolean mapToggle = false;
    public static ImageView mapLayer1 = new ImageView("sprites/ui/ui_mapLayer1.png");
    public static ImageView mapLayer2 = new ImageView("sprites/ui/ui_mapLayer2.png");
    public static ImageView mapPointer = new ImageView("sprites/ui/ui_mapPointer.png");
    public static int mapX = 0;
    public static int mapY = 0;
    public static AudioPlayer mapToggleSFX = new AudioPlayer("Audio/Sounds/SFX_MapOut.mp3", false);

    // Variables for safe toggle
    public static boolean safeToggle;
    public static SafePane safePane;

    // Variables for sound effects and background music
    public static AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    public static AudioPlayer stairsSFX = new AudioPlayer("Audio/Sounds/SFX_Stairs.mp3", false);
    public static AudioPlayer backgroundMusicIndoors = new AudioPlayer("Audio/Music/Upstairs.mp3", true);
    public static AudioPlayer backgroundMusicOutside = new AudioPlayer("Audio/Music/Outside.mp3", true);

    /**
     * Toggles the map display on or off.
     */
    public static void toggleMap(Pane pane) {
        System.out.println("Toggle map called");
        System.out.println("Map toggle value before toggle: " + mapToggle);

        Rectangle tempFix = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT);
        tempFix.setFill(Color.BLACK);
        tempFix.setVisible(false);
        pane.getChildren().add(tempFix);

        if (!mapToggle) {
            mapToggleSFX.play();
            pane.getChildren().addAll(mapLayer1, mapLayer2, mapPointer);
            System.out.println(pane.getChildren());
            Animations.mapIntro(mapLayer1, mapLayer2, mapPointer, mapX, mapY).play();
        } else {
            mapToggleSFX.stop();
            System.out.println("Removing map layers from pane");
            pane.getChildren().removeAll(mapLayer2, mapLayer1, mapPointer);
            tempFix.setVisible(true);
            tempFix.setVisible(false);
        }

        mapToggle = !mapToggle;
        System.out.println("Map toggle value after toggle: " + mapToggle);
    }

    /**
     * Sets the default player location to a new value.
     */
    public static void setNewLocation(int newLocation) {
        NEW_LOCATION = newLocation;
    }

    /**
     * Gets the default player location value.
     */
    public static int getNewLocation() {
        return NEW_LOCATION;
    }
}

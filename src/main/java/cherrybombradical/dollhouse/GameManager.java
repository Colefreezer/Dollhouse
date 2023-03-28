package cherrybombradical.dollhouse;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameManager {
    // Constants
    public static final int SCREEN_WIDTH = 1449;
    public static final int SCREEN_HEIGHT = 814;
    public static final String[] MAP_NAMES = {"Main Room", "West Hallway", "Backyard", "Upstairs, Basement Main"};
    // Variables
    public static String playerName;
    public static int NEW_LOCATION = 450;
    public static boolean hasKey1 = false;
    public static boolean hasKey2 = true;
    public static boolean hasKey3 = false;
    public static boolean mapToggle = false;
    public static boolean inventorySelect = false;
    public static boolean UpstairsDoorBlocked = true;
    public static int itemNeeded;

    public static ImageView mapLayer1 = new ImageView("sprites/ui/ui_mapLayer1.png");
    public static ImageView mapLayer2 = new ImageView("sprites/ui/ui_mapLayer2.png");
    public static ImageView mapPointer = new ImageView("sprites/ui/ui_mapPointer.png");
    public static int mapX = 0;
    public static int mapY = 0;
    public static AudioPlayer mapToggleSFX = new AudioPlayer("Audio/Sounds/SFX_MapOut.mp3", false);
    public static AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    public static AudioPlayer stairsSFX = new AudioPlayer("Audio/Sounds/SFX_Stairs.mp3", false);
    public static AudioPlayer noteSFX = new AudioPlayer("Audio/Sounds/SFX_NoteIn.mp3", false);
    public static AudioPlayer noteCloseSFX = new AudioPlayer("Audio/Sounds/SFX_NoteOut.mp3", false);
    public static AudioPlayer backgroundMusicMainMenu = new AudioPlayer("Audio/Music/dollhouseTheme.mp3", true);
    public static AudioPlayer backgroundMusicIndoors = new AudioPlayer("Audio/Music/Upstairs.mp3", true);
    public static AudioPlayer backgroundMusicOutside = new AudioPlayer("Audio/Music/Outside.mp3", true);
    public static AudioPlayer backgroundMusicFirePlace = new AudioPlayer("Audio/Music/Fireplace.mp3", true);
    public static AudioPlayer backgroundMusicBasement = new AudioPlayer("Audio/Music/Basement.mp3", true);
    public static AudioPlayer backgroundMusicUpstairs = new AudioPlayer("Audio/Music/attic.mp3", true);
    public static AudioPlayer musicBoxBackgroundMusic = new AudioPlayer("Audio/Music/MusicBox.mp3", true);
    private static long startTime;
    private static AnimationTimer timer;
    /**
     * Toggles the map on and off
     * @param pane The pane to add/remove the map elements from
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
     * Sets the new location
     * @param newLocation The new location
     */
    public static void setNewLocation(int newLocation) {
        NEW_LOCATION = newLocation;
    }
    /**
     * Gets the new location
     * @return The new location
     */
    public static int getNewLocation() {
        return NEW_LOCATION;
    }
    /**
     * Mutes all music
     */
    public static void muteMusic(){
        AudioPlayer[] audioPlayers = {backgroundMusicIndoors, backgroundMusicOutside, backgroundMusicBasement, backgroundMusicFirePlace, backgroundMusicUpstairs, musicBoxBackgroundMusic};
        for (AudioPlayer audioPlayer : audioPlayers) {
            audioPlayer.setVolume(0);
        }
    }
    /**
     * Unmutes all music
     */
    public static void unmuteMusic(){
        AudioPlayer[] audioPlayers = {backgroundMusicIndoors, backgroundMusicOutside, backgroundMusicBasement, backgroundMusicFirePlace, backgroundMusicUpstairs, musicBoxBackgroundMusic};
        for (AudioPlayer audioPlayer : audioPlayers) {
            audioPlayer.setVolume(1);
        }
    }

    /**
     * Mutes all sound effects throughout the game.
     */
    public static void muteSFX(){
        AudioPlayer[] audioPlayers = {mapToggleSFX, doorSFX, stairsSFX};
        for (AudioPlayer audioPlayer : audioPlayers) {
            audioPlayer.setVolume(0);
        }
    }

    /**
     * Unmutes all sound effects throughout the game.
     */
    public static void unmuteSFX(){
        AudioPlayer[] audioPlayers = {mapToggleSFX, doorSFX, stairsSFX};
        for (AudioPlayer audioPlayer : audioPlayers) {
            audioPlayer.setVolume(1);
        }
    }



    public static void startTimer() {
        startTime = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long elapsedTime = now - startTime;
                long seconds = elapsedTime / 1_000_000_000;
                long minutes = seconds / 60;
                seconds = seconds % 60;

                if (minutes > 0) {
                    System.out.println(playerName + "'s time: " + minutes + " minutes, " + seconds + " seconds.");
                } else {
                    System.out.println(playerName + "'s time: " + seconds + " seconds.");
                }
            }
        };
        timer.start();
        // Create Scores.txt if it doesn't exist
        File file = new File("Scores.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void stopTimer() {
        timer.stop();

        // Write final time to Scores.txt
        File file = new File("Scores.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            long elapsedTime = System.nanoTime() - startTime;
            long minutes = elapsedTime / 1_000_000_000 / 60;
            long seconds = elapsedTime / 1_000_000_000 % 60;

            if (minutes > 0) {
                writer.write(playerName + "'s final time: " + minutes + " minutes, " + seconds + " seconds.\n");
            } else {
                writer.write(playerName + "'s final time: " + seconds + " seconds.\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
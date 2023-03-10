package cherrybombradical.dollhouse;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer{
    private MediaPlayer mediaPlayer;

    public static String doorOpen = "Audio/Sounds/door.mp3";

    public SoundPlayer(String soundPath){
        Media sound = new Media(new File(soundPath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
        });
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

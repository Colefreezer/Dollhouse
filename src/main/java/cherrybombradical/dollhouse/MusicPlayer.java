package cherrybombradical.dollhouse;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {
    private MediaPlayer mediaPlayer;

    public MusicPlayer(String musicFilePath, boolean shouldLoop) {
        Media sound = new Media(new File(musicFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setOnEndOfMedia(() -> {
            if (shouldLoop) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }
        });

        if (shouldLoop) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    public void play() {
        mediaPlayer.play();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public boolean isPlaying() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
}

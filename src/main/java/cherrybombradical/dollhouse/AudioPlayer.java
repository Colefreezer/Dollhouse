package cherrybombradical.dollhouse;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    private String filePath;

    public AudioPlayer(String musicFilePath, boolean shouldLoop) {
        this.filePath = musicFilePath;
        Media sound = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setOnEndOfMedia(() -> {
            if (shouldLoop) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            }else {
                mediaPlayer.stop();
            }
        });

        if (shouldLoop) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public void play() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
            mediaPlayer.stop();
            mediaPlayer.play();
        }else {
            mediaPlayer.play();
        }
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

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

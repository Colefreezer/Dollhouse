package cherrybombradical.dollhouse;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
public class AudioPlayer {
    // MediaPlayer object to control audio playback
    private MediaPlayer mediaPlayer;
    // Path of the audio file
    private String filePath;
    /**
     * Constructor for AudioPlayer
     *
     * @param musicFilePath Path of the audio file
     * @param shouldLoop    Boolean value to indicate if the audio should loop
     */
    public AudioPlayer(String musicFilePath, boolean shouldLoop) {
        this.filePath = musicFilePath;
        // Create a new Media object from the file path
        Media sound = new Media(new File(filePath).toURI().toString());
        // Create a new MediaPlayer object with the Media object
        mediaPlayer = new MediaPlayer(sound);
        // Set the action to take when the audio ends
        mediaPlayer.setOnEndOfMedia(() -> {
            if (shouldLoop) {
                // Seek back to the beginning of the audio
                mediaPlayer.seek(Duration.ZERO);
                // Play the audio
                mediaPlayer.play();
            } else {
                // Stop the audio
                mediaPlayer.stop();
            }
        });
        // If the audio should loop, set the cycle count to infinite
        if (shouldLoop) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }
    /**
     * Set the path of the audio file
     *
     * @param filePath Path of the audio file
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Play the audio
     */
    public void play() {
        // Check if the audio is already playing
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            // Stop the audio
            mediaPlayer.stop();
            // Play the audio
            mediaPlayer.play();
        } else {
            // Play the audio
            mediaPlayer.play();
        }
    }
    /**
     * Pause the audio
     */
    public void pause() {
        // Pause the audio
        mediaPlayer.pause();
    }
    /**
     * Stop the audio
     */
    public void stop() {
        // Stop the audio
        mediaPlayer.stop();
    }
    /**
     * Set the volume of the audio
     *
     * @param volume Volume of the audio
     */
    public void setVolume(double volume) {
        // Set the volume of the audio
        mediaPlayer.setVolume(volume);
    }
    /**
     * Check if the audio is playing
     *
     * @return Boolean value indicating if the audio is playing
     */
    public boolean isPlaying() {
        // Return true if the audio is playing
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
    /**
     * Get the MediaPlayer object
     *
     * @return MediaPlayer object
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
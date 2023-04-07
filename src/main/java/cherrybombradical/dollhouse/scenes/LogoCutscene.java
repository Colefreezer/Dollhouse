package cherrybombradical.dollhouse.scenes;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.File;
/**
 * LogoCutscene is a scene that plays the group intro.
 */
public class LogoCutscene extends Scene {
    private MediaPlayer mediaPlayer;
    public LogoCutscene() {
        super(new BorderPane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, Color.BLACK);
        BorderPane root = (BorderPane) this.getRoot();
        // Create the media player and view
        Media media = new Media(new File("Videos/CBRIntro.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(GameManager.SCREEN_WIDTH);
        mediaView.setFitHeight(GameManager.SCREEN_HEIGHT);
        root.setCenter(mediaView);
        // When the video ends, switch to the main menu
        mediaPlayer.setOnEndOfMedia(() -> {
            changeScene();
        });
        // If the user clicks the mouse, switch to the main menu
        setOnMouseClicked((MouseEvent event) -> {
            changeScene();
        });
        // Set the focus to the root pane
        root.requestFocus();
        // If the user presses 'R', restart the video
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R){
                Game.mainStage.setScene(new LogoCutscene());
            }
        });
        // Start playing the video
        mediaPlayer.play();
    }
    /**
     * Change the scene to the main menu.
     */
    private void changeScene() {
        mediaPlayer.stop();
        mediaPlayer.dispose();
        Game.mainStage.setScene(new MainMenuScene());
    }
}
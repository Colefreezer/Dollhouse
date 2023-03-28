package cherrybombradical.dollhouse.scenes;

import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.panes.Map1Pane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class IntroCutscene extends Scene {
    private MediaPlayer mediaPlayer;

    public IntroCutscene() {
        super(new BorderPane(), GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        BorderPane root = (BorderPane) this.getRoot();

        Media media = new Media(new File("Videos/IntroCutscene.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.fitWidthProperty().bind(root.widthProperty());
        mediaView.fitHeightProperty().bind(root.heightProperty());

        root.setCenter(mediaView);

        mediaPlayer.setOnEndOfMedia(() -> {
            changeScene();
        });

        setOnMouseClicked((MouseEvent event) -> {
            changeScene();
        });

        Text skipText = new Text("Click anywhere to skip");
        skipText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        skipText.setFill(Color.WHITE);
        StackPane skipTextContainer = new StackPane(skipText);
        skipTextContainer.setAlignment(Pos.BOTTOM_RIGHT);
        skipTextContainer.setPadding(new javafx.geometry.Insets(10));
        root.setBottom(skipTextContainer);

        mediaPlayer.play();
    }
    private void changeScene() {
        mediaPlayer.stop();
        Game.mainStage.setScene(new Map6Scene());
    }
}

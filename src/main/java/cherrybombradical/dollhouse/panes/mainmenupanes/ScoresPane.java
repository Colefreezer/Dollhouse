package cherrybombradical.dollhouse.panes.mainmenupanes;
import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.AudioPlayer;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.scenes.IntroCutscene;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This class is responsible for creating the ScoresPane, which displays the high scores of the game.
 */
public class ScoresPane extends Group {
    public ScoresPane(){
        // Create the UI elements
        ImageView border = new ImageView(new Image("sprites/UI/windowBorder.png"));

        Text scores = new Text("Scores");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 48);
        Font pixelFontButItsSmaller = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 14);
        scores.setFont(pixelFont);
        scores.setFill(Color.WHITE);

        Button back = new Button("Go Back");
        back.setFont(pixelFontButItsSmaller);
        back.setStyle("-fx-background-color: transparent; -fx-text-fill: #FF0000; ");
        HBox buttons = new HBox(20);
        buttons.setScaleX(2.25);
        buttons.setScaleY(2.25);
        buttons.getChildren().addAll(scores,back);
        scores.setLayoutX(65);
        scores.setLayoutY(75);
        buttons.setLayoutX(600);
        buttons.setLayoutY(247);
        // Read the scores from the file and add them to a list
        List<String> scoresList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("Scores.txt"))) {
            String line;
            while ((line = in.readLine()) != null) {
                scoresList.add(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Sort the scores in descending order
        Collections.sort(scoresList, Collections.reverseOrder());
        // Create the list view and add it to the pane
        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(scoresList));
        listView.setPrefSize(660, 150);
        listView.relocate(50,90);
        this.getChildren().addAll(border, scores,buttons, listView);
        // Add an action listener to the back button
        back.setOnAction(event -> {
            ScaleTransition scaleTransition = Animations.expandOut(Duration.millis(300), this);
            scaleTransition.play();
            scaleTransition.setOnFinished(event1 -> {
                this.setVisible(false);
            });
        });
    }
}
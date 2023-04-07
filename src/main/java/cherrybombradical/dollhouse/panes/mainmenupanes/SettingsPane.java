package cherrybombradical.dollhouse.panes.mainmenupanes;
import cherrybombradical.dollhouse.*;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;

import java.io.*;

public class SettingsPane extends Group {
    // Constructor for the SettingsPane class
    public SettingsPane(){
        // Create the border image view
        ImageView border = new ImageView(new Image("sprites/UI/windowBorder.png"));
        // Create the confirmation text
        Text confirmation = new Text("Settings");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 48);
        Font pixelFontButItsSmaller = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 14);
        confirmation.setFont(pixelFont);
        confirmation.setFill(Color.WHITE);
        // Create the music toggle button
        ToggleButton musicToggle = new ToggleButton("Music: On");
        musicToggle.setFont(pixelFontButItsSmaller);
        musicToggle.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");
        // Create the sound effects toggle button
        Button clearScores = new Button("Clear Scores");
        clearScores.setFont(pixelFontButItsSmaller);
        clearScores.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");
        // Create the back button
        Button back = new Button("Back");
        back.setFont(pixelFontButItsSmaller);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");
        // Create the HBox to hold the buttons
        HBox buttons = new HBox(20);
        buttons.setScaleX(2.25);
        buttons.setScaleY(2.25);
        buttons.getChildren().addAll(musicToggle, clearScores, back);
        // Add all elements to the group
        this.getChildren().addAll(border, confirmation, buttons);
        // Set the layout of the elements
        confirmation.setLayoutX(60);
        confirmation.setLayoutY(95);
        buttons.setLayoutX(225);
        buttons.setLayoutY(175);
        // Set the action for the music toggle button
        musicToggle.setOnAction(event -> {
            boolean selected = musicToggle.isSelected();
            System.out.println(selected);
            if (selected) {
                musicToggle.setText("Music: On");
                GameManager.unmuteMusic();
                GameManager.backgroundMusicMainMenu.setVolume(1);
            } else {
                musicToggle.setText("Music: Off");
                GameManager.muteMusic();
                GameManager.backgroundMusicMainMenu.setVolume(0);
            }
        });
        // Set the action for the Clear Scores button
        clearScores.setOnAction(event -> {
            File scoresFile = new File("Scores.txt");
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(scoresFile));
                writer.print("");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // Set the action for the back button
        back.setOnAction(event -> {
            ScaleTransition scaleTransition = Animations.expandOut(Duration.millis(300), this);
            scaleTransition.play();
            scaleTransition.setOnFinished(event1 -> {
                this.setVisible(false);
            });
        });
    }
}
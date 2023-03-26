package cherrybombradical.dollhouse.panes;

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
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SettingsPane extends Group {

    public SettingsPane(){
        ImageView border = new ImageView(new Image("sprites/UI/windowBorder.png"));

        Text confirmation = new Text("Settings");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 48);
        Font pixelFontButItsSmaller = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 14);
        confirmation.setFont(pixelFont);
        confirmation.setFill(Color.WHITE);

        ToggleButton musicToggle = new ToggleButton("Music: On");
        musicToggle.setFont(pixelFontButItsSmaller);
        musicToggle.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        ToggleButton soundFXToggle = new ToggleButton("Sound Effects: On");
        soundFXToggle.setFont(pixelFontButItsSmaller);
        soundFXToggle.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        Button back = new Button("Back");
        back.setFont(pixelFontButItsSmaller);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        HBox buttons = new HBox(20);
        buttons.setScaleX(2.25);
        buttons.setScaleY(2.25);
        buttons.getChildren().addAll(musicToggle, soundFXToggle, back);

        this.getChildren().addAll( border, confirmation, buttons);

        confirmation.setLayoutX(60);
        confirmation.setLayoutY(95);

        buttons.setLayoutX(225);
        buttons.setLayoutY(175);

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

        soundFXToggle.setOnAction(event -> {
            boolean selected = soundFXToggle.isSelected();
            System.out.println(selected);

            if (selected) {
                soundFXToggle.setText("Sound Effects: On");
                GameManager.unmuteMusic();

            } else {
                soundFXToggle.setText("Sound Effects: Off");
                GameManager.muteMusic();

            }
        });

        back.setOnAction(event -> {

            ScaleTransition scaleTransition = Animations.expandOut(Duration.millis(300), this);
            scaleTransition.play();
            scaleTransition.setOnFinished(event1 -> {
                this.setVisible(false);
            });
        });
    }
}
package cherrybombradical.dollhouse.panes.mainmenupanes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.AudioPlayer;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.scenes.IntroCutscene;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EnterNamePane extends Group {
    private final AudioPlayer buttonClickSFX = new AudioPlayer("Audio/Sounds/Select_OK4.mp3", false);
    private final AudioPlayer denySFX = new AudioPlayer("Audio/Sounds/deny.wav", false);

    public EnterNamePane(Pane pane){

        ImageView border = new ImageView(new Image("sprites/UI/windowBorder.png"));

        Text enterName = new Text("Please enter a name:");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 48);
        Font pixelFontButItsSmaller = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 14);
        enterName.setFont(pixelFont);
        enterName.setFill(Color.WHITE);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name Here");
        nameField.setFont(pixelFont);
        nameField.setStyle("-fx-background-color: #151515; -fx-text-fill: #FF0000;");

        Button begin = new Button("Begin");
        begin.setFont(pixelFontButItsSmaller);
        begin.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        Button back = new Button("Go Back");
        back.setFont(pixelFontButItsSmaller);
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        HBox buttons = new HBox(20);
        buttons.setScaleX(2.25);
        buttons.setScaleY(2.25);
        buttons.getChildren().addAll(begin,back);

        this.getChildren().addAll( border, enterName, nameField, buttons);

        enterName.setLayoutX(160);
        enterName.setLayoutY(95);

        nameField.setLayoutX(160);
        nameField.setLayoutY(125);
        nameField.setPrefSize(500, 50);

        buttons.setLayoutX(325);
        buttons.setLayoutY(225);


        begin.setOnAction(event -> {
            String name = nameField.getText().toString();
            if (name.trim().isEmpty()) {
                denySFX.setVolume(0.5);
                denySFX.play();
            } else {
                GameManager.playerName = name;
                buttonClickSFX.play();
                FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(2), pane);
                fadeTransition.setOnFinished(event1 -> {
                    GameManager.backgroundMusicMainMenu.stop();
                    Game.mainStage.setScene(new IntroCutscene());
                });
                fadeTransition.play();
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

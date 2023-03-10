package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class QuitConfirmPane extends Group {
    public QuitConfirmPane(){
        ImageView border = new ImageView(new Image("sprites/UI/windowBorder.png"));

        Text confirmation = new Text("Are you sure you want to quit?");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 48);
        Font pixelFontButItsSmaller = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 14);
        confirmation.setFont(pixelFont);
        confirmation.setFill(Color.WHITE);

        Button quit = new Button("Quit");
        quit.setFont(pixelFontButItsSmaller);
        quit.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        Button betterQuit = new Button("Quit in a cool way");
        betterQuit.setFont(pixelFontButItsSmaller);
        betterQuit.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        Button resume = new Button("Resume");
        resume.setFont(pixelFontButItsSmaller);
        resume.setStyle("-fx-background-color: #000000; -fx-text-fill: #FF0000; ");

        HBox buttons = new HBox(20);
        buttons.setScaleX(2.25);
        buttons.setScaleY(2.25);
        buttons.getChildren().addAll(quit,betterQuit,resume);

        this.getChildren().addAll( border, confirmation, buttons);

        confirmation.setLayoutX(60);
        confirmation.setLayoutY(95);

        buttons.setLayoutX(225);
        buttons.setLayoutY(175);


        quit.setOnAction(event -> {
            Game.mainStage.close();
        });

        betterQuit.setOnAction(event -> {
            try {
                Thread.sleep(999999);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        resume.setOnAction(event -> {

            ScaleTransition scaleTransition = Animations.expandOut(Duration.millis(300), this);
            scaleTransition.play();
            scaleTransition.setOnFinished(event1 -> {
                this.setVisible(false);
            });
        });
    }
}

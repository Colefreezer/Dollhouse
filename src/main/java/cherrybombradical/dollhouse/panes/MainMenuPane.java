package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.scenes.Map1Scene;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainMenuPane extends BorderPane {

    /**
     * This class extends BorderPane to create a custom main menu pane.
     */

    public MainMenuPane() {
        // Create Start Game button
        Button startGame = new Button();
        ImageView startGameImg = new ImageView(new Image("Images/ui_startImage.png"));
        startGameImg.setFitHeight(80);
        startGameImg.setPreserveRatio(true);
        startGame.setGraphic(startGameImg);
        startGame.setContentDisplay(ContentDisplay.TOP);
        startGame.setStyle("-fx-background-color: #000000");
        startGame.setLayoutX(250);
        startGame.setLayoutY(640);

        // Create Settings button
        Button settingsButton = new Button();
        ImageView settingsButtonImg = new ImageView(new Image("Images/ui_settings.png"));
        settingsButtonImg.setFitHeight(70);
        settingsButtonImg.setPreserveRatio(true);
        settingsButton.setGraphic(settingsButtonImg);
        settingsButton.setContentDisplay(ContentDisplay.TOP);
        settingsButton.setStyle("-fx-background-color: #000000");
        settingsButton.setLayoutX(650);
        settingsButton.setLayoutY(640);

        // Create Quit button
        Button quitButton = new Button();
        ImageView quitButtonImg = new ImageView(new Image("Images/ui_quit.png"));
        quitButtonImg.setFitHeight(70);
        quitButtonImg.setPreserveRatio(true);
        quitButton.setGraphic(quitButtonImg);
        quitButton.setContentDisplay(ContentDisplay.TOP);
        quitButton.setStyle("-fx-background-color: #000000");
        quitButton.setLayoutX(980);
        quitButton.setLayoutY(640);

        // Set the background image
        Image background = new Image("images/MMBG1.png");
        ImageView bg = new ImageView(background);

        // Set the game logo image
        Image gameLogo = new Image("images/Logo.png");
        ImageView logo1 = new ImageView(gameLogo);
        logo1.setX(46);
        logo1.setY(200);

        // Add all the UI elements to the pane
        this.getChildren().addAll(bg, logo1, startGame, settingsButton, quitButton);

        // When Start Game button is clicked, fade to black then change the scene to Map1Scene
        startGame.setOnAction(event -> {
            Rectangle fadeOut = new Rectangle(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
            fadeOut.setFill(Color.BLACK);
            this.getChildren().add(fadeOut);
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), fadeOut);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();

            fadeTransition.setOnFinished(event1 -> {
                Game.mainStage.setScene(new Map1Scene());
            });

        });
    }
}

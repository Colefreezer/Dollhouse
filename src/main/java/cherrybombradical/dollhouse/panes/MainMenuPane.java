package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.scenes.Map1Scene;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

        // Set the background images
        Image background0 = new Image("images/backgroundSky.png");
        ImageView bg0 = new ImageView(background0);

        Image background01 = new Image("images/backgroundSky.png");
        ImageView bg01 = new ImageView(background0);

        Image background1 = new Image("images/backgroundHouse.png");
        ImageView bg1 = new ImageView(background1);

        //Add the two sky images to an HBox
        HBox skybox = new HBox();
        skybox.getChildren().addAll(bg0, bg01);

        //Set the HBox skybox to an infinite scroll
        Animations.scroll(Duration.seconds(30), skybox).play();

        // Set the game logo image
        Image gameLogo = new Image("images/Logo.png");
        ImageView logo1 = new ImageView(gameLogo);
        logo1.setX(46);
        logo1.setY(200);

        // Add all the UI elements to the pane
        this.getChildren().addAll(skybox, bg1, logo1, startGame, settingsButton, quitButton);

        // When Start Game button is clicked, fade to black then change the scene to Map1Scene
        startGame.setOnAction(event -> {
            FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(2), this);
            fadeTransition.play();

            fadeTransition.setOnFinished(event1 -> {
                Game.mainStage.setScene(new Map1Scene());
            });

        });

        quitButton.setOnAction(event -> {

            QuitConfirmPane quitConfirmPane = new QuitConfirmPane();
            ScaleTransition scaleTransition = Animations.expandIn(Duration.millis(300), quitConfirmPane);
            scaleTransition.play();
            this.setCenter(quitConfirmPane);
            this.setAlignment(quitConfirmPane, Pos.CENTER);

        });


    }
}

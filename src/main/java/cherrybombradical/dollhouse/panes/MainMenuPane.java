package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.panes.QuitConfirmPane;
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
import javafx.util.Duration;

public class MainMenuPane extends BorderPane {
    // Static final images used in the menu
    private static final Image BACKGROUND_SKY = new Image("images/backgroundSky.png");
    private static final Image BACKGROUND_HOUSE = new Image("images/backgroundHouse.png");
    private static final Image GAME_LOGO = new Image("images/Logo.png");
    private static final Image UI_START = new Image("Images/ui_startImage.png");
    private static final Image UI_SETTINGS = new Image("Images/ui_settings.png");
    private static final Image UI_QUIT = new Image("Images/ui_quit.png");
    // Static final button and image dimensions and positions
    private static final double BUTTON_HEIGHT = 70.0;

    private static final double GAME_LOGO_X = 46.0;
    private static final double GAME_LOGO_Y = 200.0;
    // Static final durations for animations
    private static final Duration FADE_DURATION = Duration.seconds(2);
    private static final Duration SCALE_DURATION = Duration.millis(300);
    private static final Duration SCROLL_DURATION = Duration.seconds(30);
    // Static final durations for animations
    private final HBox skybox = new HBox();
    private final HBox buttonLayout = new HBox(20);
    private final ImageView bg0 = new ImageView(BACKGROUND_SKY);
    private final ImageView bg1 = new ImageView(BACKGROUND_HOUSE);
    private final ImageView logo1 = new ImageView(GAME_LOGO);
    private final Button startGame = new Button();
    private final Button settingsButton = new Button();
    private final Button quitButton = new Button();

    // Constructor for the main menu screen
    public MainMenuPane() {
        // Initialize the background elements and add the scrolling animation
        initializeBackground();
        // Initialize the game logo element
        initializeLogo();
        // Initialize the buttons for starting the game, accessing settings, and quitting
        initializeButtons();
        // Set the actions to be taken when each button is clicked
        setButtonActions();
        // Add all elements to the main menu screen
        addElements();
    }

    // Initializes the background elements and starts the scrolling animation
    private void initializeBackground() {

        skybox.getChildren().addAll(bg0, new ImageView(BACKGROUND_SKY));
        Animations.scroll(SCROLL_DURATION, skybox).play();
    }

    // Initializes the game logo element
    private void initializeLogo() {
        logo1.setX(GAME_LOGO_X);
        logo1.setY(GAME_LOGO_Y);
    }
    // Initializes the start, settings, and quit buttons
    private void initializeButtons() {
        initializeButton(startGame, UI_START);
        initializeButton(settingsButton, UI_SETTINGS);
        initializeButton(quitButton, UI_QUIT);

        buttonLayout.getChildren().addAll(startGame, settingsButton, quitButton);

        buttonLayout.setLayoutX(50);
        buttonLayout.setLayoutY(600);
    }
    // Initializes a given button with a given image and settings
    private void initializeButton(Button button, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(BUTTON_HEIGHT);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setStyle("-fx-background-color: #000000");
    }
    //Initializes the events to play when a button is clicked
    private void setButtonActions() {
        startGame.setOnAction(event -> {
            FadeTransition fadeTransition = Animations.fadeOut(FADE_DURATION, this);
            fadeTransition.setOnFinished(event1 -> Game.mainStage.setScene(new Map1Scene()));
            fadeTransition.play();
        });

        quitButton.setOnAction(event -> {
            QuitConfirmPane quitConfirmPane = new QuitConfirmPane();
            ScaleTransition scaleTransition = Animations.expandIn(SCALE_DURATION, quitConfirmPane);
            scaleTransition.play();
            setCenter(quitConfirmPane);
            setAlignment(quitConfirmPane, Pos.CENTER);
        });
    }
    //Adds all the elements into the pane
    private void addElements() {
        getChildren().addAll(skybox, bg1, logo1, buttonLayout);
        Animations.fadeIn(Duration.seconds(5), this).play();
    }
}

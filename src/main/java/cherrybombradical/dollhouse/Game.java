package cherrybombradical.dollhouse;

import cherrybombradical.dollhouse.scenes.MainMenuScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Game extends Application {

    private static final int SPLASH_SCREEN_WIDTH = 924;
    private static final int SPLASH_SCREEN_HEIGHT = 866;
    private static final int SPLASH_SCREEN_DURATION = 5000; // in milliseconds

    private Stage splashScreenStage; // stage for the splash screen
    public static Stage mainStage; // main stage for the game

    // The entry point of the application, which launches the game
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the splash screen
        createSplashScreen();

        // Set the main stage variable to the primary stage
        mainStage = primaryStage;

        // Set window properties
        mainStage.setResizable(false);
        mainStage.setTitle("Dollhouse");
        mainStage.getIcons().add(new Image("sprites/Maria_Walk1.png"));
        // Set the main menu scene as the starting scene


        // Show the main stage after the splash screen duration
        Timeline splashScreenTimer = new Timeline(
                new KeyFrame(Duration.millis(SPLASH_SCREEN_DURATION), event -> {
                    splashScreenStage.close();
                    mainStage.setScene(new MainMenuScene());
                    mainStage.show();
                })
        );
        splashScreenTimer.play();
    }

    private void createSplashScreen() {
        // Create the splash screen stage
        splashScreenStage = new Stage(StageStyle.UNDECORATED);
        splashScreenStage.setWidth(SPLASH_SCREEN_WIDTH);
        splashScreenStage.setHeight(SPLASH_SCREEN_HEIGHT);
        splashScreenStage.setResizable(false);

        // Create the splash screen image
        Image splashScreenImage = new Image("Images/Splash.png");
        ImageView splashScreenImageView = new ImageView(splashScreenImage);
        splashScreenImageView.setFitWidth(SPLASH_SCREEN_WIDTH);
        splashScreenImageView.setFitHeight(SPLASH_SCREEN_HEIGHT);

        // Add the splash screen image to a stack pane
        StackPane splashScreenPane = new StackPane(splashScreenImageView);

        // Create the loading animation text
        Text loadingText = new Text("Loading.");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 64);
        loadingText.setFont(pixelFont);
        loadingText.setFill(Color.WHITE);
        StackPane.setAlignment(loadingText, Pos.BOTTOM_RIGHT);
        splashScreenPane.getChildren().add(loadingText);

        // Set up the animation for the loading text
        Timeline loadingAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, event -> loadingText.setText("Loading.")),
                new KeyFrame(Duration.millis(300), event -> loadingText.setText("Loading..")),
                new KeyFrame(Duration.millis(600), event -> loadingText.setText("Loading...")),
                new KeyFrame(Duration.millis(800), event -> loadingText.setText("Loading."))
        );
        loadingAnimation.setCycleCount(Animation.INDEFINITE);
        loadingAnimation.play();

        Scene splashScreenScene = new Scene(splashScreenPane);
        splashScreenStage.setScene(splashScreenScene);

        // Show the splash screen stage
        splashScreenStage.show();
    }

}

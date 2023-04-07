package cherrybombradical.dollhouse;
import cherrybombradical.dollhouse.scenes.*;
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
    // Constants for the splash screen
    private static final int SPLASH_SCREEN_WIDTH = 500;
    private static final int SPLASH_SCREEN_HEIGHT = 500;
    private static final int SPLASH_SCREEN_DURATION = 4000;
    // Stage for the splash screen
    private Stage splashScreenStage;
    // Main stage of the game
    public static Stage mainStage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        createSplashScreen();
        mainStage = primaryStage;
        mainStage.setResizable(false);
        mainStage.setTitle("Dollhouse");
        // Create a timeline to close the splash screen after a certain duration
        Timeline splashScreenTimer = new Timeline(
                new KeyFrame(Duration.millis(SPLASH_SCREEN_DURATION), event -> {
                    splashScreenStage.close();
                    mainStage.setScene(new LogoCutscene());
                    mainStage.show();
                })
        );
        splashScreenTimer.play();
    }
    /**
     * Creates the splash screen for the game.
     */
    private void createSplashScreen() {
        splashScreenStage = new Stage(StageStyle.UNDECORATED);
        splashScreenStage.setWidth(SPLASH_SCREEN_WIDTH);
        splashScreenStage.setHeight(SPLASH_SCREEN_HEIGHT);
        splashScreenStage.setResizable(false);

        // Load the splash screen image
        Image splashScreenImage = new Image("Images/Splash.png");
        ImageView splashScreenImageView = new ImageView(splashScreenImage);
        splashScreenImageView.setFitWidth(SPLASH_SCREEN_WIDTH);
        splashScreenImageView.setFitHeight(SPLASH_SCREEN_HEIGHT);

        // Create a stack pane to hold the image and loading text
        StackPane splashScreenPane = new StackPane(splashScreenImageView);

        // Create the loading text
        Text loadingText = new Text("Loading.");
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 64);
        loadingText.setFont(pixelFont);
        loadingText.setFill(Color.WHITE);
        StackPane.setAlignment(loadingText, Pos.BOTTOM_RIGHT);
        splashScreenPane.getChildren().add(loadingText);

        // Create an animation for the loading text
        Timeline loadingAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, event -> loadingText.setText("Loading.")),
                new KeyFrame(Duration.millis(300), event -> loadingText.setText("Loading..")),
                new KeyFrame(Duration.millis(600), event -> loadingText.setText("Loading...")),
                new KeyFrame(Duration.millis(800), event -> loadingText.setText("Loading."))
        );
        loadingAnimation.setCycleCount(Animation.INDEFINITE);
        loadingAnimation.play();

        // Create the scene and show the splash screen
        Scene splashScreenScene = new Scene(splashScreenPane);
        splashScreenStage.setScene(splashScreenScene);
        splashScreenStage.show();
    }
}
// Rewritten code with comments
package cherrybombradical.dollhouse.panes;
import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
public class CreditsPane extends Pane {
    private static final ImageView black = new ImageView(new Image("Images/Black.png"));
    // ImageView to display logo
    private final ImageView logo = new ImageView(new Image("Images/LogoCredits.png"));
    // String containing credits
    private final String creditsString = "Created by:\n\n" +
            "Colton Thibert\n" +
            "Cole Dennie\n\n" +
            "Graphics:\n" +
            "Cole Dennie\n\n" +
            "Music by:\n" +
            "Cole Dennie\n\n" +
            "Programming:\n" +
            "Colton Thibert\n" +
            "Cole Dennie\n\n" +
            "Attributions:\n" +
            "Alpha Beta BRK Font - Brian Kent\n\n" +
            "Special Thanks:\n" +
            "Câi Filiault\n" +
            "Christopher Takaki\n" +
            "Peter Nikita\n" +
            "Micheal Girimonte\n\n\n" +
            "Thank you for playing!";
    // Text object to display credits
    private final Text creditsText = new Text(creditsString);
    // Font for credits text
    private final Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 48);
    public CreditsPane(){
        this.getChildren().add(black);

        // Play background music
        GameManager.musicBoxBackgroundMusic.play();

        // Set logo position
        logo.relocate(GameManager.SCREEN_WIDTH / 4, GameManager.SCREEN_HEIGHT / 4);

        // Set font and color of credits text
        creditsText.setFont(pixelFont);
        creditsText.setFill(Color.WHITE);
        creditsText.setTextAlignment(TextAlignment.CENTER);

        // Set position of credits text
        creditsText.relocate(320, 900);


        // Create group to contain logo and credits text
        Group group = new Group(logo, creditsText);

        // Add group to pane
        this.getChildren().addAll(group);

        // Fade in transition for logo and credits text
        FadeTransition fadeLogo = new FadeTransition(Duration.seconds(2.5), group);
        fadeLogo.setFromValue(0);
        fadeLogo.setToValue(1);

        // Pause transition
        PauseTransition pause = new PauseTransition(Duration.seconds(1.0));

        // Move up transition for logo and credits text
        TranslateTransition moveUp = new TranslateTransition(Duration.seconds(25), group);
        moveUp.setByY(-2500);
        moveUp.setInterpolator(Interpolator.EASE_IN);

        // Sequential transition to play fade in, pause, and move up transitions
        SequentialTransition startCredits = new SequentialTransition(fadeLogo, pause, moveUp);
        startCredits.play();

        //Skip the credits sequence if the mouse is clicked and go to Main Menu
        this.setOnMouseClicked(event -> {
            GameManager.musicBoxBackgroundMusic.stop();
            Game.mainStage.setScene(new MainMenuScene());
        });

        //Go to main menu once credits is finished
        startCredits.setOnFinished(event -> {
            GameManager.musicBoxBackgroundMusic.stop();
            Game.mainStage.setScene(new MainMenuScene());
        });

    }
}
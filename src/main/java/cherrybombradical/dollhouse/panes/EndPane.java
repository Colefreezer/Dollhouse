package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import cherrybombradical.dollhouse.scenes.Map2Scene;
import cherrybombradical.dollhouse.scenes.Map5Scene;
import cherrybombradical.dollhouse.scenes.Map7Scene;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EndPane extends Pane {
    //End my pane
    private static final ImageView black = new ImageView(new Image("Images/Black.png"));
    private static final ImageView endScreen = new ImageView(new Image("Images/EndScreen1.png"));
    private static final ImageView endScreenWords = new ImageView(new Image("Images/EndScreenWords.png"));
    private static final ImageView frame1 = new ImageView(new Image("sprites/UI/CS/Frame1.png"));
    private static final ImageView frame2 = new ImageView(new Image("sprites/UI/CS/Frame2.png"));
    private static final ImageView frame3 = new ImageView(new Image("sprites/UI/CS/Frame3.png"));
    private final AudioPlayer end1SFX = new AudioPlayer("Audio/Sounds/SFX_End1.mp3", false);

    public EndPane(){
        System.out.println("Finished Game");
        frame1.setX(-500);
        frame2.setY(-500);
        frame3.setX(700);

   

        // Add all the nodes to the group
        this.getChildren().addAll(endScreen, black, frame1, frame2, frame3);

        TranslateTransition frame1In = new TranslateTransition(Duration.millis(400), frame1);
        frame1In.setInterpolator(Interpolator.EASE_OUT);
        frame1In.setFromX(0);
        frame1In.setToX(500);

        PauseTransition frame1Pause = new PauseTransition(Duration.millis(400));
        end1SFX.play();

        TranslateTransition frame2In = new TranslateTransition(Duration.millis(400), frame2);
        frame2In.setInterpolator(Interpolator.EASE_OUT);
        frame2In.setFromY(0);
        frame2In.setToY(500);

        PauseTransition frame2Pause = new PauseTransition(Duration.millis(400));

        TranslateTransition frame3In = new TranslateTransition(Duration.millis(400), frame3);
        frame3In.setInterpolator(Interpolator.EASE_OUT);
        frame3In.setFromX(0);
        frame3In.setToX(-700);

        PauseTransition frame3Pause = new PauseTransition(Duration.millis(1220));

        ScaleTransition frame1Scale = new ScaleTransition(Duration.millis(700), frame1);
        frame1Scale.setFromX(1.0);
        frame1Scale.setToX(9.0);
        frame1Scale.setFromY(1.0);
        frame1Scale.setToY(9.0);

        ScaleTransition frame2Scale = new ScaleTransition(Duration.millis(700), frame2);
        frame2Scale.setFromX(1.0);
        frame2Scale.setToX(9.0);
        frame2Scale.setFromY(1.0);
        frame2Scale.setToY(9.0);

        TranslateTransition frame2ScalePush = new TranslateTransition(Duration.millis(700), frame2);
        frame2ScalePush.setByY(-900);

        ScaleTransition frame3Scale = new ScaleTransition(Duration.millis(700), frame3);
        frame3Scale.setFromX(1.0);
        frame3Scale.setToX(9.0);
        frame3Scale.setFromY(1.0);
        frame3Scale.setToY(9.0);

        FadeTransition fadeToResults = new FadeTransition(Duration.millis(700), black);
        fadeToResults.setFromValue(255);
        fadeToResults.setToValue(0);

        ScaleTransition resultsScreenIn = new ScaleTransition(Duration.millis(600), endScreen);
        resultsScreenIn.setFromX(3.0);
        resultsScreenIn.setToX(1.0);
        resultsScreenIn.setFromY(3.0);
        resultsScreenIn.setToY(1.0);
        resultsScreenIn.setInterpolator(Interpolator.EASE_OUT);

        ParallelTransition goToResults = new ParallelTransition(frame1Scale, frame2Scale, frame2ScalePush, frame3Scale, fadeToResults, resultsScreenIn);

        SequentialTransition endScreenSequence = new SequentialTransition(frame1In, frame1Pause, frame2In, frame2Pause, frame3In, frame3Pause, goToResults);

        endScreenSequence.setOnFinished(event -> {
            this.getChildren().add(endScreenWords);
            endScreenWords.setOpacity(0);
            PauseTransition delay1 = new PauseTransition(Duration.millis(600));
            FadeTransition fadeInWords = new FadeTransition(Duration.millis(1200), endScreenWords);
            fadeInWords.setToValue(255);
            fadeInWords.setFromValue(0);
            ScaleTransition zoomWords = new ScaleTransition(Duration.millis(1200), endScreenWords);
            zoomWords.setFromX(2.0);
            zoomWords.setFromY(2.0);
            zoomWords.setToY(1.0);
            zoomWords.setToX(1.0);
            ParallelTransition wordsAni = new ParallelTransition(fadeInWords, zoomWords);

            SequentialTransition sequencePart2 = new SequentialTransition(delay1, wordsAni);
            sequencePart2.play();
        });

        endScreenSequence.play();


    }

}

package cherrybombradical.dollhouse;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class Animations {


    public static Timeline spriteWalk (Player player, ImageView imageView, Image[] sprites) {

        String[] footstepPaths = {
                "Audio/Sounds/SFX_footstep1.mp3",
                "Audio/Sounds/SFX_footstep2.mp3",
                "Audio/Sounds/SFX_footstep3.mp3",
                "Audio/Sounds/SFX_footstep4.mp3",
                "Audio/Sounds/SFX_footstep5.mp3"
        };

        Random randomFootstep = new Random();
        Random randomVolume = new Random();

        KeyFrame frame1 = new KeyFrame(
                Duration.millis(0),
                e -> {
                    imageView.setImage(sprites[0]);
                }
        );
        KeyFrame frame2 = new KeyFrame(
                Duration.millis(200),
                e -> {
                    imageView.setImage(sprites[1]);
                    AudioPlayer footstepSounds = new AudioPlayer(footstepPaths[randomFootstep.nextInt(5)], false);
                    footstepSounds.setVolume(0.2 + randomVolume.nextDouble(0.3));
                    footstepSounds.play();
                }
        );
        KeyFrame frame3 = new KeyFrame(
                Duration.millis(400),
                e -> {
                    imageView.setImage(sprites[0]);
                }
        );
        KeyFrame frame4 = new KeyFrame(
                Duration.millis(600),
                e -> {
                    imageView.setImage(sprites[2]);
                    AudioPlayer footstepSounds = new AudioPlayer(footstepPaths[randomFootstep.nextInt(5)], false);
                    footstepSounds.setVolume(0.2 + randomVolume.nextDouble(0.3));
                    footstepSounds.play();
                }
        );
        KeyFrame frame5 = new KeyFrame(
                Duration.millis(800),
                e -> {
                    imageView.setImage(sprites[0]);
                }
        );

        Timeline walking = new Timeline(
                frame1,
                frame2,
                frame3,
                frame4,
                frame5
        );

        walking.setCycleCount(Animation.INDEFINITE);

        return walking;
    }

    public static TranslateTransition hover(Duration duration, Node node){
        TranslateTransition translateTransition = new TranslateTransition(duration, node);
        translateTransition.setFromY(0);
        translateTransition.setToY(25);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(true);
        return translateTransition;
    }

    public static FadeTransition fadeOut(Duration duration, Pane pane){
        Rectangle fadeOut = new Rectangle(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        fadeOut.setFill(Color.BLACK);
        pane.getChildren().add(fadeOut);
        FadeTransition fadeTransition = new FadeTransition(duration, fadeOut);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        return fadeTransition;
    }

    public static FadeTransition fadeIn(Duration duration, Pane pane){
        FadeTransition fadeTransition = new FadeTransition(duration, pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        return fadeTransition;
    }

    public static ScaleTransition expandIn(Duration duration, Node node){
        ScaleTransition scaleTransition = new ScaleTransition(duration, node);
        scaleTransition.setFromY(0); scaleTransition.setFromX(0);
        scaleTransition.setToY(1); scaleTransition.setToX(1);
        scaleTransition.setCycleCount(1);
        return scaleTransition;

    }

    public static ScaleTransition expandOut(Duration duration, Node node){
        ScaleTransition scaleTransition = new ScaleTransition(duration, node);
        scaleTransition.setFromY(1); scaleTransition.setFromX(1);
        scaleTransition.setToY(0); scaleTransition.setToX(0);
        scaleTransition.setCycleCount(1);
        return scaleTransition;

    }

    public static TranslateTransition scroll(Duration duration, Node node){
        TranslateTransition translateTransition = new TranslateTransition(duration, node);
        translateTransition.setFromX(0);
        translateTransition.setToX(-GameManager.SCREEN_WIDTH);
        translateTransition.setInterpolator(Interpolator.LINEAR);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        return translateTransition;
    }

    public static TranslateTransition UIShow (Node node){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), node);
        translateTransition.setFromX(-900);
        translateTransition.setToX(0);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);
        return translateTransition;
    }
    public static TranslateTransition UIMove (Node node){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), node);
        translateTransition.setFromX(0);
        translateTransition.setToX(-900);
        translateTransition.setInterpolator(Interpolator.EASE_IN);
        return translateTransition;
    }

    public static SequentialTransition mapIntro(Node mapLayer1, Node mapLayer2, Node mapPointer, int mapXVar, int mapYVar){
        mapPointer.relocate(0, 999);
        TranslateTransition translateTransition2 =
                new TranslateTransition(Duration.millis(200), mapLayer1);
        translateTransition2.setFromY(-640);
        translateTransition2.setToY(0);


        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(200), mapLayer2);
        translateTransition.setFromX(-640);
        translateTransition.setToX(0);

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), mapLayer2);
        scale.setFromX(0);
        scale.setToX(1);

        ScaleTransition scale2 = new ScaleTransition(Duration.millis(0), mapLayer2);
        scale2.setToX(0);

        ParallelTransition parallelTransition2 = new ParallelTransition();
        parallelTransition2.getChildren().addAll(translateTransition, scale);

        ParallelTransition parallelTransition1 = new ParallelTransition();
        parallelTransition1.getChildren().addAll(translateTransition2, scale2);


        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                parallelTransition1, parallelTransition2
        );

        sequentialTransition.setOnFinished(event -> {
            mapPointer.relocate(mapXVar, mapYVar);

        });
        return sequentialTransition;
    }

    public static SequentialTransition intro(ImageView logo1Shadow, ImageView logo2, ImageView logo1, ImageView Black) {
        logo1.relocate(0, 0);
        logo2.relocate(46, 200);
        logo1Shadow.relocate(46, 200);


        ScaleTransition logo1ShadowScale = new ScaleTransition(Duration.millis(0.001), logo1Shadow);
        logo1ShadowScale.setFromY(0);
        logo1ShadowScale.setToY(0);

        TranslateTransition logo2Move = new TranslateTransition(Duration.millis(0.001), logo2);
        logo2Move.setFromX(0);
        logo2Move.setToX(-1300);


        ScaleTransition scale = new ScaleTransition(Duration.millis(2000), logo1);
        scale.setFromX(60);
        scale.setFromY(60);
        scale.setToX(1);
        scale.setToY(1);

        TranslateTransition slideIn = new TranslateTransition(Duration.millis(2000), logo1);
        slideIn.setFromX(9190);
        slideIn.setFromY(-700);
        slideIn.setToX(46);
        slideIn.setToY(200);

        ParallelTransition Frame1 = new ParallelTransition();
        Frame1.getChildren().addAll(logo2Move, logo1ShadowScale, scale, slideIn);

        //================ Frame 2 ================

        ScaleTransition scale2 = new ScaleTransition(Duration.millis(400), logo1Shadow);
        scale2.setFromY(0);
        scale2.setToY(1);

        TranslateTransition slideIn2 = new TranslateTransition(Duration.millis(400), logo1Shadow);
        slideIn2.setFromY(100);
        slideIn2.setToY(0);



        ParallelTransition Frame2 = new ParallelTransition();
        Frame2.getChildren().addAll(scale2, slideIn2);




        //last frame

        TranslateTransition knifeIn = new TranslateTransition(Duration.millis(700), logo2);
        knifeIn.setFromY(1900);
        knifeIn.setFromX(-1900);
        knifeIn.setToX(0);
        knifeIn.setToY(0);

        FadeTransition fadeBlack = new FadeTransition(Duration.millis(700), Black);
        fadeBlack.setFromValue(1.0);
        fadeBlack.setToValue(0.0);






        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(
                Frame1, Frame2, knifeIn, fadeBlack
        );

        sequentialTransition.setOnFinished(event -> {
            Black.setImage(null);
        });


        return sequentialTransition;
    }


    public static ScaleTransition buttonHover(Node node){
        ScaleTransition buttonHover = new ScaleTransition(Duration.millis(100), node);
        buttonHover.setFromY(1); buttonHover.setFromX(1);
        buttonHover.setToY(1.3); buttonHover.setToX(1.3);
        buttonHover.setCycleCount(1);
        return buttonHover;
    }

    public static ScaleTransition buttonHoverOff(Node node){
        ScaleTransition buttonHover = new ScaleTransition(Duration.millis(50), node);
        buttonHover.setFromY(1.3); buttonHover.setFromX(1.3);
        buttonHover.setToY(1); buttonHover.setToX(1);
        buttonHover.setCycleCount(1);
        return buttonHover;
    }

}

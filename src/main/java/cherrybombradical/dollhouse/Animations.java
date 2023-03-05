package cherrybombradical.dollhouse;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animations {

    public static Timeline spriteWalk (Player player, ImageView imageView, Image[] sprites){
        KeyFrame frame1 = new KeyFrame(Duration.millis(0), e -> {imageView.setImage(sprites[0]);});
        KeyFrame frame2 = new KeyFrame(Duration.millis(200), e -> {imageView.setImage(sprites[1]);});
        KeyFrame frame3 = new KeyFrame(Duration.millis(400), e -> {imageView.setImage(sprites[0]);});
        KeyFrame frame4 = new KeyFrame(Duration.millis(600), e -> {imageView.setImage(sprites[2]);});
        KeyFrame frame5 = new KeyFrame(Duration.millis(800), e -> {imageView.setImage(sprites[0]);});

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

    public static SequentialTransition mapIntro(Node mapLayer1, Node mapLayer2){
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
        return sequentialTransition;
    }
}

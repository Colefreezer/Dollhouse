package cherrybombradical.dollhouse;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animations {

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
        Rectangle fadeIn = new Rectangle(0, 0, GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT);
        fadeIn.setFill(Color.BLACK);
        pane.getChildren().add(fadeIn);
        FadeTransition fadeTransition = new FadeTransition(duration, fadeIn);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
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
}

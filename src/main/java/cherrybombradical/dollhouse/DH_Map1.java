package cherrybombradical.dollhouse;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DH_Map1 extends Application {
    private static final int IMAGE_WIDTH = 32;
    private static final int IMAGE_HEIGHT = 57;
    private static final int MOVE_DISTANCE = 300;

    private boolean isMoving = false;

    @Override
    public void start(Stage primaryStage) {
        Image image1 = new Image("sprites/Maria_Walk1.png");
        Image image2 = new Image("sprites/Maria_Walk2.png");
        ImageView imageView = new ImageView(image1);
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        StackPane root = new StackPane(imageView);

//Scene play
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dollhouse");
        Scene scene = new Scene(root, 1449, 814);
        primaryStage.setScene(scene);
        primaryStage.show();
        imageView.requestFocus();
        scene.setOnKeyPressed(event -> {
            if (!isMoving) {
                TranslateTransition tt = new TranslateTransition(Duration.seconds(1), imageView);
                if (event.getCode() == KeyCode.LEFT) {
                    double newX = imageView.getTranslateX() - MOVE_DISTANCE;
                    if (newX < -(scene.getWidth() / 2 - IMAGE_WIDTH / 2)) {
                        newX = -(scene.getWidth() / 2 - IMAGE_WIDTH / 2);
                    }
                    imageView.setScaleX(-1); // flip horizontally
                    imageView.setImage(image2); // change image
                    tt.setToX(newX);
                } else if (event.getCode() == KeyCode.RIGHT) {
                    double newX = imageView.getTranslateX() + MOVE_DISTANCE;
                    if (newX > (scene.getWidth() / 2 - IMAGE_WIDTH / 2)) {
                        newX = (scene.getWidth() / 2 - IMAGE_WIDTH / 2);
                    }
                    imageView.setScaleX(1); // restore original orientation
                    imageView.setImage(image1); // change image back
                    tt.setToX(newX);
                }
                isMoving = true;
                imageView.setImage(image2); // change image
                tt.setOnFinished(evt -> {
                    isMoving = false;
                    imageView.setImage(image1); // change image back when done moving
                });
                tt.play();
            }
        });


    }

    public static void main(String[] args) {
        launch(args);
    }
}
package cherrybombradical.dollhouse;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.security.Key;

public class Player extends Pane {


    private Image standingImage;
    private Image movingImage;
    private ImageView imageView;
    private double xPosition;
    private double yPosition;
    private double moveSpeed;
    private boolean isMoving = false;

    private boolean facingRight = true;

    public Player(String standingImagePath, String movingImagePath, double xPosition, double yPosition, double moveSpeed) {
        this.standingImage = new Image(standingImagePath);
        this.movingImage = new Image(movingImagePath);
        this.imageView = new ImageView(standingImage);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveSpeed = moveSpeed;
        this.imageView.setX(xPosition);
        this.imageView.setY(yPosition);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void moveLeft() {
        xPosition -= moveSpeed;
        imageView.setLayoutX(xPosition);

        if (!isMoving) {
            imageView.setImage(movingImage);
            isMoving = true;
        }

        if (facingRight) {
            imageView.setScaleX(-1);
            facingRight = false;
        }

    }

    public void moveRight() {
        xPosition += moveSpeed;
        imageView.setLayoutX(xPosition);

        if (!isMoving) {
            imageView.setImage(movingImage);
            isMoving = true;
        }

        if (!facingRight) {
            imageView.setScaleX(1);
            facingRight = true;
        }
    }

    public void stopMoving(){
        imageView.setImage(standingImage);
        isMoving = false;
    }
}

package cherrybombradical.dollhouse;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.security.Key;

public class Player extends Pane {
    private ImageView standingSprite;
    private double xPosition;
    private double yPosition;
    private double moveSpeed;
    private boolean isMoving = false;

    private boolean facingRight = true;

    public Player(String standingSprite, double xPosition, double yPosition, double moveSpeed) {
        this.standingSprite = new ImageView(new Image(standingSprite));
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveSpeed = moveSpeed;
        this.standingSprite.setX(xPosition);
        this.standingSprite.setY(yPosition);
    }

    public ImageView getImageView() {
        return standingSprite;
    }

    public void moveLeft() {
        xPosition -= moveSpeed;
        standingSprite.setLayoutX(xPosition);

        if (facingRight) {
            standingSprite.setScaleX(-1);
            facingRight = false;
        }

    }

    public void moveRight() {
        xPosition += moveSpeed;
        standingSprite.setLayoutX(xPosition);

        if (!facingRight) {
            standingSprite.setScaleX(1);
            facingRight = true;
        }
    }
}

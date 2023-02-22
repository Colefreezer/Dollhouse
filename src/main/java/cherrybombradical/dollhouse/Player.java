package cherrybombradical.dollhouse;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Player extends Pane {

    // instance variables
    private Image standingImage;    // the image to use when the player is standing still
    private Image movingImage;      // the image to use when the player is moving
    private ImageView imageView;   // the player's ImageView
    private double xPosition;       // the player's x position on the screen
    private double yPosition;       // the player's y position on the screen
    private double moveSpeed;       // the player's move speed
    private boolean isMoving = false;   // whether the player is currently moving
    private boolean facingRight = true; // whether the player is currently facing right

    /**
     * Constructor for the Player class
     * @param standingImagePath - the file path to the image to use when the player is standing still
     * @param movingImagePath - the file path to the image to use when the player is moving
     * @param xPosition - the initial x position of the player on the screen
     * @param yPosition - the initial y position of the player on the screen
     * @param moveSpeed - the speed at which the player moves
     */
    public Player(String standingImagePath, String movingImagePath, double xPosition, double yPosition, double moveSpeed) {
        // set the instance variables
        this.standingImage = new Image(standingImagePath);
        this.movingImage = new Image(movingImagePath);
        this.imageView = new ImageView(standingImage);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveSpeed = moveSpeed;
        this.imageView.setX(xPosition);
        this.imageView.setY(yPosition);
    }

    /**
     * Getter method for the player's ImageView
     * @return - the player's ImageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Move the player left by moveSpeed pixels
     */
    public void moveLeft() {
        xPosition -= moveSpeed;
        imageView.setLayoutX(xPosition);

        // if the player is not already moving, set the image to the moving image
        if (!isMoving) {
            imageView.setImage(movingImage);
            isMoving = true;
        }

        // if the player is facing right, flip the image horizontally to face left
        if (facingRight) {
            imageView.setScaleX(-1);
            facingRight = false;
        }
    }

    /**
     * Move the player right by moveSpeed pixels
     */
    public void moveRight() {
        xPosition += moveSpeed;
        imageView.setLayoutX(xPosition);

        // if the player is not already moving, set the image to the moving image
        if (!isMoving) {
            imageView.setImage(movingImage);
            isMoving = true;
        }

        // if the player is facing left, flip the image horizontally to face right
        if (!facingRight) {
            imageView.setScaleX(1);
            facingRight = true;
        }
    }

    /**
     * Stop the player from moving and set the image to the standing image
     */
    public void stopMoving(){
        imageView.setImage(standingImage);
        isMoving = false;
    }
}

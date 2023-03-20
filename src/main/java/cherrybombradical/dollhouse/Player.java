package cherrybombradical.dollhouse;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Player extends Pane {

    // instance variables
    private String[] spritePaths = {"sprites/Maria_Walk1.png", "sprites/Maria_Walk2.png", "sprites/Maria_Walk3.png"};
    private Image[] sprites = {new Image(spritePaths[0]), new Image(spritePaths[1]), new Image(spritePaths[2])};
    private Animation walkingAnimation;

    private ImageView imageView;   // the player's ImageView
    private double xPosition;       // the player's x position on the screen
    private double yPosition;       // the player's y position on the screen
    private double moveSpeed = 200;       // the player's move speed
    private boolean isMoving = false;   // whether the player is currently moving
    private boolean facingRight = true; // whether the player is currently facing right
    private final TranslateTransition transition;

    /**
     * Constructor for the Player class
     * //@param standingImagePath - the file path to the image to use when the player is standing still
     * //@param movingImagePath - the file path to the image to use when the player is moving
     * @param xPosition - the initial x position of the player on the screen
     * @param yPosition - the initial y position of the player on the screen
     * @param moveSpeed - the speed at which the player moves
     */
    public Player(double xPosition, double yPosition, double moveSpeed) {
        // set the instance variables
        //this.standingImage = new Image(standingImagePath);
        //this.movingImage = new Image(movingImagePath);

        this.imageView = new ImageView(sprites[0]);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveSpeed = moveSpeed;
        this.imageView.setX(xPosition);
        this.imageView.setY(yPosition);



        // create a TranslateTransition for movement
        transition = new TranslateTransition();
        transition.setNode(imageView);
        transition.setDuration(Duration.millis(500));
        transition.setInterpolator(Interpolator.LINEAR);
    }

    /**
     * Getter method for the player's ImageView
     * @return - the player's ImageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    public double getXPosition(){
        return xPosition;
    }
    public boolean isMoving(){
        return isMoving;
    }

    /**
     * Move the player left by moveSpeed pixels
     */
    public void moveLeft() {
        if (!GameManager.mapToggle){
            transition.setByX(-moveSpeed);
            transition.play();
            System.out.println("Moving");
            // if the player is not already moving, set the image to the moving image
            if (!isMoving) {
                isMoving = true;
                walkingAnimation = Animations.spriteWalk(this, imageView, sprites);
                walkingAnimation.play();

            }

            // if the player is facing right, flip the image horizontally to face left
            if (facingRight) {
                imageView.setScaleX(-1);
                facingRight = false;
            }
        }

    }

    /**
     * Move the player right by moveSpeed pixels
     */
    public void moveRight() {
        if (!GameManager.mapToggle){
            transition.setByX(moveSpeed);
            transition.play();

            System.out.println("Moving");
            // if the player is not already moving, set the image to the moving image
            if (!isMoving) {
                isMoving = true;
                walkingAnimation = Animations.spriteWalk(this, imageView, sprites);
                walkingAnimation.play();
            }

            // if the player is facing left, flip the image horizontally to face right
            if (!facingRight) {
                imageView.setScaleX(1);
                facingRight = true;
            }
        }


    }

    /**
     * Stop the player from moving and set the image to the standing image
     */
    public void stopMoving(){
        isMoving = false;
        if (walkingAnimation != null) {
            walkingAnimation.pause();
            walkingAnimation = null;
            imageView.setImage(sprites[0]); // set the image to standing
        }
    }

    public void pauseMovement(){

    }


}

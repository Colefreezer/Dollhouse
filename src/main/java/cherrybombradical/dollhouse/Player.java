package cherrybombradical.dollhouse;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
public class Player extends Pane {
    // Declare variables
    private String[] spritePaths = {"sprites/Maria_Walk1.png", "sprites/Maria_Walk2.png", "sprites/Maria_Walk3.png", "sprites/Maria_Smile.png"};
    private Image[] sprites = {new Image(spritePaths[0]), new Image(spritePaths[1]), new Image(spritePaths[2])};
    private Animation walkingAnimation;
    private ImageView imageView;
    private double xPosition;
    private double yPosition;
    private double moveSpeed = 200;
    private boolean isMoving = false;
    private boolean facingRight = true;
    public boolean canMoveRight = true;
    public boolean canMoveLeft = true;

    private final TranslateTransition transition;
    // Constructor
    public Player(double xPosition, double yPosition, double moveSpeed) {
        this.imageView = new ImageView(sprites[0]);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.moveSpeed = moveSpeed;
        this.imageView.setX(xPosition);
        this.imageView.setY(yPosition);
        transition = new TranslateTransition();
        transition.setNode(imageView);
        transition.setDuration(Duration.millis(500));
        transition.setInterpolator(Interpolator.LINEAR);
    }
    // Getter for imageView
    public ImageView getImageView() {
        return imageView;
    }
    // Getter for xPosition
    public double getXPosition(){
        return xPosition;
    }
    // Getter for isMoving
    public boolean isMoving(){
        return isMoving;
    }
    // Method to move left
    public void moveLeft() {
        if (!GameManager.mapToggle){
            if (canMoveLeft){
                transition.setByX(-moveSpeed);
                transition.play();
                System.out.println("Moving");
                if (!isMoving) {
                    isMoving = true;
                    walkingAnimation = Animations.spriteWalk(this, imageView, sprites);
                    walkingAnimation.play();
                }
                if (facingRight) {
                    imageView.setScaleX(-1);
                    facingRight = false;
                }
            }
            else {
                transition.setByX(0);
                transition.stop();
            }
        }
    }
    // Method to move right
    public void moveRight() {
        if (!GameManager.mapToggle){
            if (canMoveRight){
                transition.setByX(moveSpeed);
                transition.play();
                System.out.println("Moving");
                if (!isMoving) {
                    isMoving = true;
                    walkingAnimation = Animations.spriteWalk(this, imageView, sprites);
                    walkingAnimation.play();
                }
                if (!facingRight) {
                    imageView.setScaleX(1);
                    facingRight = true;
                }
            }
            else {
                transition.setByX(0);
                transition.stop();
            }

        }

    }
    // Method to stop moving
    public void stopMoving(){
        isMoving = false;
        if (walkingAnimation != null) {
            walkingAnimation.pause();
            walkingAnimation = null;
            imageView.setImage(sprites[0]);
        }
    }
}
package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map3Pane extends Pane {

    // Store the current map ID
    public static int mapID = 2;
    // ==== MAP = OUTSIDE
    public static Player player;
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);

    public Map3Pane(){
        System.out.println("Map 3 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        if (GameManager.backgroundMusicIndoors.isPlaying()){
            GameManager.backgroundMusicIndoors.stop();
            GameManager.backgroundMusicOutside.play();
        }

        // Create the player object and add its ImageView to the scene
        player = new Player(GameManager.getNewLocation(), 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Create HUD
        hud = new HUDPane();

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        //Load the Arrow Image for when near the left door
        ImageView arrowL = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowL.setX(200);
        arrowL.setY(150);
        arrowL.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowL).play();

        //Set Left Arrow HitBox
        Rectangle leftArrowHitBox = new Rectangle(250, 260, 50, 250);
        leftArrowHitBox.setVisible(false);

        //Load the Arrow Image for when near the right door
        ImageView arrowR = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowR.setX(1360);
        arrowR.setY(200);
        arrowR.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowR).play();

        //Set Right Arrow HitBox
        Rectangle rightArrowHitBox = new Rectangle(1250, 260, 125, 250);
        rightArrowHitBox.setVisible(false);

        //Set Left Boundary
        Rectangle rightBound = new Rectangle(1400, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(10, 260, 50, 250);
        leftBound.setVisible(false);

        // Add all the nodes to the group
        this.getChildren().addAll(map, arrowL, arrowR, leftArrowHitBox, rightArrowHitBox,
                hud, player.getImageView(), lighting, rightBound, leftBound);

        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(leftArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowL.setVisible(true);
                this.setOnMouseClicked(event -> {
                    //Fade Transition
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.6), this);
                    fadeTransition.play();
                    //Door Sound
                    GameManager.doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(75);
                    //Stop the players movement animation
                    player.stopMoving();
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map4Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowL.setVisible(false);
            }
        });

        //Detect if player (image) is colliding with the Right HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(rightArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowR.setVisible(true);

                this.setOnMouseClicked(event -> {
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(1), this);
                    fadeTransition.play();
                    GameManager.setNewLocation(0);
                    GameManager.doorSFX.play();
                    //Stop the players movement animation
                    player.stopMoving();
                    fadeTransition.setOnFinished(event1 -> {
                        Game.mainStage.setScene(new Map2Scene());
                    });
                });

            } else {
                // player is not colliding with door hitbox
                arrowR.setVisible(false);
            }
        });

        //Right Boundary
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(rightBound.getBoundsInParent())) {
                player.canMoveRight = false;

            } else {
                player.canMoveRight = true;
            }
        });

        //Left Boundary
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(leftBound.getBoundsInParent())) {
                player.canMoveLeft = false;

            } else {
                player.canMoveLeft = true;
            }
        });

    }

}

package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map10Pane extends Pane {

    // Store the current map ID
    public static int mapID = 9;
    // ==== MAP = First Room Attic
    public static Player player;
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    public Map10Pane(){

        System.out.println("Map 10 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();

        // Create the player object
        player = new Player(GameManager.getNewLocation(), 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Create HUD
        hud = new HUDPane();

        //Load the Arrow Image for when near the left door
        ImageView arrowL = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowL.setX(225);
        arrowL.setY(400);
        arrowL.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowL).play();

        //Set Left Arrow HitBox
        Rectangle leftArrowHitBox = new Rectangle(250, 260, 100, 250);
        leftArrowHitBox.setVisible(false);

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        ImageView controlsWindow = new ImageView(new Image("sprites/UI/controlsBorder.png"));
        controlsWindow.relocate(300, 150);
        Animations.expandIn(Duration.millis(500), controlsWindow).play();
        controlsWindow.setOnMouseClicked(event -> {
            controlsWindow.setVisible(false);
        });

        //Set Left Boundary
        Rectangle rightBound = new Rectangle(1100, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(300, 260, 50, 250);
        leftBound.setVisible(false);

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, hud,
                player.getImageView(), lighting, arrowL, leftBound, rightBound);

        if (!GameManager.controlsDismissed){
            this.getChildren().add(controlsWindow);
            GameManager.controlsDismissed = true;
        }
        else{
            if (this.getChildren().contains(controlsWindow)){
                this.getChildren().remove(controlsWindow);
            }

        }

        // ============ DOOR LEFT ============
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
                    doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(440);
                    //Stop the players movement animation
                    player.stopMoving();
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map6Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowL.setVisible(false);
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

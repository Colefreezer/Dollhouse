package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import cherrybombradical.dollhouse.scenes.Map1Scene;
import cherrybombradical.dollhouse.scenes.Map2Scene;
import cherrybombradical.dollhouse.scenes.Map3Scene;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map2Pane extends Pane {

    // Store the current map ID
    public static int mapID = 1;
    // ==== MAP = LEFT HALLWAY
    public static Player player;
    public static HUDPane hud;

    public Map2Pane(){
        System.out.println("Map 2 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        if (!GameManager.backgroundMusicIndoors.isPlaying()){
            GameManager.backgroundMusicOutside.stop();
            GameManager.backgroundMusicIndoors.play();
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
        arrowL.setX(10);
        arrowL.setY(150);
        arrowL.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowL).play();

        //Set Left Arrow HitBox
        Rectangle leftArrowHitBox = new Rectangle(10, 260, 50, 250);
        leftArrowHitBox.setVisible(false);

        //Load the Arrow Image for when near the right door
        ImageView arrowR = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowR.setX(1200);
        arrowR.setY(200);
        arrowR.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowR).play();

        //Set Left Boundary
        Rectangle rightBound = new Rectangle(1400, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(10, 260, 50, 250);
        leftBound.setVisible(false);

        //Set Right Arrow HitBox
        Rectangle rightArrowHitBox = new Rectangle(1250, 260, 50, 250);
        rightArrowHitBox.setVisible(false);



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
                    GameManager.setNewLocation(600);
                    //Stop the players movement animation
                    player.stopMoving();
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map3Scene());

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
                        Game.mainStage.setScene(new Map1Scene());

                    });
                });

            } else {
                // player is not colliding with door
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

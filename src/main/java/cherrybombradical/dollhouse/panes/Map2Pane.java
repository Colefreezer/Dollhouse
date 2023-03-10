package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.Player;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import cherrybombradical.dollhouse.scenes.Map1Scene;
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
    public static Player player;

    public Map2Pane(){
        Animations.fadeIn(Duration.seconds(3), this).play();
        // Create the player object and add its ImageView to the scene
        player = new Player(580, 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Create HUD
        HUDPane hud = new HUDPane();

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

        //Set Right Arrow HitBox
        Rectangle rightArrowHitBox = new Rectangle(1250, 260, 50, 250);
        rightArrowHitBox.setVisible(false);



        // Add all the nodes to the group
        this.getChildren().addAll(map, arrowL, arrowR, leftArrowHitBox, rightArrowHitBox,
                hud, player.getImageView(), lighting);

        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(leftArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowL.setVisible(true);

                this.setOnMouseClicked(event -> {
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(2), this);
                    fadeTransition.play();

                    fadeTransition.setOnFinished(event1 -> {
                        Game.mainStage.setScene(new Map1Scene());
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
                // player is colliding with door hitbox
                arrowR.setVisible(true);
                this.setOnMouseClicked(event -> {
                    Game.mainStage.setScene(new MainMenuScene());
                });

            } else {
                // player is not colliding with door hitbox
                arrowR.setVisible(false);
            }
        });

        // Key events for player movement (not currently working):

        /*
        // set focus on Map1Pane so that it can receive key events
        this.setFocusTraversable(true);
        this.requestFocus();

        // add event handler for arrow keys
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                player.moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                player.moveRight();
            }
        });
        */

    }

}

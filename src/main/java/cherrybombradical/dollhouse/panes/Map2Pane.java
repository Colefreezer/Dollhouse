package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.Player;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import cherrybombradical.dollhouse.scenes.Map1Scene;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map2Pane extends Pane {

    // Store the current map ID
    public static int mapID = 1;

    public Map2Pane(){

        // Create HUD and Maria HUD
        ImageView hud = new ImageView(new Image("sprites/ui/ui_hud1.png"));
        hud.setX(0);
        hud.setY(564);
        ImageView mariahud = new ImageView(new Image("sprites/ui/ui_maria1.png"));
        mariahud.setX(28);
        mariahud.setY(604);

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

        // Create buttons for moving left and right
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");
        leftButton.setLayoutX(600); leftButton.setLayoutY(20);
        leftButton.setScaleX(2); leftButton.setScaleY(2);
        rightButton.setLayoutX(800); rightButton.setLayoutY(20);
        rightButton.setScaleX(2); rightButton.setScaleY(2);

        // Create the player object and add its ImageView to the scene
        Player player = new Player("sprites/Maria_Walk1.png", "sprites/Maria_Walk2.png",580, 303, 50);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Add all the nodes to the group
        this.getChildren().addAll(map, arrowL, arrowR, leftArrowHitBox, rightArrowHitBox,
                hud, mariahud, player.getImageView(), lighting, leftButton, rightButton);

        // Set up event handlers for the left and right buttons
        leftButton.setOnAction(event -> {
            player.moveLeft();
        });
        rightButton.setOnAction(event -> {
            player.moveRight();
        });

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

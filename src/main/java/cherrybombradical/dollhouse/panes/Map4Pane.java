package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.panes.eventpanes.SafePane;
import cherrybombradical.dollhouse.scenes.Map3Scene;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map4Pane extends Pane {

    // Store the current map ID
    public static int mapID = 5;
    // ==== MAP = SHED
    public static Player player;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer stairsSFX = new AudioPlayer("Audio/Sounds/SFX_Stairs.mp3", false);
    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);


    public Map4Pane(){
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        // Create the player object and add its ImageView to the scene
        player = new Player(GameManager.getNewLocation(), 303, 150);
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

        //Load the Arrow Image for when near the safe
        ImageView arrowR = new ImageView(new Image("sprites/UI/interact.png"));
        arrowR.setX(1200);
        arrowR.setY(200);
        arrowR.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowR).play();

        //Set Right Interact Icon
        Rectangle rightArrowHitBox = new Rectangle(1250, 260, 50, 250);
        rightArrowHitBox.setVisible(false);

        SafePane safePane = new SafePane(this);
        safePane.setVisible(false);

        //Set Left Boundary
        Rectangle rightBound = new Rectangle(1400, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(10, 260, 50, 250);
        leftBound.setVisible(false);

        // Add all the nodes to the group
        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox,
                hud, player.getImageView(), lighting, arrowL, arrowR, safePane, leftBound, rightBound);

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
                    GameManager.setNewLocation(75);
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

        //Detect if player (image) is colliding with the Safe
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(rightArrowHitBox.getBoundsInParent())) {
                // player is colliding with safe
                arrowR.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (!GameManager.hasKey2){
                        if (!safePane.isVisible()){
                            safePane.setVisible(true);
                            Animations.UIShow(safePane).play();
                            uIShow.play();
                        }else{
                            Animations.UIShow(safePane).play();
                            uIShow.play();
                        }

                    }
                });

            }
            else {
                // player is not colliding with safe
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

    public void addKeyImage(){
        Image itemKey1 = new Image("sprites/UI/Icons/icn_key1.png");
        ImageView itemKey1view = new ImageView(itemKey1);
        itemKey1view.setX(300);
        itemKey1view.setY(615);
        this.getChildren().add(itemKey1view);
    }



}

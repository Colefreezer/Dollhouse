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

public class Map13Pane extends Pane {

    // Store the current map ID
    public static int mapID = 12;
    // ==== MAP = OUTSIDE FRONT
    public static Player player;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    public boolean inEvent = false;
    public Map13Pane(){
        GameManager.stopTimer();
        System.out.println("Map 13 Loaded");
        if (GameManager.backgroundMusicFirePlace.isPlaying()){
            GameManager.backgroundMusicFirePlace.stop();
            GameManager.backgroundMusicOutside.play();
        }
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        // EVENT STUFF
        Button MusicButton = new Button();
        Button keyButtonSilver = new Button();
        Button backButton = new Button();

        ImageView KeyLock = new ImageView(new Image("sprites/UI/ui_keyhole_Gold.png"));
        ImageView keySilver = new ImageView(new Image("sprites/UI/ui_musicBox3.png"));

        keySilver.setOpacity(0);
        KeyLock.setLayoutX(-600);




        // Create the player object
        player = new Player(GameManager.getNewLocation(), 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Create HUD
        HUDPane hud = new HUDPane();

        //Load the Arrow Image for when near the left door
        ImageView arrowL = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowL.setX(190);
        arrowL.setY(200);
        arrowL.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowL).play();

        //Set Left Arrow HitBox
        Rectangle leftArrowHitBox = new Rectangle(150, 260, 50, 250);
        leftArrowHitBox.setVisible(false);

        //Load the Interact Image for when near the Gate
        ImageView arrowM = new ImageView(new Image("sprites/UI/interact.png"));
        arrowM.setX(1010);
        arrowM.setY(145);
        arrowM.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowM).play();

        //Set middle Arrow HitBox
        Rectangle midArrowHitBox = new Rectangle(1030, 260, 50, 250);
        midArrowHitBox.setVisible(false);

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        //Set Left Boundary
        Rectangle rightBound = new Rectangle(1400, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(10, 260, 50, 250);
        leftBound.setVisible(false);

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, hud,
                player.getImageView(), lighting, arrowL, arrowM, leftBound, rightBound);



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
                    GameManager.doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(540);
                    fadeTransition.setOnFinished(event1 -> {

                        //Load Scene
                        Game.mainStage.setScene(new Map7Scene());
                    });
                });

            } else {
                // player is not colliding with door
                arrowL.setVisible(false);
            }
        });

        // ============ GATE ============
        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(midArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowM.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (inEvent == false ){
                        arrowM.setVisible(false);
                        uIShow.play();
                        KeyLock.setLayoutX(0);


                        // Back Button
                        backButton.setLayoutX(566);
                        backButton.setLayoutY(90);
                        backButton.setOpacity(0);
                        backButton.setScaleX(4);
                        backButton.setScaleY(3);

                        this.getChildren().addAll(KeyLock, backButton);
                        Animations.UIShow(KeyLock).play();
                        System.out.println("KeyHole showing");
                        inEvent = true;
                        GameManager.inventorySelect = true;
                    }

                    keyButtonSilver.setOnAction((e) -> {
                        keySilver.setOpacity(0);

                        HUDPane.AddInventory("SilverKey");
                        Image itemKey2 = new Image("sprites/UI/Icons/icn_key2.png");
                        ImageView itemKey2view = new ImageView(itemKey2);
                        itemKey2view.setX(600);
                        itemKey2view.setY(615);
                        this.getChildren().add(itemKey2view);
                        GameManager.hasKey1 = true;
                    });
                    backButton.setOnAction((e) -> {
                        uIMove.play();
                        Animations.UIMove(KeyLock).play();
                        inEvent = false;
                    });
                });

            } else {
                // player is not colliding with door
                arrowM.setVisible(false);
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

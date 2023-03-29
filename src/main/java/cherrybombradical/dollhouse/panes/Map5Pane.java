package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map5Pane extends Pane {

    // Store the current map ID
    public static int mapID = 3;
    // ==== MAP = Upstairs
    public static Player player;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer stairsSFX = new AudioPlayer("Audio/Sounds/SFX_Stairs.mp3", false);
    private final AudioPlayer doorLockedSFX = new AudioPlayer("Audio/Sounds/SFX_DoorLocked.mp3", false);
    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    public boolean inEvent = false;


    public Map5Pane(){
        Animations.fadeIn(Duration.seconds(0.5), this).play();

        if (!GameManager.backgroundMusicUpstairs.isPlaying()){
            GameManager.backgroundMusicUpstairs.stop();
            GameManager.backgroundMusicIndoors.play();
        }

        // EVENT STUFF
        Button backButton = new Button();
        ImageView KeyLock = new ImageView(new Image("sprites/UI/ui_keyhole_Silver.png"));
        ImageView keySilver = new ImageView(new Image("sprites/UI/ui_musicBox3.png"));
        keySilver.setOpacity(0);
        KeyLock.setLayoutX(-600);

        ImageView KeyIn = new ImageView(new Image("sprites/UI/ui_key_Silver.png"));
        KeyIn.setLayoutY(247);
        KeyIn.setLayoutX(110);



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

        //Load the Arrow Image for when near the right door
        ImageView arrowR = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowR.setX(1200);
        arrowR.setY(200);
        arrowR.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowR).play();

        //Set Right Arrow HitBox
        Rectangle rightArrowHitBox = new Rectangle(1250, 260, 50, 250);
        rightArrowHitBox.setVisible(false);

        //Load the Arrow Image for when near the Stairs
        ImageView arrowS = new ImageView(new Image("sprites/UI/arrow2.png"));
        arrowS.setX(595);
        arrowS.setY(145);
        arrowS.setRotate(180);
        arrowS.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowS).play();

        //Set Stairs Arrow HitBox
        Rectangle stairsArrowHitBox = new Rectangle(730, 260, 50, 250);
        stairsArrowHitBox.setVisible(false);

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

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox, hud,
                player.getImageView(), lighting, arrowL, arrowR, arrowS, rightBound, leftBound);



        // ============ DOOR LEFT ============
        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(leftArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowL.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (inEvent == false ){
                        arrowL.setVisible(false);
                        uIShow.play();
                        KeyLock.setLayoutX(0);


                        // Back Button
                        backButton.setLayoutX(536);
                        backButton.setLayoutY(90);
                        backButton.setOpacity(0);
                        backButton.setScaleX(4);
                        backButton.setScaleY(3);

                        this.getChildren().addAll(KeyLock, backButton, KeyIn);
                        Animations.UIShow(KeyLock).play();
                        System.out.println("KeyHole Silver showing");
                        inEvent = true;
                        GameManager.inventorySelect = true;
                        GameManager.itemNeeded = 2;
                    }
                    backButton.setOnAction((e) -> {
                        TranslateTransition uiMoveAni = new TranslateTransition(Duration.millis(200), KeyLock);
                        uiMoveAni.setFromX(0);
                        uiMoveAni.setToX(-900);
                        uiMoveAni.setInterpolator(Interpolator.EASE_IN);
                        uiMoveAni.play();
                        GameManager.itemNeeded = 0;
                        GameManager.inventorySelect = false;
                        uIMove.play();
                        inEvent = false;
                        uiMoveAni.setOnFinished(event1 -> {
                            this.getChildren().removeAll(KeyLock, backButton);
                        });
                    });
                });

                } else {
                // player is not colliding with door
                arrowL.setVisible(false);

            }
        });



        // ============ DOOR RIGHT ============
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
                    doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(320);
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map6Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowR.setVisible(false);
            }
        });

        // ============ STAIRS ============
        //Detect if player (image) is colliding with the Right HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(stairsArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowS.setVisible(true);
                this.setOnMouseClicked(event -> {
                    //Fade Transition
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.6), this);
                    fadeTransition.play();
                    //Door Sound
                    stairsSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(320);
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map1Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowS.setVisible(false);
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

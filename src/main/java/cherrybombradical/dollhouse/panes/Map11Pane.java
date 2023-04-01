package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map11Pane extends Pane {

    // Store the current map ID
    public static int mapID = 10;
    // ==== MAP = BOILER ROOM
    public static Player player;
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer musicBoxOpenSFX = new AudioPlayer("Audio/Sounds/SFX_MusicBoxOpen.mp3", false);
    private final AudioPlayer Dumbwaiter = new AudioPlayer("Audio/Sounds/SFX_Dumbwaiter.mp3", false);

    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    private final AudioPlayer keySFX = new AudioPlayer("Audio/Sounds/SFX_KeyUnlock.mp3", false);
    public boolean inEvent = false;
    public Map11Pane(){
        System.out.println("Map 11 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        // EVENT STUFF
        Button MusicButton = new Button();
        Button keyUnlock = new Button();
        Button backButton = new Button();

        ImageView KeyLock = new ImageView(new Image("sprites/UI/ui_keyhole_Gold.png"));
        ImageView keySilver = new ImageView(new Image("sprites/UI/ui_musicBox3.png"));

        keyUnlock.setLayoutX(226);
        keyUnlock.setLayoutY(260);
        keyUnlock.setOpacity(0);
        keyUnlock.setScaleX(16);
        keyUnlock.setScaleY(7);


        keySilver.setOpacity(0);
        KeyLock.setLayoutX(-600);

        ImageView KeyIn = new ImageView(new Image("sprites/UI/ui_key_Gold.png"));
        KeyIn.setLayoutX(110);
        KeyIn.setLayoutY(157);
        KeyIn.setOpacity(0);




        // Create the player object
        player = new Player(GameManager.getNewLocation(), 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Create HUD
        hud = new HUDPane();

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
        arrowR.setX(1340);
        arrowR.setY(200);
        arrowR.setVisible(false);
        arrowR.setRotate(-90);
        Animations.hover(Duration.millis(1000), arrowR).play();

        //Set Right Arrow HitBox
        Rectangle rightArrowHitBox = new Rectangle(1340, 260, 50, 250);
        rightArrowHitBox.setVisible(false);

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
        ImageView door = new ImageView(new Image("sprites/Misc/Door.png"));
        door.relocate(1004,213);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        //Set right Boundary
        Rectangle rightBound = new Rectangle(1500, 260, 50, 250);
        rightBound.setVisible(true);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(0, 260, 50, 250);
        leftBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBoundGate = new Rectangle(1000, 260, 50, 250);
        leftBound.setVisible(true);

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox, hud,
                door, player.getImageView(), lighting, arrowL, arrowR, arrowM, rightBound, leftBound, leftBoundGate);



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
                    Dumbwaiter.play();
                    //Location for next scene
                    GameManager.setNewLocation(210);
                    fadeTransition.setOnFinished(event1 -> {

                        //Load Scene
                        Game.mainStage.setScene(new Map9Scene());
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
                    //Location for next scene
                    GameManager.setNewLocation(25);
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map8Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowR.setVisible(false);
            }
        });
        // ============ GATE ============
        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(midArrowHitBox.getBoundsInParent())) {
                if (GameManager.key1Used == true) {

                } else if (GameManager.key1Used == false){
                    // player is colliding with door
                    arrowM.setVisible(true);
                    this.setOnMouseClicked(event -> {
                        arrowM.setVisible(false);
                        if (inEvent == false ){
                            arrowL.setVisible(false);
                            uIShow.play();
                            KeyLock.setLayoutX(0);
                            KeyIn.setRotate(90);

                            // Back Button
                            backButton.setLayoutX(536);
                            backButton.setLayoutY(90);
                            backButton.setOpacity(0);
                            backButton.setScaleX(4);
                            backButton.setScaleY(3);

                            this.getChildren().addAll(KeyLock, backButton, KeyIn, keyUnlock);
                            Animations.UIShow(KeyLock).play();
                            System.out.println("KeyHole Silver showing");
                            inEvent = true;

                            keyUnlock.setOnAction((e) -> {
                                // ======= Insert Key =======
                                if (GameManager.hasKey1 = true) {
                                    keySFX.play();
                                    System.out.println("Key In");
                                    KeyIn.setOpacity(255);
                                    RotateTransition keyRotate = new RotateTransition(Duration.millis(200), KeyIn);
                                    keyRotate.setByAngle(90);
                                    ScaleTransition goIn = new ScaleTransition(Duration.millis(200), KeyIn);
                                    goIn.setFromX(2.0);
                                    goIn.setFromY(2.0);
                                    goIn.setToX(1.0);
                                    goIn.setToY(1.0);
                                    PauseTransition delay1 = new PauseTransition(Duration.millis(400));
                                    SequentialTransition sequenceKey = new SequentialTransition(goIn, delay1, keyRotate);
                                    sequenceKey.play();


                                    sequenceKey.setOnFinished(event1 -> {
                                        hud.removeItems(1);
                                        GameManager.key1Used = true;

                                        TranslateTransition uiMoveAni = new TranslateTransition(Duration.millis(200), KeyLock);
                                        uiMoveAni.setFromX(0);
                                        uiMoveAni.setToX(-900);
                                        uiMoveAni.setInterpolator(Interpolator.EASE_IN);

                                        TranslateTransition keyMove = new TranslateTransition(Duration.millis(200), KeyIn);
                                        keyMove.setFromX(0);
                                        keyMove.setToX(-900);
                                        keyMove.setInterpolator(Interpolator.EASE_IN);


                                        ParallelTransition parGoOff = new ParallelTransition(uiMoveAni, keyMove);
                                        parGoOff.play();
                                        parGoOff.setOnFinished(e2 -> {
                                            this.getChildren().removeAll(door, leftBoundGate);
                                            inEvent = false;
                                            player.canMoveLeft = true;
                                            player.canMoveRight = true;
                                        });
                                        uIMove.play();
                                        inEvent = false;
                                        uiMoveAni.setOnFinished(e1 -> {
                                            this.getChildren().removeAll(KeyLock, backButton);
                                        });
                                    });
                                } else {
                                    System.out.println("no");
                                }

                            });
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
                }



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

        //Left Gate Boundary
        if (GameManager.key1Used == false) {
            player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
                if (newBounds.intersects(leftBoundGate.getBoundsInParent())) {
                    player.canMoveLeft = false;

                } else {
                    player.canMoveLeft = true;
                }
            });
        } else if (GameManager.key1Used == true) {
            this.getChildren().remove(leftBoundGate);
        }



    }

}

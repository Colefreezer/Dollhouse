package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.*;
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
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer stairsSFX = new AudioPlayer("Audio/Sounds/SFX_Stairs.mp3", false);
    private final AudioPlayer doorLockedSFX = new AudioPlayer("Audio/Sounds/SFX_DoorLocked.mp3", false);
    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    private final AudioPlayer keySFX = new AudioPlayer("Audio/Sounds/SFX_KeyUnlock.mp3", false);
    public boolean inEvent = false;


    public Map5Pane(){
        System.out.println("Map 5 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();

        if (!GameManager.backgroundMusicUpstairs.isPlaying()){
            GameManager.backgroundMusicUpstairs.stop();
            GameManager.backgroundMusicIndoors.play();
        }

        // EVENT STUFF
        Button keyUnlock = new Button();
        Button backButton = new Button();

        ImageView KeyLock = new ImageView(new Image("sprites/UI/ui_keyhole_Silver.png"));

        keyUnlock.setLayoutX(226);
        keyUnlock.setLayoutY(260);
        keyUnlock.setOpacity(0);
        keyUnlock.setScaleX(16);
        keyUnlock.setScaleY(7);

        KeyLock.setLayoutX(-600);

        ImageView KeyIn = new ImageView(new Image("sprites/UI/ui_key_Silver.png"));
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
            // Check if the player's bounds intersects with the mid arrow hitbox
            if (newBounds.intersects(leftArrowHitBox.getBoundsInParent())) {
                // If the key has not been used yet
                if (GameManager.key2Used == false) {
                    // Make the mid arrow visible
                    arrowL.setVisible(true);
                    // Set an event listener for when the mouse is clicked
                    this.setOnMouseClicked(event -> {
                        // Make the mid arrow invisible
                        arrowL.setVisible(false);
                        // If the event is not already happening
                        if (inEvent == false ){
                            // Make the left arrow invisible
                            arrowL.setVisible(false);
                            // Play the UI show animation
                            uIShow.play();
                            // Set the initial layout of the key lock
                            KeyLock.setLayoutX(0);
                            KeyIn.setRotate(90);
                            backButton.setLayoutX(536);
                            backButton.setLayoutY(90);
                            backButton.setOpacity(0);
                            backButton.setScaleX(4);
                            backButton.setScaleY(3);
                            // Add the key lock, back button, key in, and key unlock to the scene
                            this.getChildren().addAll(KeyLock, backButton, KeyIn, keyUnlock);
                            // Play the UI show animation for the key lock
                            Animations.UIShow(KeyLock).play();
                            System.out.println("KeyHole Silver showing");
                            // Set the inEvent boolean to true
                            inEvent = true;
                            // Set an event listener for when the key unlock button is pressed
                            keyUnlock.setOnAction((e) -> {
                                // If the player has the key
                                if (GameManager.hasKey2 = true) {
                                    // Play the key SFX
                                    keySFX.play();
                                    System.out.println("Key In");
                                    // Make the key in visible
                                    KeyIn.setOpacity(255);
                                    // Create a rotate transition for the key in
                                    RotateTransition keyRotate = new RotateTransition(Duration.millis(200), KeyIn);
                                    keyRotate.setByAngle(90);
                                    // Create a scale transition for the key in
                                    ScaleTransition goIn = new ScaleTransition(Duration.millis(200), KeyIn);
                                    goIn.setFromX(2.0);
                                    goIn.setFromY(2.0);
                                    goIn.setToX(1.0);
                                    goIn.setToY(1.0);
                                    // Create a pause transition
                                    PauseTransition delay1 = new PauseTransition(Duration.millis(400));
                                    // Create a sequential transition for the key in
                                    SequentialTransition sequenceKey = new SequentialTransition(goIn, delay1, keyRotate);
                                    sequenceKey.play();
                                    // Set an event listener for when the sequential transition is finished
                                    sequenceKey.setOnFinished(event1 -> {
                                        // Remove the key from the hud
                                        hud.removeItems(1);
                                        // Set the key1Used boolean to true
                                        GameManager.key2Used = true;
                                        // Create a translate transition for the key lock
                                        TranslateTransition uiMoveAni = new TranslateTransition(Duration.millis(200), KeyLock);
                                        uiMoveAni.setFromX(0);
                                        uiMoveAni.setToX(-900);
                                        uiMoveAni.setInterpolator(Interpolator.EASE_IN);
                                        // Create a translate transition for the key in
                                        TranslateTransition keyMove = new TranslateTransition(Duration.millis(200), KeyIn);
                                        keyMove.setFromX(0);
                                        keyMove.setToX(-900);
                                        keyMove.setInterpolator(Interpolator.EASE_IN);
                                        // Create a parallel transition for the key lock and key in
                                        ParallelTransition parGoOff = new ParallelTransition(uiMoveAni, keyMove);
                                        parGoOff.play();
                                        // Set an event listener for when the parallel transition is finished
                                        parGoOff.setOnFinished(e2 -> {
                                            FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.6), this);
                                            fadeTransition.play();
                                            //Door Sound
                                            doorSFX.play();
                                            //Location for next scene
                                            GameManager.setNewLocation(320);
                                            fadeTransition.setOnFinished(event2 -> {
                                                //Load Scene
                                                Game.mainStage.setScene(new Map12Scene());
                                            });




                                            // Set the inEvent boolean to false
                                            inEvent = false;
                                            // Set the player's movement booleans to true
                                            player.canMoveLeft = true;
                                            player.canMoveRight = true;
                                        });
                                        // Play the UI move animation
                                        uIMove.play();
                                        // Set the inEvent boolean to false
                                        inEvent = false;
                                        // Set an event listener for when the UI move animation is finished
                                        uiMoveAni.setOnFinished(e1 -> {
                                            // Remove the key lock and back button from the scene
                                            this.getChildren().removeAll(KeyLock, backButton);
                                        });
                                    });

                                } else {
                                    System.out.println("no");
                                }
                            });
                        }
                        // Set an event listener for when the back button is pressed
                        backButton.setOnAction((e) -> {
                            // Create a translate transition for the key lock
                            TranslateTransition uiMoveAni = new TranslateTransition(Duration.millis(200), KeyLock);
                            uiMoveAni.setFromX(0);
                            uiMoveAni.setToX(-900);
                            uiMoveAni.setInterpolator(Interpolator.EASE_IN);
                            uiMoveAni.play();
                            // Set the item needed and inventory select booleans to false
                            GameManager.itemNeeded = 0;
                            GameManager.inventorySelect = false;
                            // Play the UI move animation
                            uIMove.play();
                            // Set the inEvent boolean to false
                            inEvent = false;
                            // Set an event listener for when the UI move animation is finished
                            uiMoveAni.setOnFinished(event1 -> {
                                // Remove the key lock and back button from the scene
                                this.getChildren().removeAll(KeyLock, backButton);
                            });
                        });
                    });
                } else {
                    // Make the mid arrow invisible
                    arrowL.setVisible(false);
                }
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

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
        arrowL.setX(450);
        arrowL.setY(200);
        arrowL.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowL).play();

        //Set Left Arrow HitBox
        Rectangle leftArrowHitBox = new Rectangle(450, 260, 50, 250);
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
        if(GameManager.key1Used){
            door.setVisible(false);
        }else{
            door.setVisible(true);
        }

        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        //Set right Boundary
        Rectangle rightBound = new Rectangle(1500, 260, 50, 250);
        rightBound.setVisible(true);

        int leftBoundPos;
        if (GameManager.key1Used){
            leftBoundPos = 0;
        }else {
            leftBoundPos = 1000;
        }

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(leftBoundPos, 260, 50, 250);
        leftBound.setVisible(false);

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox, hud,
                door, player.getImageView(), lighting, arrowL, arrowR, arrowM, rightBound, leftBound);



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
            // Check if the player's bounds intersects with the mid arrow hitbox
            if (newBounds.intersects(midArrowHitBox.getBoundsInParent())) {
                // If the key has not been used yet
                if (GameManager.key1Used == false) {
                    // Make the mid arrow visible
                    arrowM.setVisible(true);
                    // Set an event listener for when the mouse is clicked
                    this.setOnMouseClicked(event -> {
                        // Make the mid arrow invisible
                        arrowM.setVisible(false);
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
                                if (GameManager.hasKey1) {
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
                                        GameManager.key1Used = true;
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
                                            // Remove the door and left bound gate from the scene
                                            this.getChildren().removeAll(door);
                                            GameManager.hasKey1 = false;
                                            leftBound.setX(0);
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
                    arrowM.setVisible(false);
                }
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

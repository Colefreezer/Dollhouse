package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map12Pane extends Pane {

    // Store the current map ID
    public static int mapID = 11;
    // ==== MAP = BEDROOM
    public static Player player;
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer musicBoxOpenSFX = new AudioPlayer("Audio/Sounds/SFX_MusicBoxOpen.mp3", false);
    private final AudioPlayer Dumbwaiter = new AudioPlayer("Audio/Sounds/SFX_Dumbwaiter.mp3", false);
    private final AudioPlayer dollMoveHands = new AudioPlayer("Audio/Sounds/SFX_DollMove.mp3", false);
    private final AudioPlayer dollLaugh = new AudioPlayer("Audio/Sounds/SFX_DollLaugh.mp3", false);
    private final AudioPlayer suspense = new AudioPlayer("Audio/Sounds/SFX_Suspense.mp3", false);

    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    private final AudioPlayer keyGrab = new AudioPlayer("Audio/Sounds/SFX_GetKey.mp3", false);
    public boolean inEvent = false;
    public Map12Pane(){
        System.out.println("Map 12 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();

        if (GameManager.backgroundMusicIndoors.isPlaying()){
            GameManager.backgroundMusicIndoors.stop();
            GameManager.heartbeatSFX.play();
        }

        // EVENT STUFF
        Button moveHands = new Button();
        Button keyButtonPick = new Button();
        Button backButton = new Button();

        ImageView dollInteract = new ImageView(new Image("sprites/UI/ui_doll1.png"));
        ImageView rubyKey = new ImageView(new Image("sprites/UI/ui_doll_key.png"));

        dollInteract.setLayoutX(-600);


        moveHands.setScaleX(12);
        moveHands.setScaleY(4);
        moveHands.setLayoutX(220);
        moveHands.setLayoutY(400);
        moveHands.setOpacity(0);


        rubyKey.setLayoutX(0);
        rubyKey.setLayoutY(0);
        rubyKey.setOpacity(0);

        keyButtonPick.setScaleX(12);
        keyButtonPick.setScaleY(4);
        keyButtonPick.setLayoutX(220);
        keyButtonPick.setLayoutY(400);
        keyButtonPick.setOpacity(0);












        // Create the player object
        player = new Player(GameManager.getNewLocation(), 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Create HUD
        hud = new HUDPane();

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

        //Load the Interact Image for the doll
        ImageView arrowM = new ImageView(new Image("sprites/UI/interact.png"));
        arrowM.setX(550);
        arrowM.setY(145);
        arrowM.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowM).play();

        //Set doll Arrow HitBox
        Rectangle midArrowHitBox = new Rectangle(550, 260, 50, 250);
        midArrowHitBox.setVisible(false);

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        // Add all the nodes to the group

        this.getChildren().addAll(map, rightArrowHitBox, hud,
                player.getImageView(), lighting, arrowR, arrowM);

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
                        Game.mainStage.setScene(new Map5Scene());
                    });
                });

            } else {
                // player is not colliding with door
                arrowR.setVisible(false);
            }
        });

        // ============ DOLL ============
        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(midArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowM.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (!inEvent){
                        arrowM.setVisible(false);
                        uIShow.play();
                        dollInteract.setLayoutX(0);


                        // Back Button
                        backButton.setLayoutX(566);
                        backButton.setLayoutY(90);
                        backButton.setOpacity(0);
                        backButton.setScaleX(4);
                        backButton.setScaleY(3);

                        this.getChildren().addAll(dollInteract, backButton, moveHands);
                        Animations.UIShow(dollInteract).play();
                        System.out.println("KeyHole showing");
                        inEvent = true;
                        GameManager.inventorySelect = true;
                    }

                    //Move doll hands to reveal Ruby Key
                    moveHands.setOnAction((e) -> {
                        dollMoveHands.play();
                        dollInteract.setImage(new Image("sprites/UI/ui_doll2.png"));
                        this.getChildren().addAll(rubyKey, keyButtonPick);
                        rubyKey.setOpacity(255);
                    });
                    //Take Ruby Key
                    keyButtonPick.setOnAction((e) -> {
                        GameManager.hasKey3 = true;
                        GameManager.heartbeatSFX.stop();
                        keyGrab.play();
                        this.getChildren().removeAll(keyButtonPick, rubyKey, backButton);
                        PauseTransition pause = new PauseTransition(Duration.millis(3000));
                        PauseTransition pause2 = new PauseTransition(Duration.millis(1200));
                        pause.play();

                        pause.setOnFinished(event1 -> {
                            dollLaugh.play();
                            dollInteract.setImage(new Image("sprites/UI/ui_doll3.png"));
                            pause2.play();
                            pause2.setOnFinished(event2 -> {
                                ImageView redFlash = new ImageView(new Image("sprites/misc/RedFlash.png"));
                                this.getChildren().add(redFlash);
                                FadeTransition fadeRed = new FadeTransition(Duration.millis(400), redFlash);
                                fadeRed.setToValue(0);
                                fadeRed.setFromValue(255);
                                fadeRed.play();
                                this.getChildren().remove(dollInteract);
                                suspense.play();
                                GameManager.chasedMusic.play();
                                lighting.setImage(new Image("sprites/shadows/shadow11_2.png"));
                                map.setImage(new Image("sprites/maps/map11_2.png"));
                                GameManager.key3Used = true;

                            });

                        });


                    });
                    backButton.setOnAction((e) -> {

                        uIMove.play();
                        Animations.UIMove(dollInteract).play();
                        this.getChildren().removeAll(dollInteract, backButton);
                        inEvent = false;
                    });
                });
            } else {
                // player is not colliding with door
                arrowM.setVisible(false);
            }
        });

    }

}

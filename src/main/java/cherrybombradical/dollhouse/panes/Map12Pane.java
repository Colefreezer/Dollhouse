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

public class Map12Pane extends Pane {

    // Store the current map ID
    public static int mapID = 11;
    // ==== MAP = BEDROOM
    public static Player player;
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer musicBoxOpenSFX = new AudioPlayer("Audio/Sounds/SFX_MusicBoxOpen.mp3", false);
    private final AudioPlayer Dumbwaiter = new AudioPlayer("Audio/Sounds/SFX_Dumbwaiter.mp3", false);

    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    public boolean inEvent = false;
    public Map12Pane(){
        System.out.println("Map 12 Loaded");
        Animations.fadeIn(Duration.seconds(0.5), this).play();

        if (!GameManager.backgroundMusicIndoors.isPlaying()){
            GameManager.backgroundMusicIndoors.stop();
            GameManager.backgroundMusicUpstairs.play();
        }
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

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox, hud,
                player.getImageView(), lighting, arrowL, arrowR, arrowM);

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

    }

}

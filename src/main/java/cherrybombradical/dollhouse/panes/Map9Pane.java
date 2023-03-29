package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.panes.eventpanes.NotePane;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map9Pane extends Pane {

    // Store the current map ID
    public static int mapID = 8;
    // ==== MAP = BOILER ROOM
    public static Player player;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer musicBoxOpenSFX = new AudioPlayer("Audio/Sounds/SFX_MusicBoxOpen.mp3", false);

    private final AudioPlayer keyGrab = new AudioPlayer("Audio/Sounds/SFX_GetKey.mp3", false);
    private final AudioPlayer uIShow = new AudioPlayer("Audio/Sounds/SFX_UIShow.mp3", false);
    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    private final AudioPlayer chairMove = new AudioPlayer("Audio/Sounds/SFX_ChairMove.mp3", false);
    public boolean inEvent = false;
    public Map9Pane(){

        Animations.fadeIn(Duration.seconds(0.5), this).play();
        // EVENT STUFF
        Button MusicButton = new Button();
        Button keyButtonSilver = new Button();
        Button backButton = new Button();

        ImageView MusicBox = new ImageView(new Image("sprites/UI/ui_musicBox1.png"));
        ImageView keySilver = new ImageView(new Image("sprites/UI/ui_musicBox3.png"));

        keySilver.setOpacity(0);
        MusicBox.setLayoutX(-600);


        ImageView ChairDoor = new ImageView(new Image("sprites/Misc/Chair.png"));
        ChairDoor.setLayoutX(1103);
        ChairDoor.setLayoutY(367);




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

        //Load the Interact Image for when near the Music Box
        ImageView arrowM = new ImageView(new Image("sprites/UI/interact.png"));
        arrowM.setX(875);
        arrowM.setY(145);
        arrowM.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowM).play();

        //Set middle Arrow HitBox
        Rectangle midArrowHitBox = new Rectangle(870, 260, 50, 250);
        midArrowHitBox.setVisible(false);


        //Load the Arrow for Chair
        ImageView arrowChair = new ImageView(new Image("sprites/UI/interact.png"));
        arrowChair.setX(1180);
        arrowChair.setY(200);
        arrowChair.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowChair).play();

        //Set Right Arrow HitBox
        Rectangle ChairArrowHitBox = new Rectangle(1100, 260, 50, 250);
        ChairArrowHitBox.setVisible(false);

        NotePane notePane = new NotePane("LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n" +
                "LOOK UP AT THE MOON\n", 16);
        notePane.setVisible(false);

        ImageView noteInteract = new ImageView(new Image("sprites/UI/Read.png"));
        noteInteract.setX(500);
        noteInteract.setY(200);
        noteInteract.setVisible(false);
        Animations.hover(Duration.millis(1000), noteInteract).play();

        Rectangle noteHitBox = new Rectangle(500, 260, 50, 250);
        noteHitBox.setVisible(true);

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        //Set Right Boundary
        Rectangle rightBound = new Rectangle(1350, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(200, 260, 50, 250);
        leftBound.setVisible(false);

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox, ChairDoor,
                player.getImageView(),lighting, arrowL, noteInteract, notePane, hud, arrowR, arrowM, leftBound, rightBound);



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
                    GameManager.setNewLocation(210);
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

        // ============ MOVE CHAIR ============
        //Detect if player (image) is colliding with the Right HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(ChairArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowChair.setVisible(true);
                this.setOnMouseClicked(event -> {
                    //Fade Transition
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.6), this);
                    fadeTransition.play();
                    //Door Sound
                    chairMove.play();
                    //Location for next scene
                    fadeTransition.setOnFinished(event1 -> {
                        ChairDoor.setOpacity(0);
                        FadeTransition fadebackIn = Animations.fadeIn(Duration.seconds(0.6), this);
                        fadebackIn.play();


                    });
                });

            } else {
                // player is not colliding with door
                arrowChair.setVisible(false);
            }
        });


        // ============ MUSIC BOX ============
        //Detect if player (image) is colliding with the Right HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(midArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowM.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (inEvent == false ){
                        arrowM.setVisible(false);
                        uIShow.play();
                        MusicBox.setLayoutX(0);

                        // Music Box Button
                        MusicButton.setLayoutX(236);
                        MusicButton.setLayoutY(356);
                        MusicButton.setOpacity(0);
                        MusicButton.setScaleX(4);
                        MusicButton.setScaleY(3);


                        // Back Button
                        backButton.setLayoutX(566);
                        backButton.setLayoutY(90);
                        backButton.setOpacity(0);
                        backButton.setScaleX(4);
                        backButton.setScaleY(3);

                        this.getChildren().addAll(MusicBox, MusicButton, backButton);
                        Animations.UIShow(MusicBox).play();
                        System.out.println("Music Box showing");
                        inEvent = true;
                    }
                    MusicButton.setOnAction((e) -> {
                        musicBoxOpenSFX.play();
                        GameManager.musicBoxBackgroundMusic.play();
                        MusicBox.setImage(new Image("sprites/UI/ui_musicBox2.png"));
                        keySilver.setOpacity(255);
                        this.getChildren().addAll(keySilver, keyButtonSilver);
                        keyButtonSilver.setLayoutX(226);
                        keyButtonSilver.setLayoutY(200);
                        keyButtonSilver.setOpacity(0);
                        keyButtonSilver.setScaleX(16);
                        keyButtonSilver.setScaleY(4);
                    });
                    keyButtonSilver.setOnAction((e) -> {
                        keySilver.setOpacity(0);
                        keyGrab.play();
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
                        Animations.UIMove(MusicBox).play();
                        inEvent = false;
                    });
                });


            } else {
                // player is not colliding with door
                arrowM.setVisible(false);
            }
        });

        // ============ NOTE ============
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(noteHitBox.getBoundsInParent())) {
                // player is colliding with note
                noteInteract.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (!notePane.isVisible()){
                        GameManager.noteSFX.play();
                        notePane.setVisible(true);
                        Animations.noteMoveIn(notePane).play();
                    }else {
                        GameManager.noteCloseSFX.play();
                        notePane.setVisible(false);

                    }

                });

            } else {
                // player is not colliding with note
                notePane.setVisible(false);
                noteInteract.setVisible(false);
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

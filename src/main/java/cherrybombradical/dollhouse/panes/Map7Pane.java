package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.panes.eventpanes.NotePane;
import cherrybombradical.dollhouse.scenes.*;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map7Pane extends Pane {

    // Store the current map ID
    public static int mapID = 7;
    // ==== MAP = LIVING ROOM

    public static Player player;
    public static HUDPane hud;
    private final AudioPlayer doorSFX = new AudioPlayer("Audio/Sounds/SFX_Door1.mp3", false);
    private final AudioPlayer stairsSFX = new AudioPlayer("Audio/Sounds/SFX_Stairs.mp3", false);
    private final AudioPlayer doorLockedSFX = new AudioPlayer("Audio/Sounds/SFX_DoorLocked.mp3", false);

    public Map7Pane(){
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        if (GameManager.key3Used) {

        } else {
            if (GameManager.backgroundMusicIndoors.isPlaying()){
                GameManager.backgroundMusicIndoors.stop();
                GameManager.backgroundMusicFirePlace.play();
            }
            else if (GameManager.backgroundMusicBasement.isPlaying()){
                GameManager.backgroundMusicBasement.stop();
                GameManager.backgroundMusicFirePlace.play();
            }
            else if (GameManager.backgroundMusicOutside.isPlaying()) {
                GameManager.backgroundMusicOutside.stop();
                GameManager.backgroundMusicFirePlace.play();
            }

        }



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

        //Load the Arrow Image for when near the middle door
        ImageView arrowM = new ImageView(new Image("sprites/UI/arrow.png"));
        arrowM.setX(955);
        arrowM.setY(145);
        arrowM.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowM).play();

        //Set middle Arrow HitBox
        Rectangle midArrowHitBox = new Rectangle(940, 260, 50, 250);
        midArrowHitBox.setVisible(false);


        //Set Notepad HitBox
        NotePane notePane = new NotePane("0 eyes see what 4 can't,\n" +
                "5 fingers reach where\n" +
                "1 won't. Beware the doll's\n" +
                "whispers in the night,\n" +
                "for its secrets hold the\n" +
                "key to your fright.", 27);
        notePane.setVisible(false);
        ImageView noteInteract = new ImageView(new Image("sprites/UI/Read.png"));
        noteInteract.setX(700);
        noteInteract.setY(200);
        noteInteract.setVisible(false);
        Animations.hover(Duration.millis(1000), noteInteract).play();

        Rectangle noteHitBox = new Rectangle(700, 260, 50, 250);
        noteHitBox.setVisible(true);

        //Load door Locked Text
        ImageView lockedDoorText = new ImageView(new Image("sprites/UI/Locked.png"));
        lockedDoorText.setX(64);
        lockedDoorText.setY(458);
        lockedDoorText.setVisible(false);


        // Load the map image and the shadow overlay for the current map ID
        if (GameManager.key3Used) {
            ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + "_2.png"));
            map.setX(0);
            map.setY(0);
            ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + "_2.png"));
            lighting.setX(0);
            lighting.setY(0);



            // Add all the nodes to the group
            this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox,
                    player.getImageView(), lighting, lockedDoorText, arrowL, arrowR, arrowM, noteInteract, notePane,  hud);



        } else {
            ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
            map.setX(0);
            map.setY(0);
            ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
            lighting.setX(0);
            lighting.setY(0);
            Animations.fire(Duration.millis(1000), Duration.millis(100), lighting).play();

            // Load the Fire Sprite
            ImageView fire = new ImageView(new Image("sprites/Misc/sprite_fire.gif"));
            fire.setX(586);
            fire.setY(390);


            //Layers
            ImageView layer1 = new ImageView(new Image("sprites/Misc/Map12Layer1.png"));
            layer1.setX(0);
            layer1.setY(0);



            // Add all the nodes to the group
            this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox, fire,
                    player.getImageView(), lighting, layer1, arrowL, arrowR, arrowM, noteInteract, notePane,  hud);

        }
        //Set Left Boundary
        Rectangle rightBound = new Rectangle(1300, 260, 50, 250);
        rightBound.setVisible(false);

        //Set Left Boundary
        Rectangle leftBound = new Rectangle(50, 260, 50, 250);
        leftBound.setVisible(false);






        // ============ DOOR LEFT ============
        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(leftArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowL.setVisible(true);
                this.setOnMouseClicked(event -> {
                    //Fade Transition
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.3), this);
                    fadeTransition.play();
                    //Door Sound
                    doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(580);
                    //Stop the players movement animation
                    player.stopMoving();
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map1Scene());

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
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.3), this);
                    fadeTransition.play();
                    //Door Sound
                    doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(45);
                    //Stop the players movement animation
                    player.stopMoving();
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map13Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowR.setVisible(false);
            }
        });

        // ============ MIDDLE ============
        //Detect if player (image) is colliding with the Right HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(midArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowM.setVisible(true);
                this.setOnMouseClicked(event -> {
                    if (!GameManager.key3Used){
                        //Fade Transition
                        FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.3), this);
                        fadeTransition.play();
                        //Door Sound
                        stairsSFX.play();
                        //Location for next scene
                        GameManager.setNewLocation(540);
                        //Stop the players movement animation
                        player.stopMoving();
                        fadeTransition.setOnFinished(event1 -> {
                            //Load Scene
                            Game.mainStage.setScene(new Map8Scene());

                        });
                    }else{
                        doorLockedSFX.play();
                        lockedDoorText.setVisible(true);
                    }

                });

            } else {
                // player is not colliding with door
                arrowM.setVisible(false);
                lockedDoorText.setVisible(false);
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

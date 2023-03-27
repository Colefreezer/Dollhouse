package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.*;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import cherrybombradical.dollhouse.scenes.Map2Scene;
import cherrybombradical.dollhouse.scenes.Map5Scene;
import cherrybombradical.dollhouse.scenes.Map7Scene;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Map1Pane extends Pane {

    // Store the current map ID
    public static int mapID = 0;
    // ==== MAP = MAIN ROOM

    public static Player player;

    public Map1Pane(){
        GameManager.startTimer();
        Animations.fadeIn(Duration.seconds(0.5), this).play();
        if (!GameManager.backgroundMusicFirePlace.isPlaying()){
            GameManager.backgroundMusicFirePlace.stop();
            GameManager.backgroundMusicIndoors.play();
        }

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
        arrowS.setVisible(false);
        Animations.hover(Duration.millis(1000), arrowS).play();

        //Set Stairs Arrow HitBox
        Rectangle stairsArrowHitBox = new Rectangle(730, 260, 50, 250);
        stairsArrowHitBox.setVisible(false);

        NotePane notePane = new NotePane("Hey, whats the matter with ya? You\ngot me the wrong clock!\n" +
                "\n" +
                "This is a Grandfather clock, and I\nspecifically asked for a Grandmother clock.\n" +
                "\n" +
                "Don't you know the difference? I mean,\nseriously, how hard is it to get that right?\n" +
                "\n" +
                "I thought you were supposed to be a\nprofessional dollmaker. Guess I was wrong.\n" +
                "\n" +
                "Look, I don't want to cause a scene, but\nI'm not going to let this go.\n" +
                "\n" +
                "I demand that you fix this mistake and get\nme the right clock ASAP.\n" +
                "\n" +
                "Otherwise, you're going to have one angry\nBoston doll on your hands.\n" +
                "\n" +
                "And trust me, you don't want to mess with a\nBoston doll when they're angry.\n" +
                "\n" +
                "So get on it, and make it right!", 16);
        notePane.setVisible(false);

        ImageView noteInteract = new ImageView(new Image("sprites/UI/Read.png"));
        noteInteract.setX(975);
        noteInteract.setY(200);
        noteInteract.setVisible(false);
        Animations.hover(Duration.millis(1000), noteInteract).play();

        Rectangle noteHitBox = new Rectangle(950, 260, 50, 250);
        noteHitBox.setVisible(false);

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox,
                player.getImageView(), lighting, arrowL, arrowR, arrowS, notePane, hud,noteHitBox, noteInteract);

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
                    GameManager.doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(580);
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map2Scene());

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
                    GameManager.doorSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(75);
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map7Scene());

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
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(0.3), this);
                    fadeTransition.play();
                    //Door Sound
                    GameManager.stairsSFX.play();
                    //Location for next scene
                    GameManager.setNewLocation(320);
                    fadeTransition.setOnFinished(event1 -> {
                        //Load Scene
                        Game.mainStage.setScene(new Map5Scene());

                    });
                });

            } else {
                // player is not colliding with door
                arrowS.setVisible(false);
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
    }

}

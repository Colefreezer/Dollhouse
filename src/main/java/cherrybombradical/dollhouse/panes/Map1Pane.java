package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.Player;
import cherrybombradical.dollhouse.scenes.MainMenuScene;
import cherrybombradical.dollhouse.scenes.Map1Scene;
import cherrybombradical.dollhouse.scenes.Map2Scene;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Map1Pane extends Pane {

    // Store the current map ID
    public static int mapID = 0;
    public static boolean mapToggle = false;

    public Map1Pane(){

        // Create HUD and Maria HUD
        ImageView hud = new ImageView(new Image("sprites/ui/ui_hud1.png"));
        hud.setX(0);
        hud.setY(564);
        ImageView mariahud = new ImageView(new Image("sprites/ui/ui_maria1.png"));
        mariahud.setX(28);
        mariahud.setY(604);

        ImageView mariahud2 = new ImageView(new Image("sprites/ui/ui_maria2.png"));


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

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        //Map images
        ImageView mapLayer1 = new ImageView(new Image("sprites/ui/ui_mapLayer1.png"));
        mapLayer1.setX(0);
        mapLayer1.setY(0);

        ImageView mapLayer2 = new ImageView(new Image("sprites/ui/ui_mapLayer2.png"));
        mapLayer2.setX(0);
        mapLayer2.setY(0);

        Text mapNameText = new Text("whatever");



        // Create buttons for moving left and right
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");
        Button mapButton = new Button("Map");
        leftButton.setLayoutX(600); leftButton.setLayoutY(20);
        leftButton.setScaleX(2); leftButton.setScaleY(2);
        rightButton.setLayoutX(800); rightButton.setLayoutY(20);
        rightButton.setScaleX(2); rightButton.setScaleY(2);
        mapButton.setLayoutX(1000); mapButton.setLayoutY(20);
        mapButton.setScaleX(2); mapButton.setScaleY(2);

        // Create the player object and add its ImageView to the scene
        Player player = new Player("sprites/Maria_Walk1.png", "sprites/Maria_Walk2.png",500, 303, 150);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);
        player.getImageView().setLayoutX(player.getXPosition());

        // Add all the nodes to the group
        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox,
                hud, mariahud, player.getImageView(), lighting, arrowL, arrowR,
                leftButton, rightButton, mapButton);

        // Set up event handlers for the left and right buttons
//        leftButton.setOnAction(event -> {
//            player.moveLeft();
//        });
//        rightButton.setOnAction(event -> {
//            player.moveRight();
//        });
        leftButton.setOnMousePressed(event -> {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(50), e -> player.moveLeft())
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            leftButton.setOnMouseReleased(event1 -> {
                player.stopMoving();
                timeline.stop();
            });
        });

        leftButton.setOnMousePressed(event -> {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(50), e -> player.moveLeft())
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            leftButton.setOnMouseReleased(event1 -> {
                player.stopMoving();
                timeline.stop();
            });
        });

        rightButton.setOnMousePressed(event -> {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(50), e -> player.moveRight())
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            rightButton.setOnMouseReleased(event1 -> {
                player.stopMoving();
                timeline.stop();
            });
        });


        //=============
        mapButton.setOnAction(event -> {
            String[] mapName = {"Main Room", "West Hallway", "Backyard", "Upstairs, Basement Main"};
                if (mapToggle == false){
                this.getChildren().addAll(mapLayer2, mapLayer1);

                //Maria portrait change
                    mariahud2.setX(28);
                    mariahud2.setY(604);
                    this.getChildren().addAll(mariahud2);

                //Map animation
                TranslateTransition translateTransition2 =
                        new TranslateTransition(Duration.millis(200), mapLayer1);
                translateTransition2.setFromY(-640);
                translateTransition2.setToY(0);


                TranslateTransition translateTransition =
                        new TranslateTransition(Duration.millis(200), mapLayer2);
                translateTransition.setFromX(-640);
                translateTransition.setToX(0);

                ScaleTransition scale = new ScaleTransition(Duration.millis(200), mapLayer2);
                scale.setFromX(0);
                scale.setToX(1);

                ScaleTransition scale2 = new ScaleTransition(Duration.millis(0), mapLayer2);
                scale2.setToX(0);

                ParallelTransition parallelTransition2 = new ParallelTransition();
                parallelTransition2.getChildren().addAll(translateTransition, scale);

                ParallelTransition parallelTransition1 = new ParallelTransition();
                parallelTransition1.getChildren().addAll(translateTransition2, scale2);


                SequentialTransition sequentialTransition = new SequentialTransition();
                sequentialTransition.getChildren().addAll(
                        parallelTransition1, parallelTransition2
                );
                mapToggle = true;
                sequentialTransition.play();
                } else {
                    this.getChildren().removeAll(mapLayer1, mapLayer2, mariahud2);
                    mapToggle = false;
                }
        });

        //Detect if player (image) is colliding with the left HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(leftArrowHitBox.getBoundsInParent())) {
                // player is colliding with door
                arrowL.setVisible(true);

                this.setOnMouseClicked(event -> {
                    FadeTransition fadeTransition = Animations.fadeOut(Duration.seconds(2), this);
                    fadeTransition.play();

                    fadeTransition.setOnFinished(event1 -> {
                        Game.mainStage.setScene(new Map2Scene());
                    });
                });

            } else {
                // player is not colliding with door
                arrowL.setVisible(false);
            }
        });

        //Detect if player (image) is colliding with the Right HitBox
        player.getImageView().boundsInParentProperty().addListener((obs, oldBounds, newBounds) -> {
            if (newBounds.intersects(rightArrowHitBox.getBoundsInParent())) {
                // player is colliding with door hitbox
                arrowR.setVisible(true);
                this.setOnMouseClicked(event -> {
                    Game.mainStage.setScene(new MainMenuScene());
                });

            } else {
                // player is not colliding with door hitbox
                arrowR.setVisible(false);
            }
        });

        // Key events for player movement (not currently working):

        /*
        // set focus on Map1Pane so that it can receive key events
        this.setFocusTraversable(true);
        this.requestFocus();

        // add event handler for arrow keys
        setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                player.moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                player.moveRight();
            }
        });
        */

    }

}

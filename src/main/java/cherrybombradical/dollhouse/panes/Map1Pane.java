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

    public static Player player;

    public Map1Pane(){
        Animations.fadeIn(Duration.seconds(3), this).play();

        // Create the player object
        player = new Player(500, 303, 150);
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



        // Add all the nodes to the group

        this.getChildren().addAll(map, leftArrowHitBox, rightArrowHitBox,
                hud, player.getImageView(), lighting, arrowL, arrowR,
                 mapButton);



        leftButton.setOnMousePressed(event -> {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(1), e -> player.moveLeft())
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
                    new KeyFrame(Duration.millis(1), e -> player.moveRight())
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            rightButton.setOnMouseReleased(event1 -> {
                player.stopMoving();
                timeline.stop();
            });
        });

        //Toggle Map on
        mapButton.setOnAction(event -> {
            player.toggleMap(this, mapLayer2, mapLayer1);
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

    }

}

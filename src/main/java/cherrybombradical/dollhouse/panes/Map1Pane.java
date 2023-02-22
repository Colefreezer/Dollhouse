package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Player;
import cherrybombradical.dollhouse.scenes.Map1Scene;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Map1Pane extends Group{

    // Store the current map ID
    public static int mapID = 0;

    public Map1Pane(){

        // Create HUD and Maria HUD
        ImageView hud = new ImageView(new Image("sprites/ui/ui_hud1.png"));
        hud.setX(0);
        hud.setY(564);
        ImageView mariahud = new ImageView(new Image("sprites/ui/ui_maria1.png"));
        mariahud.setX(28);
        mariahud.setY(604);

        // Load the map image and the shadow overlay for the current map ID
        ImageView map = new ImageView(new Image("sprites/maps/map" + mapID + ".png"));
        map.setX(0);
        map.setY(0);
        ImageView lighting = new ImageView(new Image("sprites/shadows/shadow" + mapID + ".png"));
        lighting.setX(0);
        lighting.setY(0);

        // Create buttons for moving left and right
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");
        leftButton.setLayoutX(600); leftButton.setLayoutY(20);
        leftButton.setScaleX(2); leftButton.setScaleY(2);
        rightButton.setLayoutX(800); rightButton.setLayoutY(20);
        rightButton.setScaleX(2); rightButton.setScaleY(2);

        // Create the player object and add its ImageView to the scene
        Player player = new Player("sprites/Maria_Walk1.png", "sprites/Maria_Walk2.png",167, 303, 10);
        player.getImageView().setFitHeight(250);
        player.getImageView().setPreserveRatio(true);

        // Add all the nodes to the group
        this.getChildren().addAll(map, hud, mariahud, player.getImageView(), lighting, leftButton, rightButton);

        // Set up event handlers for the left and right buttons
        leftButton.setOnAction(event -> {
            player.moveLeft();
        });
        rightButton.setOnAction(event -> {
            player.moveRight();
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

package cherrybombradical.dollhouse;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DH_Map2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {




        //  ==========  Background Image  ==========
        Image uiHud = new Image("sprites/UI/ui_hud1.png");
        ImageView hud = new ImageView(uiHud);
        hud.setX(0);
        hud.setY(564);







        HBox controlsBox = new HBox();
        Button upButton = new Button("Up");
        Button downButton = new Button("Down");
        Button rightButton = new Button("Right");
        Button leftButton = new Button("Left");
        ImageView player = new ImageView(new Image("sprites/Maria_Walk1.png"));
        player.setScaleX(5);
        player.setScaleY(5);



        controlsBox.setAlignment(Pos.CENTER);

        controlsBox.getChildren().addAll(upButton, downButton, rightButton, leftButton);

        downButton.setOnAction(event -> {
            player.setTranslateY(player.getTranslateY() + 10);
        });
        upButton.setOnAction(event -> {
            player.setTranslateY(player.getTranslateY() - 10);
        });
        rightButton.setOnAction(event -> {
            player.setTranslateX(player.getTranslateX() + 10);
        });
        leftButton.setOnAction(event -> {
            player.setTranslateX(player.getTranslateX() - 10);
        });

        BorderPane bp = new BorderPane();


    }
}

package cherrybombradical.dollhouse;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }





    @Override
    public void start(Stage primaryStage) {

        //  ==========  Start game logo  ==========
        Button startGame = new Button();
        ImageView startGameImg = new ImageView(new Image("Images/ui_startImage.png"));
        startGameImg.setFitHeight(80);
        startGameImg.setPreserveRatio(true);
        startGame.setGraphic(startGameImg);
        startGame.setContentDisplay(ContentDisplay.TOP);
        startGame.setStyle("-fx-background-color: #000000");
        startGame.setLayoutX(10);
        startGame.setLayoutY(640);

        //  ==========  Settings Button ==========
        Button settingsButton = new Button();
        ImageView settingsButtonImg = new ImageView(new Image("Images/ui_settings.png"));
        settingsButtonImg.setFitHeight(70);
        settingsButtonImg.setPreserveRatio(true);
        settingsButton.setGraphic(settingsButtonImg);
        settingsButton.setContentDisplay(ContentDisplay.TOP);
        settingsButton.setStyle("-fx-background-color: #000000");
        settingsButton.setLayoutX(400);
        settingsButton.setLayoutY(640);

        //  ==========  Quit Button ==========
        Button quitButton = new Button();
        ImageView quitButtonImg = new ImageView(new Image("Images/ui_quit.png"));
        quitButtonImg.setFitHeight(70);
        quitButtonImg.setPreserveRatio(true);
        quitButton.setGraphic(quitButtonImg);
        quitButton.setContentDisplay(ContentDisplay.TOP);
        quitButton.setStyle("-fx-background-color: #000000");
        quitButton.setLayoutX(730);
        quitButton.setLayoutY(640);



        //  ==========  Background Image  ==========
        Image background = new Image("images/MMBG1.png");
        ImageView bg = new ImageView(background);

        //  ==========  Logo Image  ==========
        Image gameLogo = new Image("images/Logo.png");
        ImageView logo1 = new ImageView(gameLogo);
        logo1.setX(46);
        logo1.setY(200);


        Group root = new Group();
        root.getChildren().addAll(bg, logo1, startGame, settingsButton, quitButton);


        //Scene play
        primaryStage.setResizable(false);
        primaryStage.setTitle("Dollhouse");
        Scene scene = new Scene(root, 1449, 814);
        primaryStage.setScene(scene);
        primaryStage.show();
    }





}

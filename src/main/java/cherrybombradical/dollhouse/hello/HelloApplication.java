package cherrybombradical.dollhouse.hello;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override


    public void start(Stage stage) throws IOException {
        ImageView mainMenu = new ImageView();
        ImageView groupName = new ImageView();
        mainMenu.setFitWidth(700);
        mainMenu.setPreserveRatio(true);




        //  ==========  Start game logo  ==========
        Button startGame = new Button();
        ImageView startGameImg = new ImageView(new Image("Images/ui_startImage.png"));
        startGameImg.setFitHeight(100);
        startGameImg.setPreserveRatio(true);
        startGame.setGraphic(startGameImg);
        startGame.setContentDisplay(ContentDisplay.TOP);
        startGame.setStyle("-fx-background-color: #000000");

        //  ==========  Settings Button ==========
        Button settingsButton = new Button();
        ImageView settingsButtonImg = new ImageView(new Image("Images/ui_settings.png"));
        settingsButtonImg.setFitHeight(70);
        settingsButtonImg.setPreserveRatio(true);
        settingsButton.setGraphic(settingsButtonImg);
        settingsButton.setContentDisplay(ContentDisplay.TOP);
        settingsButton.setStyle("-fx-background-color: #000000");

        //  ==========  Quit Button ==========
        Button quitButton = new Button();
        ImageView quitButtonImg = new ImageView(new Image("Images/ui_quit.png"));
        quitButtonImg.setFitHeight(70);
        quitButtonImg.setPreserveRatio(true);
        quitButton.setGraphic(quitButtonImg);
        quitButton.setContentDisplay(ContentDisplay.TOP);
        quitButton.setStyle("-fx-background-color: #000000");




        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #000000");
        VBox vbox = new VBox();


        Image logo = new Image("Images/Logo.png");
        Image groupLogo = new Image("Images/GroupLogo.png");
        mainMenu.setImage(logo);

        root.setCenter(vbox);
        vbox.setAlignment(Pos.CENTER);





        vbox.getChildren().addAll(mainMenu, startGame, settingsButton, quitButton, groupName);
        Scene scene = new Scene(root, 1449, 814, Color.BLACK);
        stage.setResizable(false);
        stage.setTitle("Dollhouse");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
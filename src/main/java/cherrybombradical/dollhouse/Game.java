package cherrybombradical.dollhouse;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

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

        // Fade screen to black upon clicking "Start Game"
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // create a rectangle that covers the screen
                Rectangle rectangle = new Rectangle(0,0, primaryStage.getWidth(), primaryStage.getHeight());
                rectangle.setFill(Color.BLACK);

                // add the rectangle to the stack pane
                Group root = (Group) primaryStage.getScene().getRoot();
                root.getChildren().add(rectangle);

                // create a fade transition that lasts for 2 seconds
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), rectangle);
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);

                //Play the fade animation
                fadeTransition.play();

                fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Stage stage = (Stage) startGame.getScene().getWindow();
                        stage.setScene(gameScene());
                    }
                });

            }
        });



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

    public static Scene gameScene(){

        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, 1449, 814);




        ImageView player = new ImageView(new Image("sprites/Maria_Walk1.png"));
        player.setFitHeight(250);
        player.setPreserveRatio(true);







        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                player.setTranslateX(player.getTranslateX() - 10);
            }

            if (event.getCode() == KeyCode.RIGHT) {
                player.setTranslateX(player.getTranslateX() + 10);
            }


        });



        bp.setCenter(player);


        return scene;
    }





}

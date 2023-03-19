package cherrybombradical.dollhouse;


import cherrybombradical.dollhouse.scenes.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application {

    public static Stage mainStage; // mainStage variable to hold the primary stage

    // The entry point of the application, which launches the game
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        // set the mainStage variable to the primaryStage
        mainStage = primaryStage;

        // Set window properties
        mainStage.setResizable(false);
        mainStage.setTitle("Dollhouse");
        mainStage.getIcons().add(new Image("sprites/Maria_Walk1.png"));
        // Set the main menu scene as the starting scene
        mainStage.setScene(new Map9Scene());

        // Display the game window
        mainStage.show();
    }
}

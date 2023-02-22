package cherrybombradical.dollhouse;

import cherrybombradical.dollhouse.scenes.MainMenuScene;
import javafx.application.Application;
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

        // Set the main menu scene as the starting scene
        mainStage.setScene(new MainMenuScene());

        // Display the game window
        mainStage.show();
    }
}

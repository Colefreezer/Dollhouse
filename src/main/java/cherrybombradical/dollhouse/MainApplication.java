package cherrybombradical.dollhouse;


import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // show the splash screen before launching the main application
        new cherrybombradical.dollhouse.SplashScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


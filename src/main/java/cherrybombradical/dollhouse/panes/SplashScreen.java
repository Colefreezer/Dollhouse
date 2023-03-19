package cherrybombradical.dollhouse;
import cherrybombradical.dollhouse.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SplashScreen extends Stage {

    public SplashScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("splash.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            initStyle(StageStyle.UNDECORATED);
            show();

            // simulate loading of the application
            new Thread(() -> {
                try {
                    Thread.sleep(3000); // wait for 3 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // close the splash screen and launch the application
                Platform.runLater(() -> {
                    close();
                    try {
                        new Game().start(new Stage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

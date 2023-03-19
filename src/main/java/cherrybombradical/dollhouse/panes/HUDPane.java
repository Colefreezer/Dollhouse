package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.GameManager;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HUDPane extends Pane {
    private Image HUD = new Image("sprites/ui/ui_hud1.png");
    private Image mariaBase = new Image("sprites/ui/ui_maria1.png");
    private Image mariaMap = new Image("sprites/ui/ui_maria2.png");
    public HUDPane(){
        ImageView hud = new ImageView(HUD);
        hud.setX(0);
        hud.setY(564);
        ImageView mariaPOV = new ImageView(mariaBase);
        mariaPOV.setX(28);
        mariaPOV.setY(604);
        this.getChildren().addAll(hud, mariaPOV);

    }
    public static void AddInventory(String itemName){
        switch (itemName) {
            case "GoldKey":
                System.out.println("You found the Gold Key");
                break;
            case "SilverKey":
                System.out.println("You found the Silver Key");
                break;
            default:
                System.out.println("Item not found");
                break;
        }
    }



}

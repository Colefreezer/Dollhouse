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
<<<<<<< HEAD

        Image itemKey1 = new Image("sprites/UI/Icons/icn_key2.png");
        ImageView itemKey1view = new ImageView(itemKey1);
        itemKey1view.setVisible(false);

        Image itemKey2 = new Image("sprites/UI/Icons/icn_key1.png");
        ImageView itemKey2view = new ImageView(itemKey2);
        itemKey2view.setVisible(false);
        
        itemKey1view.setX(600);
        itemKey1view.setY(615);

        itemKey2view.setX(300);
        itemKey2view.setY(615);
        this.getChildren().addAll(hud, mariaPOV, itemKey1view, itemKey2view);

        if (GameManager.hasKey1){
            itemKey1view.setVisible(true);
        }

        if (GameManager.hasKey2){
            itemKey2view.setVisible(true);
        }


=======
        this.getChildren().addAll(hud, mariaPOV);
>>>>>>> parent of 61926c0 (Cutscene, inventory system has a start)

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

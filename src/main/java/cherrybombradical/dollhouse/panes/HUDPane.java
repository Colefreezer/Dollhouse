package cherrybombradical.dollhouse.panes;
import cherrybombradical.dollhouse.GameManager;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
/**
 * This class is responsible for creating the HUD pane.
 */
public class HUDPane extends Pane {
    // Images used in the HUD
    private Image HUD = new Image("sprites/ui/ui_hud1.png");
    private Image mariaBase = new Image("sprites/ui/ui_maria1.png");
    private Image mariaMap = new Image("sprites/ui/ui_maria2.png");
    // ImageViews for the images
    private ImageView hud = new ImageView(HUD);
    private ImageView mariaPOV = new ImageView(mariaBase);
    public HUDPane(){
        // Set the positions of the images
        hud.setX(0);
        hud.setY(564);
        mariaPOV.setX(28);
        mariaPOV.setY(604);
        // Create the images and image views for the keys
        Image itemKey1 = new Image("sprites/UI/Icons/icn_key1.png");
        ImageView itemKey1view = new ImageView(itemKey1);
        itemKey1view.setVisible(false);
        Image itemKey2 = new Image("sprites/UI/Icons/icn_key2.png");
        ImageView itemKey2view = new ImageView(itemKey2);
        itemKey2view.setVisible(false);
        // Set the positions of the keys
        itemKey1view.setX(300);
        itemKey1view.setY(615);
        itemKey2view.setX(500);
        itemKey2view.setY(615);
        // Add all the images to the pane
        this.getChildren().addAll(hud, mariaPOV, itemKey1view, itemKey2view);
        // Check if the player has the keys and set them to visible if they do
        if (GameManager.hasKey1){
            itemKey2view.setVisible(true);
        }
        if (GameManager.hasKey2){
            itemKey1view.setVisible(true);
        }
    }
    /**
     * This method adds an item to the inventory.
     * @param itemName The name of the item to be added.
     */
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
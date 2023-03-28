package cherrybombradical.dollhouse.panes;
import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.AudioPlayer;
import cherrybombradical.dollhouse.Game;
import cherrybombradical.dollhouse.GameManager;
import cherrybombradical.dollhouse.scenes.IntroCutscene;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
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
    private final AudioPlayer UIDeny = new AudioPlayer("Audio/Sounds/SFX_Denied.mp3", false);
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
        // ====================== KEY 1 ======================
        Image itemKey1 = new Image("sprites/UI/Icons/icn_key1.png");
        Button itemKey1Button = new Button();
        ImageView itemKey1view = new ImageView(itemKey1);
        itemKey1view.setVisible(false);
        itemKey1view.setX(300);
        itemKey1view.setY(615);
        // key button layout
        itemKey1Button.setLayoutX(350);
        itemKey1Button.setLayoutY(665);
        itemKey1Button.setScaleX(13);
        itemKey1Button.setScaleY(7);
        itemKey1Button.setOpacity(0);

        // ====================== KEY 2 ======================
        Image itemKey2 = new Image("sprites/UI/Icons/icn_key2.png");
        Button itemKey2Button = new Button();
        ImageView itemKey2view = new ImageView(itemKey2);
        itemKey2view.setVisible(false);
        itemKey2view.setX(500);
        itemKey2view.setY(615);
        // key button layout
        itemKey2Button.setLayoutX(550);
        itemKey2Button.setLayoutY(665);
        itemKey2Button.setScaleX(13);
        itemKey2Button.setScaleY(7);
        itemKey2Button.setOpacity(0);

        // ====================== KEY 3 ======================
        Image itemKey3 = new Image("sprites/UI/Icons/icn_key3.png");
        Button itemKey3Button = new Button();
        ImageView itemKey3view = new ImageView(itemKey3);
        itemKey3view.setVisible(false);
        itemKey3view.setX(700);
        itemKey3view.setY(615);
        // key button layout
        itemKey3Button.setLayoutX(750);
        itemKey3Button.setLayoutY(665);
        itemKey3Button.setScaleX(13);
        itemKey3Button.setScaleY(7);
        itemKey3Button.setOpacity(0);



        // Add all the images to the pane
        this.getChildren().addAll(hud, mariaPOV, itemKey1view, itemKey1Button, itemKey2view, itemKey2Button, itemKey3view, itemKey3Button);
        // Check if the player has the keys and set them to visible if they do
        if (GameManager.hasKey1){
            itemKey1view.setVisible(true);
        }
        if (GameManager.hasKey2){
            itemKey2view.setVisible(true);
        }
        if (GameManager.hasKey3){
            itemKey3view.setVisible(true);
        }

        //=== Item select ===
        itemKey1Button.setOnAction(event -> {
            if (GameManager.inventorySelect == true) {
                switch (GameManager.itemNeeded) {
                    case 1:
                        if (GameManager.hasKey1 == true) {
                            System.out.println("Works");
                        } else {
                            UIDeny.play();
                        }
                        break;
                    default:
                        UIDeny.play();
                        break;
                }
            }
        });
        itemKey2Button.setOnAction(event -> {
            if (GameManager.inventorySelect == true) {
                switch (GameManager.itemNeeded) {
                    case 2:
                        if (GameManager.hasKey2 == true) {
                            System.out.println("Works");
                        } else {
                            UIDeny.play();
                        }
                        break;
                    default:
                        UIDeny.play();
                        break;
                }
            }
        });
        itemKey3Button.setOnAction(event -> {
            if (GameManager.inventorySelect == true) {
                switch (GameManager.itemNeeded) {
                    case 3:
                        if (GameManager.hasKey3 == true) {
                            System.out.println("Works");
                        } else {
                            UIDeny.play();
                        }
                        break;
                    default:
                        UIDeny.play();
                        break;
                }
            }
        });


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
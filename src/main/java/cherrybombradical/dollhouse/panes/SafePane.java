package cherrybombradical.dollhouse.panes;

import cherrybombradical.dollhouse.Animations;
import cherrybombradical.dollhouse.AudioPlayer;
import cherrybombradical.dollhouse.GameManager;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SafePane extends Pane {

    private static final String SAFE_IMAGE_PATH = "sprites/UI/ui_safe1.png";
    private static final String OPEN_SAFE_IMAGE_PATH = "sprites/UI/ui_safe2.png";
    private static final String KEY_IMAGE_PATH = "sprites/UI/ui_safeKey.png";
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;

    private boolean safeUnlocked = false;

    private Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 64);

    private int section1Value = 0;
    private int section2Value = 0;
    private int section3Value = 0;
    private int section4Value = 0;

    private ImageView safeImageView;
    private ImageView openSafeImageView;
    private ImageView keyImageView;
    private Text section1Text;
    private Text section2Text;
    private Text section3Text;
    private Text section4Text;


    private final AudioPlayer sectionTick = new AudioPlayer("Audio/Sounds/SFX_SafeClick.mp3", false);
    private final AudioPlayer confirmSFX = new AudioPlayer("Audio/Sounds/SFX_Safe_Open.mp3", false);
    private final AudioPlayer denySFX = new AudioPlayer("Audio/Sounds/SFX_Safe_Deny.mp3", false);

    private final AudioPlayer uIMove = new AudioPlayer("Audio/Sounds/SFX_UIMove.mp3", false);
    private final AudioPlayer keyGrab = new AudioPlayer("Audio/Sounds/SFX_GetKey.mp3", false);

    private final Map4Pane map4Pane;

    public SafePane(Map4Pane map4Pane) {
        this.map4Pane = map4Pane;
        // Load the safe image and set its position
        Image safeImage = new Image(SAFE_IMAGE_PATH);
        safeImageView = new ImageView(safeImage);

        Image openSafeImage = new Image(OPEN_SAFE_IMAGE_PATH);
        openSafeImageView = new ImageView(openSafeImage);
        openSafeImageView.setVisible(false);

        Image keyImage = new Image(KEY_IMAGE_PATH);
        keyImageView = new ImageView(keyImage);
        keyImageView.setVisible(false);


        getChildren().addAll(safeImageView, openSafeImageView, keyImageView);

        // Create text nodes for each section and set their positions and font
        section1Text = new Text(String.valueOf(section1Value));
        section1Text.setFont(pixelFont);
        section1Text.setLayoutX(100);
        section1Text.setLayoutY(320);
        section2Text = new Text(String.valueOf(section2Value));
        section2Text.setFont(pixelFont);
        section2Text.setLayoutX(225);
        section2Text.setLayoutY(320);
        section3Text = new Text(String.valueOf(section3Value));
        section3Text.setFont(pixelFont);
        section3Text.setLayoutX(348);
        section3Text.setLayoutY(320);
        section4Text = new Text(String.valueOf(section4Value));
        section4Text.setFont(pixelFont);
        section4Text.setLayoutX(470);
        section4Text.setLayoutY(320);
        getChildren().addAll(section1Text, section2Text, section3Text, section4Text);

        // Create buttons for each section and set their positions
        Button section1UpButton = createUpButton(section1Text, 65, 215);
        Button section1DownButton = createDownButton(section1Text, 65, 325);
        Button section2UpButton = createUpButton(section2Text, 190, 215);
        Button section2DownButton = createDownButton(section2Text, 190, 325);
        Button section3UpButton = createUpButton(section3Text, 310, 215);
        Button section3DownButton = createDownButton(section3Text, 310, 325);
        Button section4UpButton = createUpButton(section4Text, 435, 215);
        Button section4DownButton = createDownButton(section4Text, 435, 325);

        Button confirmButton = createConfirmButton(485, 390);
        Button backButton = createBackButton(460, 100);
        Button collectKeyButton = createCollectKeyButton(185, 390);

        // Add the buttons to the pane
        getChildren().addAll(section1UpButton, section1DownButton, section2UpButton, section2DownButton,
                section3UpButton, section3DownButton, section4UpButton, section4DownButton, confirmButton, backButton, collectKeyButton);
    }

    public boolean isSafeUnlocked() {
        return safeUnlocked;
    }

    private Button createUpButton(Text sectionText, int x, int y) {
        Button button = new Button("+");
        button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOpacity(0);
        button.setOnAction(event -> {
            sectionTick.stop();
            int value = Integer.parseInt(sectionText.getText());
            value = (value + 1) % 10;
            sectionText.setText(String.valueOf(value));
            sectionTick.play();
            if (sectionText == section1Text) {
                section1Value = value;
            } else if (sectionText == section2Text) {
                section2Value = value;
            } else if (sectionText == section3Text) {
                section3Value = value;
            } else if (sectionText == section4Text) {
                section4Value = value;
            }
        });
        return button;
    }



    private Button createDownButton(Text sectionText, int x, int y) {
        Button button = new Button("-");
        button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOpacity(0);
        button.setOnAction(event -> {
            sectionTick.stop();
            int value = Integer.parseInt(sectionText.getText());
            value = (value + 9) % 10;
            sectionText.setText(String.valueOf(value));
            sectionTick.play();
            if (sectionText == section1Text) {
                section1Value = value;
            } else if (sectionText == section2Text) {
                section2Value = value;
            } else if (sectionText == section3Text) {
                section3Value = value;
            } else if (sectionText == section4Text) {
                section4Value = value;
            }
        });
        return button;
    }

    private Button createConfirmButton(int x, int y){
        Button button = new Button();
        button.setPrefSize(50, 50);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOpacity(0);
        button.setOnAction(event -> {
            checkCode();
        });
        return button;
    }

    private Button createBackButton(int x, int y){
        Button button = new Button("Back");
        button.setPrefSize(175, 85);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOpacity(0);
        button.setOnAction(event -> {
            //Remove SafePane from Map
            uIMove.play();
            Animations.UIMove(this).play();
        });
        return button;
    }

    private Button createCollectKeyButton(int x, int y){
        Button button = new Button("Collect Key");
        button.setPrefSize(175, 100);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setOpacity(0);
        button.setOnAction(event -> {
            GameManager.hasKey2 = true;
            keyGrab.play();
            map4Pane.addKeyImage();
            keyImageView.setVisible(false);
            button.setVisible(false);
        });
        return button;
    }


    private boolean checkCode() {
        if (section1Value == 0 && section2Value == 4 && section3Value == 5 && section4Value == 1) {
            System.out.println("Code entered!");
            confirmSFX.play();
            safeUnlocked = true;

            safeImageView.setVisible(false);
            section1Text.setVisible(false); section2Text.setVisible(false);
            section3Text.setVisible(false); section4Text.setVisible(false);
            openSafeImageView.setVisible(true);
            keyImageView.setVisible(true);
        }else{
            System.out.println("Wrong code!");
            denySFX.play();
        }
        return safeUnlocked;
    }


}

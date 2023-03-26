package cherrybombradical.dollhouse.panes;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class NotePane extends Pane {
    Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 32);
    public NotePane(String text){
        ImageView noteBG = new ImageView(new Image("sprites/UI/ui_note.png"));

        String noteString = text;
        Text noteText = new Text(noteString);
        noteText.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 28));

        this.getChildren().addAll(noteBG, noteText);

        noteBG.relocate(500, 0);
        noteText.relocate(520,25);

    }

}

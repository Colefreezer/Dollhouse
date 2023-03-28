package cherrybombradical.dollhouse.panes;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
/**
 * This class is responsible for creating a NotePane object, which is a pane containing an image and text.
 */
public class NotePane extends Pane {
    // Font used for the text
    Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 32);
    /**
     * Constructor for NotePane object.
     * @param text The text to be displayed on the NotePane.
     */
    public NotePane(String text, int textSize){
        // Create the background image
        ImageView noteBG = new ImageView(new Image("sprites/UI/ui_note.png"));
        // Get the text to be displayed
        String noteString = text;
        // Create the text object
        Text noteText = new Text(noteString);
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/Pixel.ttf"), 36);

        noteText.setFont(pixelFont);

        // Add the background image and text to the pane
        this.getChildren().addAll(noteBG, noteText);
        // Set the position of the background image and text
        noteBG.relocate(500, 0);
        noteText.relocate(520,25);
    }
}
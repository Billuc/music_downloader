import javax.swing.*;
import java.awt.*;

public class AlbumView extends JPanel {
    JLabel albumURLLabel;
    JTextArea albumURLField;

    public AlbumView() {
        super();
        this.setBackground(new Color(255,180,180));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        albumURLLabel = new JLabel("Album's Spotify URL : ");
        albumURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        albumURLField = new JTextArea();

        this.add(albumURLLabel);
        this.add(albumURLField);
    }

    public String getAlbumUrl() {
        return albumURLField.getText();
    }
}

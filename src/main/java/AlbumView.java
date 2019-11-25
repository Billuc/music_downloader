import javax.swing.*;
import java.awt.*;

class AlbumView extends JPanel {
    private JLabel albumURLLabel;
    private JTextArea albumURLField;

    AlbumView() {
        super();
        this.setBackground(new Color(255,180,180));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        albumURLLabel = new JLabel("Album's Spotify URL : ");
        albumURLLabel.setHorizontalTextPosition(JLabel.CENTER);

        albumURLField = new JTextArea();
        albumURLField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        albumURLField.setLineWrap(true);

        this.add(albumURLLabel);
        this.add(albumURLField);
    }

    String getAlbumUrl() {
        return albumURLField.getText();
    }
}

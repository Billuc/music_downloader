import javax.swing.*;
import java.awt.*;

class ArtistView extends JPanel {
    private JLabel artistURLLabel;
    private JTextArea artistURLField;

    ArtistView() {
        super();
        this.setBackground(new Color(200,200,255));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        artistURLLabel = new JLabel("<html>Artist's Spotify URL : <br/>(all albums will be downloaded)</html>");
        artistURLLabel.setHorizontalTextPosition(JLabel.CENTER);

        artistURLField = new JTextArea();
        artistURLField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.add(artistURLLabel);
        this.add(artistURLField);
    }

    String getArtistUrl() {
        return artistURLField.getText();
    }
}

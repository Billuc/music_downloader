import javax.swing.*;
import java.awt.*;

public class ArtistView extends JPanel {
    JLabel artistURLLabel;
    JTextArea artistURLField;

    public ArtistView() {
        super();
        this.setBackground(new Color(200,200,255));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        artistURLLabel = new JLabel("Artist's Spotify URL (all albums will be downloaded) :");
        artistURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        artistURLField = new JTextArea();

        this.add(artistURLLabel);
        this.add(artistURLField);
    }

    public String getArtistUrl() {
        return artistURLField.getText();
    }
}

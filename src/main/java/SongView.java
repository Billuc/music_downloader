import javax.swing.*;
import java.awt.*;

public class SongView extends JPanel {
    JLabel artistLabel;
    JTextArea artistField;
    JLabel songLabel;
    JTextArea songField;

    public SongView() {
        super();
        this.setBackground(new Color(225,225, 90));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(2,2, 5,5));

        artistLabel = new JLabel("Artist's name : ");
        artistLabel.setHorizontalTextPosition(JLabel.CENTER);
        artistField = new JTextArea();

        songLabel = new JLabel("Song's title : ");
        songLabel.setHorizontalTextPosition(JLabel.CENTER);
        songField = new JTextArea();

        this.add(artistLabel);
        this.add(artistField);
        this.add(songLabel);
        this.add(songField);
    }

    public String getArtist() {
        return artistField.getText();
    }

    public String getSongTitle() {
        return songField.getText();
    }
}

import javax.swing.*;
import java.awt.*;

class SongView extends JPanel {
    private JLabel artistLabel;
    private JTextArea artistField;
    private JLabel songLabel;
    private JTextArea songField;

    SongView() {
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

    String getArtist() {
        return artistField.getText();
    }

    String getSongTitle() {
        return songField.getText();
    }
}

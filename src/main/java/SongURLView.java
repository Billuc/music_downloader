import javax.swing.*;
import java.awt.*;

class SongURLView extends JPanel {
    private JLabel songURLLabel;
    private JTextArea songURLField;

    SongURLView() {
        super();
        this.setBackground(new Color(221, 161, 255));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        songURLLabel = new JLabel("Song's Spotify URL : ");
        songURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        songURLField = new JTextArea();

        this.add(songURLLabel);
        this.add(songURLField);
    }

    String getSongUrl() {
        return songURLField.getText();
    }
}

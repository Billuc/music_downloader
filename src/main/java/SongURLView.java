import javax.swing.*;
import java.awt.*;

public class SongURLView extends JPanel {
    JLabel songURLLabel;
    JTextArea songURLField;

    public SongURLView() {
        super();
        this.setBackground(new Color(255,180,180));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        songURLLabel = new JLabel("Song's Spotify URL : ");
        songURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        songURLField = new JTextArea();

        this.add(songURLLabel);
        this.add(songURLField);
    }

    public String getSongUrl() {
        return songURLField.getText();
    }
}

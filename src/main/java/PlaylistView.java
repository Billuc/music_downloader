import javax.swing.*;
import java.awt.*;

public class PlaylistView extends JPanel{
    JLabel playlistURLLabel;
    JTextArea playlistURLField;

    public PlaylistView() {
        super();
        this.setBackground(new Color(180,240,180));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.setLayout(new GridLayout(1,2, 5,5));

        playlistURLLabel = new JLabel("Playlist's Spotify URL : ");
        playlistURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        playlistURLField = new JTextArea();

        this.add(playlistURLLabel);
        this.add(playlistURLField);
    }

    public String getPlaylistUrl() {
        return playlistURLField.getText();
    }
}

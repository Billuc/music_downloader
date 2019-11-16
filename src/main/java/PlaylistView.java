import javax.swing.*;
import java.awt.*;

class PlaylistView extends JPanel{
    private JLabel playlistURLLabel;
    private JTextArea playlistURLField;

    PlaylistView() {
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

    String getPlaylistUrl() {
        return playlistURLField.getText();
    }
}

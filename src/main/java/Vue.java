import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vue extends JPanel implements ActionListener {
    final int SONG_PANEL_INDEX = 0;
    final int ALBUM_PANEL_INDEX = 1;
    final int PLAYLIST_PANEL_INDEX = 2;

    JTabbedPane modeSelector;

    JPanel dlFromSongPanel;

    JLabel artistLabel;
    JTextArea artistField;

    JLabel songLabel;
    JTextArea songField;

    JPanel dlFromPlaylistPanel;

    JLabel playlistURLLabel;
    JTextArea playlistURLField;

    JPanel dlFromAlbumPanel;

    JLabel albumURLLabel;
    JTextArea albumURLField;

    JPanel moreOptionsPanel;

    JCheckBox withMetaDataCheckBox;

    JPanel songFolderPanel;
    JLabel songFolderLabel;
    JTextArea songFolderField;
    JButton folderChooserButton;

    JPanel extensionPanel;
    JLabel extensionLabel;
    JComboBox extensionChooser;

    JCheckBox trimMusicCheckBox;

    JButton downloadButton;

    public Vue() {
        super();

        this.setLayout(new BorderLayout());
        this.setBackground(Color.CYAN);

        initModeSelector();
        initOtherOptionsPane();

        this.add(modeSelector, BorderLayout.CENTER);
        this.add(moreOptionsPanel, BorderLayout.SOUTH);
    }

    private void initModeSelector() {
        initSongPanel();
        initAlbumPanel();
        initPlaylistPanel();

        modeSelector = new JTabbedPane();
        modeSelector.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        modeSelector.addTab("DL From Song", dlFromSongPanel);
        modeSelector.addTab("DL From Album", dlFromAlbumPanel);
        modeSelector.addTab("DL From Playlist", dlFromPlaylistPanel);
    }

    private void initSongPanel() {
        dlFromSongPanel = new JPanel();
        dlFromSongPanel.setBackground(Color.RED);
    }

    private void initAlbumPanel() {
        dlFromAlbumPanel = new JPanel();
        dlFromAlbumPanel.setBackground(Color.green);
    }

    private void initPlaylistPanel() {
        dlFromPlaylistPanel = new JPanel();
        dlFromPlaylistPanel.setBackground(Color.blue);
    }

    private void initOtherOptionsPane() {
        moreOptionsPanel = new JPanel();
        moreOptionsPanel.setLayout(new GridLayout(3,2));
        moreOptionsPanel.setBackground(Color.yellow);
        moreOptionsPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        initOptions();

        moreOptionsPanel.add(withMetaDataCheckBox);
        moreOptionsPanel.add(songFolderPanel);
        moreOptionsPanel.add(extensionPanel);
        moreOptionsPanel.add(trimMusicCheckBox);
        moreOptionsPanel.add(downloadButton);
    }

    private void initOptions() {
        withMetaDataCheckBox = new JCheckBox("Download Metadata", true);
        withMetaDataCheckBox.setHorizontalTextPosition(JCheckBox.LEFT);

        initSongFolderPanel();
        initExtensionPanel();

        trimMusicCheckBox = new JCheckBox("Trim the music", false);
        trimMusicCheckBox.setHorizontalTextPosition(JCheckBox.LEFT);

        downloadButton = new JButton("Download");
        downloadButton.addActionListener(this);
    }

    private void initSongFolderPanel() {
        songFolderPanel = new JPanel();
        songFolderPanel.setLayout(new BoxLayout(songFolderPanel, BoxLayout.X_AXIS));

        songFolderLabel = new JLabel("Song Folder : ");
        songFolderField = new JTextArea();
        folderChooserButton = new JButton("Choose a folder");
        folderChooserButton.addActionListener(this);

        songFolderPanel.add(songFolderLabel);
        songFolderPanel.add(songFolderField);
        songFolderPanel.add(folderChooserButton);
    }

    private void initExtensionPanel() {
        extensionPanel = new JPanel();
        extensionPanel.setLayout(new BoxLayout(extensionPanel, BoxLayout.X_AXIS));

        extensionLabel = new JLabel("Music Extension");

        String[] extensions = new String[] {".mp3", ".m4a", ".flac"};
        extensionChooser = new JComboBox(extensions);
        extensionChooser.setSelectedIndex(0);
        extensionChooser.addActionListener(this);

        extensionPanel.add(extensionLabel);
        extensionPanel.add(extensionChooser);
    }

    public void actionPerformed(ActionEvent actionEvent) {

    }
}

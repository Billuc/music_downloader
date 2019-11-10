import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vue extends JPanel implements ActionListener {
    final int SONG_PANEL_INDEX = 0;
    final int ALBUM_PANEL_INDEX = 1;
    final int PLAYLIST_PANEL_INDEX = 2;

    final int MP3_INDEX = 0;
    final int M4A_INDEX = 1;
    final int FLAC_INDEX = 2;

    final String PATH_TO_SPOTDL = "../../../resources/spotdl/bin/";

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
        dlFromSongPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        dlFromSongPanel.setLayout(new GridLayout(2,2, 5,5));

        artistLabel = new JLabel("Nom de l'artiste : ");
        artistLabel.setHorizontalTextPosition(JLabel.CENTER);
        artistField = new JTextArea();

        songLabel = new JLabel("Titre de la chanson : ");
        songLabel.setHorizontalTextPosition(JLabel.CENTER);
        songField = new JTextArea();

        dlFromSongPanel.add(artistLabel);
        dlFromSongPanel.add(artistField);
        dlFromSongPanel.add(songLabel);
        dlFromSongPanel.add(songField);
    }

    private void initAlbumPanel() {
        dlFromAlbumPanel = new JPanel();
        dlFromAlbumPanel.setBackground(Color.green);
        dlFromAlbumPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        dlFromAlbumPanel.setLayout(new GridLayout(1,2, 5,5));

        albumURLLabel = new JLabel("URL Spotify de l'album : ");
        albumURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        albumURLField = new JTextArea();

        dlFromAlbumPanel.add(albumURLLabel);
        dlFromAlbumPanel.add(albumURLField);
    }

    private void initPlaylistPanel() {
        dlFromPlaylistPanel = new JPanel();
        dlFromPlaylistPanel.setBackground(Color.blue);
        dlFromPlaylistPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        dlFromPlaylistPanel.setLayout(new GridLayout(1,2, 5,5));

        playlistURLLabel = new JLabel("URL Spotify de la playlist : ");
        playlistURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        playlistURLField = new JTextArea();

        dlFromPlaylistPanel.add(playlistURLLabel);
        dlFromPlaylistPanel.add(playlistURLField);
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
        if (actionEvent.getSource() == downloadButton) {
            String commandToExecute = /*PATH_TO_SPOTDL + */"spotdl ";

            if (!withMetaDataCheckBox.isSelected()) {
                commandToExecute += "--no-metadata ";
            }

            if (trimMusicCheckBox.isSelected()) {
                commandToExecute += "--trim-silence ";
            }

            if (songFolderField.getText() != null && !"".equals(songFolderField.getText())) {
                commandToExecute += "--folder ";
                commandToExecute += songFolderField.getText();
                commandToExecute += " ";
            }

            if (modeSelector.getSelectedIndex() == SONG_PANEL_INDEX) {
                String artist = artistField.getText();
                String title = songField.getText();
                String toSearch = "\"" + artist + " - " + title + "\"";

                commandToExecute += "--song ";
                commandToExecute += toSearch;
                commandToExecute += " ";
            } else if (modeSelector.getSelectedIndex() == ALBUM_PANEL_INDEX) {
                String url = albumURLField.getText();

                commandToExecute += "--album ";
                commandToExecute += url;
                commandToExecute += " ";
            } else if (modeSelector.getSelectedIndex() == PLAYLIST_PANEL_INDEX) {
                String url = playlistURLField.getText();

                commandToExecute += "--playlist ";
                commandToExecute += url;
                commandToExecute += " ";
            }

            System.out.println(commandToExecute);

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("bash", "-c", commandToExecute);

            try {
                Process process = processBuilder.start();

                StringBuilder output = new StringBuilder();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line + "\n");
                }

                int exitVal = process.waitFor();
                if (exitVal == 0) {
                    System.out.println("Success!");
                    System.out.println(output);
                } else {
                    System.out.println("Failure!");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //folderChooserButton;
    }
}

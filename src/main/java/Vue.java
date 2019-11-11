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
    final int ARTIST_PANEL_INDEX = 2;
    final int PLAYLIST_PANEL_INDEX = 3;

    final int MP3_INDEX = 0;
    final int M4A_INDEX = 1;
    final int FLAC_INDEX = 2;

    final String PATH_TO_SPOTDL = "../../../resources/spotdl/bin/";
    final String SPOTDL = "spotdl ";

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

    JPanel dlFromArtistPanel;
    JLabel artistURLLabel;
    JTextArea artistURLField;

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

    JFrame parent;

    String options;

    public Vue(JFrame parent) {
        super();

        this.parent = parent;

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
        initArtistPanel();
        initPlaylistPanel();

        modeSelector = new JTabbedPane();
        modeSelector.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        modeSelector.addTab("DL From Song", dlFromSongPanel);
        modeSelector.addTab("DL From Album", dlFromAlbumPanel);
        modeSelector.addTab("DL From Artist (all albums)", dlFromArtistPanel);
        modeSelector.addTab("DL From Playlist", dlFromPlaylistPanel);
    }

    private void initSongPanel() {
        dlFromSongPanel = new JPanel();
        dlFromSongPanel.setBackground(Color.RED);
        dlFromSongPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        dlFromSongPanel.setLayout(new GridLayout(2,2, 5,5));

        artistLabel = new JLabel("Artist's name : ");
        artistLabel.setHorizontalTextPosition(JLabel.CENTER);
        artistField = new JTextArea();

        songLabel = new JLabel("Song's title : ");
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

    private void initArtistPanel() {
        dlFromArtistPanel = new JPanel();
        dlFromArtistPanel.setBackground(Color.magenta);
        dlFromArtistPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        dlFromArtistPanel.setLayout(new GridLayout(1,2, 5,5));

        artistURLLabel = new JLabel("URL Spotify de l'artiste (tous les albums seront téléchargés) : ");
        artistURLLabel.setHorizontalTextPosition(JLabel.CENTER);
        artistURLField = new JTextArea();

        dlFromArtistPanel.add(artistURLLabel);
        dlFromArtistPanel.add(artistURLField);
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

    private String generateCommand() {
        String dlOption = "";
        options = "--overwrite skip ";


        if (!withMetaDataCheckBox.isSelected()) {
            options += "--no-metadata ";
        }

        if (trimMusicCheckBox.isSelected()) {
            options += "--trim-silence ";
        }

        if (songFolderField.getText() != null && !"".equals(songFolderField.getText())) {
            options += "--folder ";
            options += "\"" + songFolderField.getText() + "\"";
            options += " ";
        }

        options += "--output-ext ";
        options += extensionChooser.getSelectedItem();
        options += " ";

        if (modeSelector.getSelectedIndex() == SONG_PANEL_INDEX) {
            String artist = artistField.getText();
            String title = songField.getText();
            String toSearch = "\"" + artist + " - " + title + "\"";

            dlOption += "--song ";
            dlOption += toSearch;
            dlOption += " ";
        } else {
            dlOption += "--write-to \"tracks.txt\" ";

            if (modeSelector.getSelectedIndex() == ALBUM_PANEL_INDEX) {
                String url = albumURLField.getText();

                dlOption += "--album ";
                dlOption += url;
                dlOption += " ";
            } else if (modeSelector.getSelectedIndex() == PLAYLIST_PANEL_INDEX) {
                String url = playlistURLField.getText();

                dlOption += "--playlist ";
                dlOption += url;
                dlOption += " ";
            } else if (modeSelector.getSelectedIndex() == ARTIST_PANEL_INDEX) {
                String url = artistURLField.getText();

                dlOption += "--all-albums ";
                dlOption += url;
                dlOption += " ";
            }

            return SPOTDL + dlOption;
        }

        return SPOTDL + options + dlOption;
    }

    private void executeCommand(String command) {
        System.out.println(command);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.start();
            DownloadingSongDialog dsd = new DownloadingSongDialog(parent, "Downloading song ...", false);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            new ReaderThread(reader, dsd).start();
            new ReaderThread(errorReader, dsd).start();

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                JOptionPane.showMessageDialog(this, "The execution of the command was a failure !", "Failure!", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == downloadButton) {
            executeCommand(generateCommand());

            if (modeSelector.getSelectedIndex() != SONG_PANEL_INDEX) {
                executeCommand(SPOTDL + options + "--list \"tracks.txt\"");
                executeCommand("rm tracks.txt");
            }
        }
        else if (actionEvent.getSource() == folderChooserButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Dossier de sauvegarde des musiques");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // disable the "All files" option
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String folderName = chooser.getSelectedFile().getAbsolutePath();
                songFolderField.setText(folderName);
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vue extends JPanel implements ActionListener {
    final int SONG_PANEL_INDEX = 0;
    final int SONG_URL_PANEL_INDEX = 1;
    final int ALBUM_PANEL_INDEX = 2;
    final int ARTIST_PANEL_INDEX = 3;
    final int PLAYLIST_PANEL_INDEX = 4;

    final int MP3_INDEX = 0;
    final int M4A_INDEX = 1;
    final int FLAC_INDEX = 2;

    final String PATH_TO_SPOTDL = "../../../resources/spotdl/bin/";
    final String SPOTDL = "spotdl ";

    JTabbedPane modeSelector;

    SongView dlFromSongPanel;
    SongURLView dlFromSongUrlPanel;
    PlaylistView dlFromPlaylistPanel;
    AlbumView dlFromAlbumPanel;
    ArtistView dlFromArtistPanel;

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
        this.setBackground(new Color(255,255,255));

        initModeSelector();
        initOtherOptionsPane();

        this.add(modeSelector, BorderLayout.CENTER);
        this.add(moreOptionsPanel, BorderLayout.SOUTH);
    }

    private void initModeSelector() {
        dlFromSongPanel = new SongView();
        dlFromSongUrlPanel = new SongURLView();
        dlFromPlaylistPanel = new PlaylistView();
        dlFromAlbumPanel = new AlbumView();
        dlFromArtistPanel = new ArtistView();

        modeSelector = new JTabbedPane();
        modeSelector.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));

        modeSelector.addTab("DL From Song", dlFromSongPanel);
        modeSelector.addTab("DL From Song URL", dlFromSongUrlPanel);
        modeSelector.addTab("DL From Album", dlFromAlbumPanel);
        modeSelector.addTab("DL From Artist (all albums)", dlFromArtistPanel);
        modeSelector.addTab("DL From Playlist", dlFromPlaylistPanel);
    }

    private void initOtherOptionsPane() {
        moreOptionsPanel = new JPanel();
        moreOptionsPanel.setLayout(new GridLayout(3,2));
        moreOptionsPanel.setBackground(new Color(180,180,180));
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
            String artist = dlFromSongPanel.getArtist();
            String title = dlFromSongPanel.getSongTitle();
            String toSearch = "\"" + artist + " - " + title + "\"";

            dlOption += "--song ";
            dlOption += toSearch;
            dlOption += " ";
        } else if (modeSelector.getSelectedIndex() == SONG_URL_PANEL_INDEX) {
            String url = dlFromSongUrlPanel.getSongUrl();

            dlOption += "--song ";
            dlOption += url;
            dlOption += " ";
        } else {
            dlOption += "--write-to \"tracks.txt\" ";

            if (modeSelector.getSelectedIndex() == ALBUM_PANEL_INDEX) {
                String url = dlFromAlbumPanel.getAlbumUrl();

                dlOption += "--album ";
                dlOption += url;
                dlOption += " ";
            } else if (modeSelector.getSelectedIndex() == PLAYLIST_PANEL_INDEX) {
                String url = dlFromPlaylistPanel.getPlaylistUrl();

                dlOption += "--playlist ";
                dlOption += url;
                dlOption += " ";
            } else if (modeSelector.getSelectedIndex() == ARTIST_PANEL_INDEX) {
                String url = dlFromArtistPanel.getArtistUrl();

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

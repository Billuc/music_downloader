import javax.swing.*;
import java.awt.*;

class Vue extends JPanel {
    private final int SONG_PANEL_INDEX = 0;
    private final int SONG_URL_PANEL_INDEX = 1;
    private final int ALBUM_PANEL_INDEX = 2;
    private final int ARTIST_PANEL_INDEX = 3;
    private final int PLAYLIST_PANEL_INDEX = 4;

    private final String SPOTDL = "spotdl ";

    private JTabbedPane modeSelector;

    private SongView dlFromSongPanel;
    private SongURLView dlFromSongUrlPanel;
    private PlaylistView dlFromPlaylistPanel;
    private AlbumView dlFromAlbumPanel;
    private ArtistView dlFromArtistPanel;

    private OptionsPanel moreOptionsPanel;

    private Fenetre parent;
    private Controler controler;

    Vue(Fenetre parent, Controler pControler) {
        super();
        this.setBackground(new Color(211, 211, 211, 255));

        this.parent = parent;
        this.controler = pControler;

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        initModeSelector(c);
        initOtherOptionsPane(c);
    }

    private void initModeSelector(GridBagConstraints c) {
        dlFromSongPanel = new SongView();
        dlFromSongUrlPanel = new SongURLView();
        dlFromPlaylistPanel = new PlaylistView();
        dlFromAlbumPanel = new AlbumView();
        dlFromArtistPanel = new ArtistView();

        modeSelector = new JTabbedPane();
        modeSelector.addTab("DL From Song", dlFromSongPanel);
        modeSelector.addTab("DL From Song URL", dlFromSongUrlPanel);
        modeSelector.addTab("DL From Album", dlFromAlbumPanel);
        modeSelector.addTab("DL From Artist (all albums)", dlFromArtistPanel);
        modeSelector.addTab("DL From Playlist", dlFromPlaylistPanel);

        c.insets = new Insets(10,10,5,10);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1f;
        c.weighty = 1f;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(modeSelector, c);
    }

    private void initOtherOptionsPane(GridBagConstraints c) {
        moreOptionsPanel = new OptionsPanel(this, controler);

        c.insets = new Insets(5,10,10,10);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1f;
        c.weighty = 0f;
        c.fill = GridBagConstraints.BOTH;
        this.add(moreOptionsPanel, c);
    }

    String[] generateCommands() {
        String dlOption = "";
        String options = "--overwrite skip ";

        options += moreOptionsPanel.getOptions();

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

            return new String[] { SPOTDL + dlOption,
                                SPOTDL + options + "--list \"tracks.txt\"",
                                "rm tracks.txt" };
        }

        return new String[]{ SPOTDL + options + dlOption };
    }
}

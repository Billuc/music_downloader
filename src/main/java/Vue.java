import javax.swing.*;

public class Vue extends JPanel {
    JLabel artistLabel;
    JTextArea artistField;

    JLabel songLabel;
    JTextArea songField;

    JLabel playlistURLLabel;
    JTextArea playlistURLField;

    JLabel albumURLLabel;
    JTextArea albumURLField;

    JCheckBox withMetaDataCheckBox;
    boolean withMetaData;

    JLabel songFolderLabel;
    JTextArea songFolderField;
    JButton folderChooserButton;

    JComboBox extensionChooser;

    JCheckBox trimMusicCheckBox;
    boolean trimMusic;

    public Vue() {
        super();


    }
}

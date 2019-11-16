import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel implements ActionListener {
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

    Vue parent;

    public OptionsPanel(Vue parent) {
        super();
        this.setLayout(new GridLayout(3,2));
        this.setBackground(new Color(180,180,180));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.parent = parent;

        initOptions();

        this.add(withMetaDataCheckBox);
        this.add(songFolderPanel);
        this.add(extensionPanel);
        this.add(trimMusicCheckBox);
        this.add(downloadButton);
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
            parent.download();
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

    public String getOptions() {
        String options = "";

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

        return options;
    }
}

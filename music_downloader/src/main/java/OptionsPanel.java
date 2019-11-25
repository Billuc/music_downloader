import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

public class OptionsPanel extends JPanel implements ActionListener {
    private JCheckBox withMetaDataCheckBox;

    private JLabel songFolderLabel;
    private JTextArea songFolderField;
    private JButton folderChooserButton;

    private JLabel extensionLabel;
    private JComboBox extensionChooser;

    private JCheckBox trimMusicCheckBox;

    private JButton downloadButton;

    private Vue parent;
    private Controler controleur;

    OptionsPanel(Vue parent, Controler pControler) {
        super();
        this.setBackground(new Color(201, 255, 89, 96));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        this.parent = parent;
        this.controleur = pControler;

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        initSongFolderOption(layout, c);
        initExtensionOption(layout, c);
        initTrimMusicCheckbox(layout, c);
        initMetaDataCheckbox(layout, c);
        initDownloadButton(layout, c);
    }

    private void initSongFolderOption(GridBagLayout layout, GridBagConstraints c) {
        songFolderLabel = new JLabel("Song Folder : ");

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(songFolderLabel, c);
        this.add(songFolderLabel);

        songFolderField = new JTextArea();
        songFolderField.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,0);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(songFolderField, c);
        this.add(songFolderField);

        folderChooserButton = new JButton("Choose a folder");
        folderChooserButton.addActionListener(this);

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,0,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(folderChooserButton, c);
        this.add(folderChooserButton);
    }

    private void initExtensionOption(GridBagLayout layout, GridBagConstraints c) {
        extensionLabel = new JLabel("Music Extension : ");

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(extensionLabel, c);
        this.add(extensionLabel);

        String[] extensions = new String[] {".mp3", ".m4a", ".flac"};
        extensionChooser = new JComboBox(extensions);
        extensionChooser.setSelectedIndex(0);
        extensionChooser.addActionListener(this);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(extensionChooser, c);
        this.add(extensionChooser);
    }

    private void initTrimMusicCheckbox(GridBagLayout layout, GridBagConstraints c) {
        trimMusicCheckBox = new JCheckBox("Trim the music ", false);
        trimMusicCheckBox.setHorizontalTextPosition(JCheckBox.LEFT);

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 2;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(trimMusicCheckBox, c);
        this.add(trimMusicCheckBox);
    }

    private void initMetaDataCheckbox(GridBagLayout layout, GridBagConstraints c) {
        withMetaDataCheckBox = new JCheckBox("Download Metadata ", true);
        withMetaDataCheckBox.setHorizontalTextPosition(JCheckBox.LEFT);

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = 5;
        c.ipady = 2;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(withMetaDataCheckBox, c);
        this.add(withMetaDataCheckBox);
    }

    private void initDownloadButton(GridBagLayout layout, GridBagConstraints c) {
        downloadButton = new JButton("Download");
        downloadButton.addActionListener(this);
        downloadButton.setBackground(Color.RED);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.ipadx = 5;
        c.ipady = 5;
        c.weightx = 1f;
        c.weighty = 1f;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(downloadButton, c);
        this.add(downloadButton);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == downloadButton) {
            new Thread(() -> controleur.download()).start();
        }
        else if (actionEvent.getSource() == folderChooserButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Dossier de sauvegarde des musiques");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setCurrentDirectory(new File(".."));

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

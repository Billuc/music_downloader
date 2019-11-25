import javax.swing.*;
import java.awt.*;

public class DownloadDialog extends JFrame {
    private JTextArea infosDisplayer;
    private JTextArea downloadingDisplayer;

    DownloadDialog(Controler ctrler) {
        super();
        this.setSize(500,300);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.setAlwaysOnTop(true);
        this.addWindowListener(ctrler);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        infosDisplayer = new JTextArea();
        infosDisplayer.setEditable(false);
        //infosDisplayer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        infosDisplayer.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(infosDisplayer);

        c.insets = new Insets(5,5,0,5);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1f;
        c.weighty = 0.4f;
        c.fill = GridBagConstraints.BOTH;
        this.add(scroll, c);

        downloadingDisplayer = new JTextArea();
        downloadingDisplayer.setEditable(false);
        //downloadingDisplayer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        downloadingDisplayer.setLineWrap(true);
        JScrollPane dlScroll = new JScrollPane(downloadingDisplayer);

        c.insets = new Insets(0,5,5,5);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1f;
        c.weighty = 1f;
        c.fill = GridBagConstraints.BOTH;
        this.add(dlScroll, c);
    }

    public void start() {
        this.setVisible(true);
    }

    void appendMsg(String message) {
        if (message.contains("INFO")) {
            infosDisplayer.append(message + "\n");
            infosDisplayer.setCaretPosition(infosDisplayer.getText().length());
        }
        else {
            downloadingDisplayer.append(message + "\n");
            downloadingDisplayer.setCaretPosition(downloadingDisplayer.getText().length());
        }
    }
}

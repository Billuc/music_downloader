import javax.swing.*;
import java.awt.*;

public class DownloadDialog extends JDialog {
    JTextArea displayer;
    JScrollPane scroll;

    public DownloadDialog() {
        super();
        this.setLayout(new BorderLayout());
        this.setSize(400,300);
        this.setLocationByPlatform(true);

        displayer = new JTextArea();
        displayer.setEditable(false);
        displayer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        displayer.setLineWrap(true);
        displayer.setCaretColor(Color.RED);

        scroll = new JScrollPane(displayer);
        this.add(scroll);
    }

    public void start() {
        this.setVisible(true);
    }

    public void appendMsg(String message) {
        displayer.append(message + "\n");
        displayer.setCaretPosition(displayer.getText().length());
    }
}

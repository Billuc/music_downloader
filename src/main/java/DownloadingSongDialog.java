import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class DownloadingSongDialog extends JDialog {
    //JScrollPane jsp;
    private JTextPane text;
    private StyledDocument document;
    private SimpleAttributeSet normalText;
    private SimpleAttributeSet errorText;

    DownloadingSongDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setSize(500,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //jsp = new JScrollPane();
        initTextArea();
        //jsp.add(text);
        this.add(text, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void initTextArea() {
        text = new JTextPane();
        text.setText("");

        document = text.getStyledDocument();

        normalText = new SimpleAttributeSet();
        StyleConstants.setForeground(normalText, Color.BLACK);
        StyleConstants.setFontFamily(normalText, "Lucida Console");
        StyleConstants.setAlignment(normalText, StyleConstants.ALIGN_JUSTIFIED);

        errorText = new SimpleAttributeSet();
        StyleConstants.setForeground(errorText, Color.RED);
        StyleConstants.setBold(errorText, true);
        StyleConstants.setFontFamily(errorText, "Lucida Console");
        StyleConstants.setAlignment(errorText, StyleConstants.ALIGN_JUSTIFIED);
    }

    private void appendToPane(JTextPane tp, String msg, SimpleAttributeSet style) {
        try {
            document.insertString(document.getLength(), msg, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    void write(String s) {
        appendToPane(text, s, normalText);
    }

    void writeLn(String s) {
        write(s + "\n");
    }

    void error(String s) {
        appendToPane(text, s, errorText);
    }

    void errorLn(String s) {
        error(s + "\n");
    }
}

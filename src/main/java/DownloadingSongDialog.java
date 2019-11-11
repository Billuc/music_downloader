import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class DownloadingSongDialog extends JDialog {
    JScrollPane jsp;
    JTextPane text;

    public DownloadingSongDialog(JFrame parent, String title, boolean modal) {
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
        text.setBackground(Color.pink);
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }

    public void write(String s) {
        appendToPane(text, s, Color.BLACK);
    }

    public void writeLn(String s) {
        write(s + "\n");
    }

    public void error(String s) {
        appendToPane(text, s, Color.RED);
    }

    public void errorLn(String s) {
        error(s + "\n");
    }
}

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    Vue laVue;

    public Fenetre(int pWidth, int pHeight) {
        super("Music Downloader");
        this.setSize(pWidth, pHeight);
        this.setMinimumSize(new Dimension(400, 300));
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        laVue = new Vue(this);
        this.add(laVue);

        this.setVisible(true);
    }
}

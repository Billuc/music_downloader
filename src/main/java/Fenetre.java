import javax.swing.*;
import java.awt.*;

class Fenetre extends JFrame {
    Fenetre(int pWidth, int pHeight) {
        super("Music Downloader");
        this.setSize(pWidth, pHeight);
        this.setMinimumSize(new Dimension(675, 300));
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Vue laVue = new Vue(this);
        this.add(laVue);

        this.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;

class Fenetre extends JFrame {
    Controler controler;
    Vue laVue;

    Fenetre(Controler pControler) {
        super("Music Downloader");
        this.setSize(675, 300);
        this.setMinimumSize(new Dimension(675, 300));
        this.setMaximumSize(new Dimension(675, 300));
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.controler = pControler;

        laVue = new Vue(this, controler);
        this.add(laVue);

        this.setVisible(true);
    }
}

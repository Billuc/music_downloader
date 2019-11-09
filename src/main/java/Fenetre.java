import javax.swing.*;

public class Fenetre extends JFrame {
    Vue laVue;

    public Fenetre(int pWidth, int pHeight) {
        super("Music Downloader");
        this.setSize(pWidth, pHeight);
        this.setLocationByPlatform(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        laVue = new Vue();
        this.add(laVue);

        this.setVisible(true);
    }
}

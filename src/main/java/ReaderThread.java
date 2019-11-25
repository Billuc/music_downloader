import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class ReaderThread extends Thread {
    private BufferedReader reader;
    private DownloadDialog sortie;

    ReaderThread(BufferedReader pReader, DownloadDialog pSortie) {
        reader = pReader;
        sortie = pSortie;
    }

    @Override
    public void run() {
        super.run();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                sortie.appendMsg(line);
            }
        } catch (IOException ie) {
            for (StackTraceElement ste : ie.getStackTrace()) {
                //System.err.println(ste.toString());
                sortie.appendMsg(ste.toString() + "\n");
            }
        }
    }
}

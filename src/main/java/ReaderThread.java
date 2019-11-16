import java.io.BufferedReader;
import java.io.IOException;

public class ReaderThread extends Thread {
    private BufferedReader reader;
    private DownloadingSongDialog dialog;

    ReaderThread(BufferedReader pReader, DownloadingSongDialog pDialog) {
        reader = pReader;
        dialog = pDialog;
    }

    @Override
    public void run() {
        super.run();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                dialog.writeLn(line);
            }
        } catch (IOException ie) {
            for (StackTraceElement ste : ie.getStackTrace()) {
                dialog.errorLn(ste.toString());
                //System.err.println(ste.toString());
            }

            //dialog.repaint();
        }
    }
}

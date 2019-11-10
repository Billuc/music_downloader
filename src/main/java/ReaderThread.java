import java.io.BufferedReader;
import java.io.IOException;

public class ReaderThread extends Thread {
    BufferedReader reader;

    public ReaderThread(BufferedReader pReader) {
        reader = pReader;
    }

    @Override
    public void run() {
        super.run();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controler {
    Fenetre fen;

    public Controler() {
        fen = new Fenetre(this);
    }

    public void executeCommand(final String command, DownloadDialog sortie) {
        System.out.println(command);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            BufferedReader errorReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));

            new ReaderThread(reader, sortie).start();
            new ReaderThread(errorReader, sortie).start();

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                JOptionPane.showMessageDialog(fen, "The execution of the command was a failure !", "Failure!", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void download() {
        DownloadDialog dd = new DownloadDialog();
        dd.start();

        String[] commands = fen.laVue.generateCommands();
        for (String command : commands) {
            executeCommand(command, dd);
        }
        dd.appendMsg("Téléchargement terminé ! \n");
    }
}

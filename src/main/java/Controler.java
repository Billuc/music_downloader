import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Controler implements WindowListener {
    private Fenetre fen;
    private String commander;
    private String modifier;
    private List<Process> processList;
    DownloadDialog dialog;

    Controler() {
        fen = new Fenetre(this);

        String os = detectOS();

        if ("Windows".equals(os)) {
            commander = "cmd";
            modifier = "/c";
        }
        else if ("Linux".equals(os)) {
            commander = "bash";
            modifier = "-c";
        }

        processList = new ArrayList<>(3);
        dialog = new DownloadDialog(this);
    }

    private String detectOS() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return "Windows";
        }
        else {
            return "Linux";
        }
    }

    private void executeCommand(final String command, DownloadDialog sortie) {
        System.out.println(command);

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commander, modifier, command);

        try {
            Process process = processBuilder.start();
            processList.add(process);

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

            processList.remove(process);
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void download() {
        dialog.start();
        fen.setVisible(false);

        String[] commands = fen.laVue.generateCommands();
        for (String command : commands) {
            executeCommand(command, dialog);
        }
        dialog.appendMsg("Téléchargement terminé ! \n");
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        if (windowEvent.getSource() == fen) {
            System.exit(0);
        } else if (windowEvent.getSource() == dialog) {
            for (Process p : processList) {
                p.destroy();
            }

            //On supprime tracks.txt pour être sûr
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(commander, modifier, "rm tracks.txt");

            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(fen, "Error while deleting music_downloader_2/tracks.txt\nDelete it manually to avoid problems during the next download !", "IOException thrown !", JOptionPane.ERROR_MESSAGE);
            }

            fen.setVisible(true);
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}

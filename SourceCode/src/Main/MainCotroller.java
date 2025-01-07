package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import FileSource.*;
import Startmenue.*;
import GameMode.*;

/**
 * Diese Klasse managed die Applikation vom Startmenü aus
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-24
 */
public class MainCotroller implements ActionListener {
    private StartPanel startPanel = new StartPanel(this);
    private GameController gameController = new GameController();

    private FileInfoPanel fip;
    private FilePanel fp;
    private FileLoader fileLoader = new FileLoader();

    public MainCotroller() {
        this.startPanel.setVisible(true);
    }

    public static void main(String[] args) {
        new MainCotroller();
    }

    // Hier findet die Steuerung der Klassen statt
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        /**
         * EVENTHANDLER nur für die DATEIVERWALTUNG
         */

        if ("start_file".equals(action)){
            // Datei Bearbeitung
            fip = new FileInfoPanel(this);
            startPanel.setVisible(false);
        } else if (action.equals("weiter_fileinfopanel")){
            fip.setVisible(false);
            fp = new FilePanel(this);
        } else if (action.equals("beenden_fileinfopanel")){
            System.exit(0);
        } else if(action.equals("zurueck_filepanel")) {
            fp.setVisible(false);
            startPanel.setVisible(true);
        }else if(action.equals("delete_filepanel")){
            fp.deleteFragen();
        }else if(action.equals("laden_filepanel")){
            String filename = fp.getText();
            String pool = fileLoader.textFormat(filename);
            fp.loadFragen(pool);
        }else if(action.equals("speichern_filepanel")){
            String fragenuAntworten = fp.getText();
            String[][] fragepool = fileLoader.saveFormat(fragenuAntworten);
            fileLoader.saveFragen("neueFragen2", fragepool);
        }
        /**
         * EVENTHANDLER nur für den QUIZMODUS
         */
        if ("start_quiz".equals(action)) {
            // Quiz Modus
        }
        /**
         * EVENTHANDLER nur für den SPIELMODUS
         */
        if ("start_game".equals(action)) {
            gameController.startGame(); // Spiel starten
        }
        /**
         * EVENTHANDLER nur für den EXIT
         */
        if ("start_exit".equals(action)) {
            startPanel.close();
        }
    }
}

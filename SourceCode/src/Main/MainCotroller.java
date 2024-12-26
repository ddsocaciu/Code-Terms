package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Startmenue.PanelStart;
import GameMode.GameController;

/**
 * Diese Klasse managed die Applikation vom Startmenü aus
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-24
 */
public class MainCotroller implements ActionListener {
    private PanelStart startPanel = new PanelStart(this);
    private GameController gameController = new GameController();

    public MainCotroller() {
        this.startPanel.setVisible(true);
        this.startPanel.setLocation(250, 100);
    }

    public static void main(String[] args) {
        new MainCotroller();
    }

    // Hier findet die Steuerung der Klassen statt
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("start_file".equals(action)) {
            // Datei Bearbeitung
        } else if ("start_quiz".equals(action)) {
            // Quiz Modus
        } else if ("start_game".equals(action)) {
            gameController.startGame(); // Spiel starten
        } else if ("start_exit".equals(action)) {
            startPanel.close();
        }
    }
}

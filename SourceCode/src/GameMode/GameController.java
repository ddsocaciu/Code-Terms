package GameMode;
import Main.MainCotroller;
import Startmenue.StartPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Steuert das Hangman-Spiel mit den Buchstaben-Buttons.
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-27
 */
public class GameController implements ActionListener {
    private final GameModel gameModel;
    private GamePanel gamePanel;

    public GameController() {
        this.gameModel = new GameModel();
    }

    /**
     * Das Spiel wird gestartet
     *
     */
    public void startGame() {
        try {
            // Fragen laden
            gameModel.loadQuestions("Questions_Answer_QuizGame.txt");
            gameModel.selectRandomQuestion();

            // UI erstellen
            JFrame frame = new JFrame("Hangman Game");
            this.gamePanel = new GamePanel(gameModel, this); // Übergibt den Controller
            frame.add(gamePanel);

            frame.setSize(2000,2000);
            frame.setMinimumSize(new Dimension(800, 1200)); // Mindestgröße
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Sorgt dafür das die ganze Bildschirmfläche verwendet wird
            frame.setLocation(1, 10);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Fragen: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String letter = e.getActionCommand();
        gamePanel.disableLetterButton(letter);
        gamePanel.processGuess(letter);
        if("zurueck_gamepanel".equals(action)) {
            gamePanel.setVisible(false);
            gamePanel.getTopLevelAncestor().setVisible(false); // Versteckt das Fenster
            new MainCotroller();

        }
    }

}
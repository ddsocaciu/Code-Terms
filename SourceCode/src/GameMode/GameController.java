package GameMode;

import javax.swing.*;
import java.io.IOException;

/**
 * Steuert das Hangman Game
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-27
 */
public class GameController {
    private final GameModel gameModel;

    public GameController() {
        this.gameModel = new GameModel();
    }

    public void startGame() {
        try {
            // Fragen laden
            gameModel.loadQuestions("C:\\Users\\jades\\Documents\\GitHub\\ITP_Projekt\\SourceCode\\src\\FileSource\\Questions_Answer _QuizGame");

            // Zufällige Frage auswählen
            gameModel.selectRandomQuestion();

            // Fenster erstellen
            if (gameModel != null) {
                JFrame frame = new JFrame("Hangman Game");
                GamePanel gamePanel = null;
                try {
                    gamePanel = new GamePanel(gameModel);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                }
                frame.add(gamePanel);
                frame.setSize(400, 300);
                frame.setLocation(250, 100);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Fragen: " + e.getMessage());
        }
    }
}

package GameMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Steuert das Hangman-Spiel und verarbeitet Benutzerinteraktionen (siehe dazu Methode actionPerformed).
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

    public void startGame() {
        try {
            // Fragen laden
            gameModel.loadQuestions("Questions_Answer_QuizGame.txt");
            gameModel.selectRandomQuestion();

            JFrame frame = new JFrame("Hangman Game");
            this.gamePanel = new GamePanel(gameModel, this); // Übergibt den Controller
            frame.add(gamePanel);
            frame.setSize(400, 300);
            frame.setLocation(250, 100);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Fragen: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if ("submit".equals(action)) {
            String guess = gamePanel.getInputText();
            gamePanel.processGuess(guess);
        }
    }
}

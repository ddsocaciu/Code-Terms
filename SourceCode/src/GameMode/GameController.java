package GameMode;

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

    public void startGame() {
        try {
            // Fragen laden
            gameModel.loadQuestions("Questions_Answer_QuizGame.txt");
            gameModel.selectRandomQuestion();

            // UI erstellen
            JFrame frame = new JFrame("Hangman Game");
            this.gamePanel = new GamePanel(gameModel, this); // Übergibt den Controller
            frame.add(gamePanel);
            frame.setMinimumSize(new Dimension(1300, 700));
            frame.setSize(1900, 1400);
            frame.setLocation(1, 10);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Fragen: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String letter = e.getActionCommand();
        gamePanel.disableLetterButton(letter);
        gamePanel.processGuess(letter);
    }
}

package GameMode;

import javax.swing.*;
import java.awt.*;

/**
 * Diese Klasse stellt die UI des Hangman-Games dar.
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-27
 */
public class GamePanel extends JPanel {
    private final GameModel gameModel;
    private final JTextField inputField;
    private final JLabel questionLabel;
    private final JLabel wordStateLabel;
    private String currentWordState;
    private String currentAnswer;

    public GamePanel(GameModel gameModel, GameController controller) {
        if (gameModel == null || controller == null) {
            throw new IllegalArgumentException("GameModel und Controller dürfen nicht null sein");
        }

        this.gameModel = gameModel;
        this.currentAnswer = gameModel.getCurrentAnswer();
        this.currentWordState = "_".repeat(currentAnswer.length());

        setLayout(new BorderLayout());

        // Fragenanzeige
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionLabel = new JLabel("Frage: " + gameModel.getCurrentQuestion());
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        // Wortstatus-Anzeige
        wordStateLabel = new JLabel("Wort: " + currentWordState);
        questionPanel.add(wordStateLabel, BorderLayout.CENTER);

        add(questionPanel, BorderLayout.NORTH);

        // Eingabefeld und Button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputField = new JTextField(5);
        JButton submitButton = new JButton("Rate");
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(controller); // Controller als Listener hinzufügen
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.SOUTH);
    }

    public String getInputText() {
        return inputField.getText().trim().toLowerCase();
    }

    public void processGuess(String guess) {
        if (guess.length() != 1) {
            JOptionPane.showMessageDialog(this, "Bitte nur einen Buchstaben eingeben!");
            return;
        }

        boolean correctGuess = false;
        StringBuilder updatedWordState = new StringBuilder(currentWordState);

        for (int i = 0; i < currentAnswer.length(); i++) {
            if (currentAnswer.charAt(i) == guess.charAt(0) && currentWordState.charAt(i) == '_') {
                updatedWordState.setCharAt(i, guess.charAt(0));
                correctGuess = true;
            }
        }

        if (correctGuess) {
            currentWordState = updatedWordState.toString();
            wordStateLabel.setText("Wort: " + currentWordState);

            if (!currentWordState.contains("_")) {
                int playAgain = JOptionPane.showConfirmDialog(this, "Du hast das Wort erraten! Nochmal spielen?", "Victory", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.YES_OPTION) {
                    restartGame();
                }
            }
        } else {
            gameModel.incrementWrongGuesses();
            repaint();

            if (gameModel.isGameOver()) {
                JOptionPane.showMessageDialog(this, "Game Over! Das korrekte Wort war: " + currentAnswer);
                int playAgain = JOptionPane.showConfirmDialog(this, "Nochmal spielen?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.YES_OPTION) {
                    restartGame();
                }
            }
        }
    }

    private void restartGame() {
        gameModel.resetGame();
        currentAnswer = gameModel.getCurrentAnswer();
        currentWordState = "_".repeat(currentAnswer.length());
        questionLabel.setText("Frage: " + gameModel.getCurrentQuestion());
        wordStateLabel.setText("Wort: " + currentWordState);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameModel != null) {
            gameModel.draw(g);
        }
    }
}

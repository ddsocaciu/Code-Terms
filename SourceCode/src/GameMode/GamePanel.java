package GameMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse stellt die UI des Hangman Games dar
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-27
 */
public class GamePanel extends JPanel {
    private final GameModel gameModel;
    private String currentWordState;
    private String currentAnswer;
    private final JLabel questionLabel;
    private final JLabel wordStateLabel;
    private final JTextField inputField;
    private final JButton submitButton;

    public GamePanel(GameModel gameModel) {
        if (gameModel == null) {
            throw new IllegalArgumentException("GameModel darf nicht null sein");
        }
        this.gameModel = gameModel;
        this.currentAnswer = gameModel.getCurrentAnswer();
        this.currentWordState = "_".repeat(currentAnswer.length());

        setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BorderLayout());
        questionLabel = new JLabel("Frage: " + gameModel.getCurrentQuestion());
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        wordStateLabel = new JLabel("Wort: " + currentWordState);
        questionPanel.add(wordStateLabel, BorderLayout.CENTER);

        add(questionPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputField = new JTextField(5);
        submitButton = new JButton("Rate");
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess(inputField.getText().trim().toLowerCase());
            }
        });
    }

    private void processGuess(String guess) {
        if (guess.length() != 1) {
            JOptionPane.showMessageDialog(this, "Bitte nur einen Buchstaben!!!");
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
                int playAgain = JOptionPane.showConfirmDialog(this, "You guessed the word! Play again?", "Victory", JOptionPane.YES_NO_OPTION);
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

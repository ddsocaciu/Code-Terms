package GameMode;

import Main.MainCotroller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UI für das Hangman-Spiel.
 *
 * @author Maximilian
 * @version 2024-12-27
 */
public class GamePanel extends JPanel {
    private final GameModel gameModel;
    private final GameController controller;  // Hier wird der Controller gespeichert
    private final JLabel questionLabel;
    private final JLabel wordStateLabel;
    private final JPanel drawingPanel;
    private final JPanel buttonsPanel;
    private final List<JButton> letterButtons;
    private String currentWordState;
    private String currentAnswer;
    private JButton startMenueButton;

    public GamePanel(GameModel gameModel, GameController controller) {
        if (gameModel == null || controller == null) {
            throw new IllegalArgumentException("GameModel und Controller dürfen nicht null sein");
        }

        this.gameModel = gameModel;
        this.controller = controller;  // Controller speichern
        this.currentAnswer = gameModel.getCurrentAnswer();
        this.currentWordState = "_".repeat(currentAnswer.length());
        this.letterButtons = new ArrayList<>();

        // Nur GridLayout
        setLayout(new GridLayout(4, 1));  // 4 Reihen, da wir 3 Abschnitte haben: Frage, Zeichnung und Buchstaben, Menü

        // Frage anzeigen
        questionLabel = new JLabel("Frage: " + gameModel.getCurrentQuestion(), SwingConstants.CENTER);
        add(questionLabel);

        // Wortstatus anzeigen
        wordStateLabel = new JLabel("Wort: " + currentWordState, SwingConstants.CENTER);
        add(wordStateLabel);

        // Zeichen-Panel
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                gameModel.draw(g, getWidth(), getHeight()); // Fragt hier dynamisch Breite & Höhe ab
            }
        };

        add(drawingPanel);

        // Buchstaben-Buttons-Panel
        buttonsPanel = new JPanel(new GridLayout(3, 9));  // Ein GridLayout mit 3 Zeilen und 9 Spalten für die Buchstaben
        createLetterButtons();  // Buchstaben-Buttons hinzufügen
        add(buttonsPanel);
    }

    /**
     * Hier werden die Buttons erstellt
     */
    private void createLetterButtons() {
        buttonsPanel.removeAll();
        letterButtons.clear();

        // Buchstaben von A bis Z erstellen
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(String.valueOf(c));
            button.setActionCommand(String.valueOf(c).toLowerCase());
            button.addActionListener(controller);
            letterButtons.add(button);
            buttonsPanel.add(button);
        }

        // Button für "Zurück zum Hauptmenü"
        startMenueButton = new JButton("Zurueck zum Hauptmenue");
        startMenueButton.setActionCommand("zurueck_gamepanel");
        startMenueButton.addActionListener(controller);
        buttonsPanel.add(startMenueButton);

        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    /**
     * Hier werden die Buttons deaktiviert
     *
     * @param letter
     */
    public void disableLetterButton(String letter) {
        for (JButton button : letterButtons) {
            if (button.getText().equalsIgnoreCase(letter)) {
                button.setEnabled(false);
                break;
            }
        }
    }

    /**
     * Hier werden die Versuche verarbeitet
     *
     * @param guess Versuchter Buchstabe
     */
    public void processGuess(String guess) {
        if (guess.length() != 1) {
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
            drawingPanel.repaint();

            if (gameModel.isGameOver()) {
                JOptionPane.showMessageDialog(this, "Game Over! Das korrekte Wort war: " + currentAnswer);
                int playAgain = JOptionPane.showConfirmDialog(this, "Nochmal spielen?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.YES_OPTION) {
                    restartGame();
                }
            }
        }
    }

    /**
     * Hier wird das Spiel zurueckgesetzt
     */
    public void restartGame() {
        gameModel.resetGame();
        currentAnswer = gameModel.getCurrentAnswer();
        currentWordState = "_".repeat(currentAnswer.length());
        questionLabel.setText("Frage: " + gameModel.getCurrentQuestion());  // Frage zurücksetzen
        wordStateLabel.setText("Wort: " + currentWordState);
        drawingPanel.repaint();                                             // Zeichnung zurücksetzen
        createLetterButtons();                                              // Buttons neu erstellen
    }
}

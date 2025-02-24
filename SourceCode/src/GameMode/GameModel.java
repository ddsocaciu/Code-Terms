package GameMode;

import FileSource.FileLoader;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

/**
 * Diese Klasse stellt die Logik des Hangman Games dar.
 *
 * @author Maximilian Mahrhofer
 * @version 2024-12-27
 */
public class GameModel {
    private int wrongGuesses = 0;
    private final Color color = Color.BLACK;
    private String[][] questions;
    private String[] currentQA;
    private int questionCount;
    private final FileLoader fileLoader = new FileLoader();

    /**
     * Hinweis: Beschränkungen sind anpassbar
     *
     */
    public GameModel() {
        this.questions = new String[2][50]; // Maximale Anzahl von Fragen (50).
        this.currentQA = new String[2];    // Initialisierung für eine Frage.
        this.questionCount = 0;           // Anzahl der geladenen Fragen.
    }

    /**
     * Hier werden die Fragen aus der Datei geladen
     * (Verweis auf gleiche Methode in FileLoader Klasse)
     *
     * @param filePath
     * @throws IOException
     */
    public void loadQuestions(String filePath) throws IOException {
        questions = fileLoader.loadFragen(filePath);
        questionCount = 0;

        for (int i = 0; i < questions[0].length; i++) {
            if (questions[0][i] != null && questions[1][i] != null) {
                questionCount++;
            }
        }

        if (questionCount == 0) {
            throw new IOException("Keine Fragen gefunden oder Fehler beim Laden der Fragen.");
        }
    }

    /**
     * selectRandomQuestion wählt eine zufällige Frage aus der Array Liste aus
     *
     */
    public void selectRandomQuestion() {
        if (questionCount == 0) {
            currentQA = new String[]{"Keine Frage Verfügbar", ""};
            return;
        }

        Random rand = new Random();
        int index = rand.nextInt(questionCount);
        currentQA = new String[]{questions[0][index], questions[1][index]};
    }

    /**
     * Hier wird die derzeitige Frage zurueckgegeben
     *
     * @return
     */
    public String getCurrentQuestion() {
        return currentQA[0] != null ? currentQA[0] : "Keine Frage Verfügbar";
    }

    /**
     * Hier wird derzeitige Antwort zurueck gegeben
     *
     * @return Gibt die Antwort zrueck
     */
    public String getCurrentAnswer() {
        return currentQA[1] != null ? currentQA[1].toLowerCase() : "";
    }

    /**
     * Hier werden die falschen Versuche addiert
     */
    public void incrementWrongGuesses() {
        if (wrongGuesses < 6) {
            wrongGuesses++;
        }
    }

    /**
     * isGameOver prüft ob die Anzahl der Versuche die maximale Versuchanzahl übersteigt
     *
     * @return wrongGuesses
     */
    public boolean isGameOver() {
        return wrongGuesses >= 6;
    }

    /**
     * resetGame sort dafür, dass das Spiel neu gestartet wird (neue Frage)
     */
    public void resetGame() {
        wrongGuesses = 0;
        selectRandomQuestion();
    }

    /**
     * Hier wird das Hangman-Game gezeichnet
     *
     * @param g
     * @param panelWidth
     */
    public void draw(Graphics g, int panelWidth) {
        int offsetX = (panelWidth / 2) - 125;

        g.setColor(color);
        g.drawLine(offsetX + 50, 200, offsetX + 150, 200);
        g.drawLine(offsetX + 100, 200, offsetX + 100, 50);
        g.drawLine(offsetX + 100, 50, offsetX + 200, 50);
        g.drawLine(offsetX + 200, 50, offsetX + 200, 70);

        if (wrongGuesses > 0) g.drawOval(offsetX + 185, 70, 30, 30);
        if (wrongGuesses > 1) g.drawLine(offsetX + 200, 100, offsetX + 200, 150);
        if (wrongGuesses > 2) g.drawLine(offsetX + 200, 110, offsetX + 180, 130);
        if (wrongGuesses > 3) g.drawLine(offsetX + 200, 110, offsetX + 220, 130);
        if (wrongGuesses > 4) g.drawLine(offsetX + 200, 150, offsetX + 180, 180);
        if (wrongGuesses > 5) g.drawLine(offsetX + 200, 150, offsetX + 220, 180);
    }
}

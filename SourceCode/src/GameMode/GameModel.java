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

    public GameModel() {
        this.questions = new String[2][50]; // Maximale Anzahl von Fragen (50).
        this.currentQA = new String[2];    // Initialisierung für eine Frage.
        this.questionCount = 0;           // Anzahl der geladenen Fragen.
    }

    // Lade Fragen aus einer Datei.
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

    // Wählt eine zufällige Frage aus.
    public void selectRandomQuestion() {
        if (questionCount == 0) {
            currentQA = new String[]{"Keine Frage Verfügbar", ""};
            return;
        }

        Random rand = new Random();
        int index = rand.nextInt(questionCount);
        currentQA = new String[]{questions[0][index], questions[1][index]};
    }

    public String getCurrentQuestion() {
        return currentQA != null && currentQA[0] != null ? currentQA[0] : "Keine Frage Verfügbar";
    }

    public String getCurrentAnswer() {
        return currentQA != null && currentQA[1] != null ? currentQA[1].toLowerCase() : "";
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void incrementWrongGuesses() {
        if (wrongGuesses < 6) {
            wrongGuesses++;
        }
    }

    public boolean isGameOver() {
        return wrongGuesses >= 6;
    }

    public void resetGame() {
        wrongGuesses = 0;
        selectRandomQuestion();
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(50, 200, 150, 200); // Zeichnet den Boden.
        g.drawLine(100, 200, 100, 50); // Zeichnet die Stange.
        g.drawLine(100, 50, 200, 50);  // Zeichnet die obere Basis.
        g.drawLine(200, 50, 200, 70);  // Zeichnet die Schlinge.

        if (wrongGuesses > 0) g.drawOval(185, 70, 30, 30); // Der Kopf.
        if (wrongGuesses > 1) g.drawLine(200, 100, 200, 150); // Der Körper.
        if (wrongGuesses > 2) g.drawLine(200, 110, 180, 130); // Linker Arm.
        if (wrongGuesses > 3) g.drawLine(200, 110, 220, 130); // Rechter Arm.
        if (wrongGuesses > 4) g.drawLine(200, 150, 180, 180); // Linkes Bein.
        if (wrongGuesses > 5) g.drawLine(200, 150, 220, 180); // Rechtes Bein.
    }
}

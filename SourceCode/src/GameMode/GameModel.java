package GameMode;

import FileSource.FileLoader;

import java.awt.*;
import java.io.*;
import java.util.Random;

/**
 * Diese Klasse stellt die Logik des Hangman Games dar
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
    FileLoader fileLoader = new FileLoader();

    public GameModel() {
        this.questions = new String[100][2]; // Maximale Anzahl von Fragen (100)
        this.currentQA = new String[2]; // Initialisierung für eine Frage
        this.questionCount = 0; //  Frageanzahl
    }

    // Lade Fragen aus der Datei
    public void loadQuestions(String filePath) throws IOException {

        //questions = fileLoader.loadQuestions(filePath);
        questionCount = 0;


        for (String[] question : questions) {
            if (question[0] != null && question[1] != null) {
                questionCount++;
            }
        }


        if (questionCount == 0) {
            throw new IOException("Keine Fragen gefunden oder Fehler beim Laden der Fragen.");
        }
    }



    // Zufällige Frage auswählen
    public void selectRandomQuestion() {
        // Sicherstellen, dass Fragen existieren
        if (questionCount == 0) {
            currentQA = new String[]{"Keine Frage Verfügbar", ""};
            return;
        }

        // Zufällige Auswahl einer Frage
        Random rand = new Random();
        currentQA = questions[rand.nextInt(questionCount)]; // Frage aus den geladenen Fragen auswählen
    }

    // Frage abrufen
    public String getCurrentQuestion() {
        return currentQA != null && currentQA[0] != null ? currentQA[0] : "Keine Frage Verfügbar";
    }

    // Antwort abrufen
    public String getCurrentAnswer() {
        return currentQA != null && currentQA[1] != null ? currentQA[1].toLowerCase() : "";
    }

    // Fehlerhafte Versuche erhöhen
    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void incrementWrongGuesses() {
        if (wrongGuesses < 6) {
            wrongGuesses++;
        }
    }

    // Spiel beendet?
    public boolean isGameOver() {
        return wrongGuesses >= 6;
    }

    // Spiel zurücksetzen
    public void resetGame() {
        wrongGuesses = 0;
        selectRandomQuestion();
    }

    // Zeichnen des Spiels (hängende Person)
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(50, 200, 150, 200); // Zeichnet den Boden
        g.drawLine(100, 200, 100, 50); // Zeichnet die Stange
        g.drawLine(100, 50, 200, 50);  // Zeichnet die obere Basis
        g.drawLine(200, 50, 200, 70);  // Zeichnet die Schlinge

        if (wrongGuesses > 0) g.drawOval(185, 70, 30, 30); // Der Kopf
        if (wrongGuesses > 1) g.drawLine(200, 100, 200, 150); // Der Körper
        if (wrongGuesses > 2) g.drawLine(200, 110, 180, 130); // Linker Arm
        if (wrongGuesses > 3) g.drawLine(200, 110, 220, 130); // Rechter Arm
        if (wrongGuesses > 4) g.drawLine(200, 150, 180, 180); // Linkes Bein
        if (wrongGuesses > 5) g.drawLine(200, 150, 220, 180); // Rechtes Bein
    }
}

package GameMode;

import java.awt.*;
import java.io.*;
import java.util.Random;

/**
 * Diese Klasse stellt die Logik des hangman Games dar
 */
public class GameModel {
    private int wrongGuesses = 0;
    private final Color color = Color.BLACK;
    private String[][] questions; // 2D-Array für Fragen und Antworten
    private String[] currentQA; // Aktuelle Frage und Antwort
    private int questionCount; // Anzahl der geladenen Fragen

    public GameModel() {
        this.questions = new String[100][2]; // Maximale Anzahl von Fragen (100)
        this.currentQA = new String[2]; // Initialisierung für eine Frage
        this.questionCount = 0; // Initialisieren der Frageanzahl
    }

    // Lade Fragen aus der Datei
    public void loadQuestions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String question = null;
        String answer = null;

        // Lesen der Datei und Erstellen der Fragen-Antworten Paare
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Frage:")) {
                question = line.substring(6).trim(); // Frage extrahieren
            } else if (line.startsWith("Antwort:")) {
                answer = line.substring(8).trim(); // Antwort extrahieren
            } else if (line.startsWith("**********")) {
                // Sobald ein Frage-Antwort Paar vollständig ist, in Array speichern
                if (question != null && answer != null) {
                    questions[questionCount][0] = question;
                    questions[questionCount][1] = answer;
                    questionCount++; // Zähler erhöhen, wenn eine Frage geladen wurde
                    question = null;
                    answer = null;
                }
            }
        }
        reader.close();

        // Überprüfen, ob Fragen erfolgreich geladen wurden
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

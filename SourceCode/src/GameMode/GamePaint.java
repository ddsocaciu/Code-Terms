package GameMode;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePaint {
    private int wrongGuesses = 0;
    private final Color color = Color.BLACK;
    private List<String[]> questions;
    private String[] currentQA;

    public GamePaint() {
        try {
            this.questions = loadQuestions("C:\\Users\\jades\\Documents\\GitHub\\ITP_Projekt\\SourceCode\\src\\FileSource\\Questions_Answer _QuizGame");
            selectRandomQuestion();
        } catch (IOException e) {
            this.questions = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);


        g.drawLine(50, 200, 150, 200); // Zeichnet den Boden
        g.drawLine(100, 200, 100, 50); // Zeichnet die Stang
        g.drawLine(100, 50, 200, 50);  // Zeichnen die obere Basis
        g.drawLine(200, 50, 200, 70);  // Zeichnet die Schlinge

        if (wrongGuesses > 0) g.drawOval(185, 70, 30, 30); // Der Kopf
        if (wrongGuesses > 1) g.drawLine(200, 100, 200, 150); // Der Körper
        if (wrongGuesses > 2) g.drawLine(200, 110, 180, 130); // Linker Arm
        if (wrongGuesses > 3) g.drawLine(200, 110, 220, 130); // Rechter Arm
        if (wrongGuesses > 4) g.drawLine(200, 150, 180, 180); // Linkes Bein
        if (wrongGuesses > 5) g.drawLine(200, 150, 220, 180); // Rechtes Bein
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

    public void selectRandomQuestion() {
        if (questions != null && !questions.isEmpty()) {
            Random rand = new Random();
            currentQA = questions.get(rand.nextInt(questions.size()));
        }
    }

    public String getCurrentQuestion() {
        return currentQA != null ? currentQA[0] : "Keine Frage Verfügbar";
    }

    public String getCurrentAnswer() {
        return currentQA != null ? currentQA[1].toLowerCase() : "";
    }

    public List<String[]> loadQuestions(String filePath) throws IOException {
        List<String[]> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String question = null;
            String answer = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Frage:")) {
                    question = line.substring(6).trim();
                } else if (line.startsWith("Antwort:")) {
                    answer = line.substring(8).trim();
                } else if (line.startsWith("**********")) {
                    if (question != null && answer != null) {
                        questions.add(new String[]{question, answer});
                        question = null;
                        answer = null;
                    }
                }
            }
        }
        return questions;
    }
}

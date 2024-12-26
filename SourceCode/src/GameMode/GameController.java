package GameMode;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class GameController {
    private GamePaint gamePaint;

    public GameController() {
        this.gamePaint = new GamePaint();
    }

    public void startGame() {
        try {
            // Hier werden die Fragen geladen
            List<String[]> questions = gamePaint.loadQuestions("C:\\Users\\jades\\Documents\\GitHub\\ITP_Projekt\\SourceCode\\src\\FileSource\\Questions_Answer _QuizGame");

            // Das Spieler Fenster wird hier erstellt
            JFrame frame = new JFrame("Hangman Game");
            GamePanel gamePanel = new GamePanel(gamePaint);

            frame.add(gamePanel);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Laden der Fragen: " + e.getMessage());
        }
    }
}

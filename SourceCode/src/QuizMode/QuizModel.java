package QuizMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import FileSource.FileLoader; // Importiere die FileLoader-Klasse
import Main.MainCotroller;
public class QuizModel extends JFrame {
    private JLabel frageLabel;
    private JTextField antwortFeld;
    private JButton weiterButton;
    private JLabel ergebnisLabel;
    private String[][] fragenAntworten; // Die Fragen und Antworten
    private int aktuelleFrageIndex = 0;
    private int score = 0;
    private Random random;

    public QuizModel() {
        // Fragen über die FileLoader-Klasse laden
        FileLoader loader = new FileLoader();
        this.fragenAntworten = loader.loadFragen("Questions_Answer_QuizGame.txt"); // Hier den Pfad zur Datei anpassen

        if (this.fragenAntworten[0][0] == null) {
            JOptionPane.showMessageDialog(this, "Keine Fragen gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // GUI Setup
        this.setTitle("Java Quiz");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));

        this.frageLabel = new JLabel();
        this.antwortFeld = new JTextField();
        this.weiterButton = new JButton("Weiter");
        this.ergebnisLabel = new JLabel("Punkte: " + this.score);

        // ActionListener für den Button hinzufügen
        this.weiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAntwort = antwortFeld.getText().trim();
                String richtigeAntwort = fragenAntworten[1][aktuelleFrageIndex]; // Zugriff auf die Antwort

                if (userAntwort.equalsIgnoreCase(richtigeAntwort)) {
                    score++;
                }

                ergebnisLabel.setText("Punkte: " + score);

                if (aktuelleFrageIndex < fragenAntworten[0].length - 1 && fragenAntworten[0][aktuelleFrageIndex + 1] != null) {
                    aktuelleFrageIndex++; // Zur nächsten Frage gehen
                } else {
                    // Quiz beendet
                    JOptionPane.showMessageDialog(QuizModel.this, "Quiz beendet! Dein Score: " + score, "Quiz Ende", JOptionPane.INFORMATION_MESSAGE);
                    QuizModel.this.dispose(); // Fenster schließen


                    MainCotroller mainController = new MainCotroller();

                    return; // Methode beenden
                }

                frageLabel.setText(fragenAntworten[0][aktuelleFrageIndex]);
                antwortFeld.setText("");
            }
        });

        this.add(frageLabel);
        this.add(antwortFeld);
        this.add(weiterButton);
        this.add(ergebnisLabel);

        this.random = new Random();
        frageLabel.setText(this.fragenAntworten[0][this.aktuelleFrageIndex]); // Erste Frage anzeigen
        this.setVisible(true);
    }
}

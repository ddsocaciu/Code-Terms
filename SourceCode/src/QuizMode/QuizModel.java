package QuizMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import FileSource.FileLoader; // Importiere die FileLoader-Klasse

public class QuizModel extends JFrame {
    private JLabel frageLabel;
    private JTextField antwortFeld;
    private JButton weiterButton;
    private JLabel ergebnisLabel;
    private String[][] fragenAntworten; // Die Fragen und Antworten
    private int aktuelleFrageIndex;
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
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 1));

        this.frageLabel = new JLabel();
        this.antwortFeld = new JTextField();
        this.weiterButton = new JButton("Weiter");
        this.ergebnisLabel = new JLabel("Punkte: " + this.score);

        this.weiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ueberpruefeAntwort();
            }
        });

        this.add(this.frageLabel);
        this.add(this.antwortFeld);
        this.add(this.weiterButton);
        this.add(this.ergebnisLabel);

        this.random = new Random();
        this.naechsteFrage();
        this.setVisible(true);
    }

    private void ueberpruefeAntwort() {
        String userAntwort = this.antwortFeld.getText().trim();
        String richtigeAntwort = this.fragenAntworten[1][this.aktuelleFrageIndex]; // Zugriff auf die Antwort

        if (userAntwort.equalsIgnoreCase(richtigeAntwort)) {
            this.score++;
        }

        this.ergebnisLabel.setText("Punkte: " + this.score);
        this.naechsteFrage();
    }

    private void naechsteFrage() {
        do {
            this.aktuelleFrageIndex = random.nextInt(this.fragenAntworten[0].length);
        } while (this.fragenAntworten[0][this.aktuelleFrageIndex] == null); // Stelle sicher, dass die Frage nicht leer ist

        this.frageLabel.setText(this.fragenAntworten[0][this.aktuelleFrageIndex]); // Zugriff auf die Frage
        this.antwortFeld.setText(""); // Eingabefeld leeren
    }

    public static void main(String[] args) {
        new QuizModel();
    }
}

package QuizMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import FileSource.FileLoader;
import Main.MainCotroller;

public class QuizModel extends JFrame {
    JLabel bildLabel;
    JLabel frageLabel;
    JTextField antwortFeld;
    JButton weiterButton;
    JButton hauptmenueButton; // Neuer Button fürs Hauptmenü
    JLabel ergebnisLabel;
    String[][] fragenAntworten;
    int aktuelleFrageIndex = 0;
    int score = 0;

    public QuizModel() {
        // Fragen aus Datei laden
        FileLoader loader = new FileLoader();
        fragenAntworten = loader.loadFragen("Questions_Answer_QuizGame.txt");

        if (fragenAntworten == null || fragenAntworten.length < 2 || fragenAntworten[0].length == 0) {
            JOptionPane.showMessageDialog(this, "Keine Fragen gefunden!", "Fehler", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // GUI Setup mit GridLayout (6 Zeilen, 1 Spalte)
        setTitle("Java Quiz");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1)); // Eine Zeile mehr für den Hauptmenü-Button

        bildLabel = new JLabel("", SwingConstants.CENTER);
        frageLabel = new JLabel("", SwingConstants.CENTER);
        antwortFeld = new JTextField();
        weiterButton = new JButton("Weiter");
        hauptmenueButton = new JButton("Hauptmenü"); // Neuer Button fürs Hauptmenü
        ergebnisLabel = new JLabel("Punkte: " + score, SwingConstants.CENTER);

        weiterButton.setForeground(Color.WHITE);
        weiterButton.setBackground(Color.BLACK);
        weiterButton.setFocusPainted(false);

        hauptmenueButton.setForeground(Color.WHITE);
        hauptmenueButton.setBackground(Color.BLACK);
        hauptmenueButton.setFocusPainted(false);

        weiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAntwort = antwortFeld.getText().trim();
                String richtigeAntwort = fragenAntworten[1][aktuelleFrageIndex];

                if (userAntwort.equalsIgnoreCase(richtigeAntwort)) {
                    score++;
                }

                ergebnisLabel.setText("Punkte: " + score);

                if (aktuelleFrageIndex < fragenAntworten[0].length - 1 && fragenAntworten[0][aktuelleFrageIndex + 1] != null) {
                    aktuelleFrageIndex++;
                } else {
                    JOptionPane.showMessageDialog(QuizModel.this, "Quiz beendet! Dein Score: " + score, "Quiz Ende", JOptionPane.INFORMATION_MESSAGE);
                    geheZumHauptmenue();
                    return;
                }

                zeigeNaechsteFrage();
            }
        });

        // Hauptmenü-Button Aktion
        hauptmenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geheZumHauptmenue();
            }
        });

        // Reihenfolge der Elemente setzen
        add(bildLabel);
        add(frageLabel);
        add(antwortFeld);
        add(weiterButton);
        add(hauptmenueButton); // Neuer Button unter "Weiter"
        add(ergebnisLabel);

        zeigeNaechsteFrage();
        setVisible(true);
    }

    public void zeigeNaechsteFrage() {
        String neueFrage = fragenAntworten[0][aktuelleFrageIndex];

        if (neueFrage.toLowerCase().endsWith(".jpg") || neueFrage.toLowerCase().endsWith(".png")) {
            try {
                URL bildUrl = new File(neueFrage).toURI().toURL();
                ImageIcon bildIcon = new ImageIcon(bildUrl);
                Image skaliertesBild = bildIcon.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
                bildLabel.setIcon(new ImageIcon(skaliertesBild));
                frageLabel.setText(""); // Keine Text-Frage, wenn Bild geladen wird
            } catch (MalformedURLException e) {
                frageLabel.setText("Fehler beim Laden des Bildes.");
                bildLabel.setIcon(null);
            }
        } else {
            frageLabel.setText(neueFrage);
            bildLabel.setIcon(null); // Kein Bild setzen, wenn normale Frage
        }

        antwortFeld.setText("");
    }

    // Methode zum Wechsel ins Hauptmenü
    private void geheZumHauptmenue() {
        dispose(); // Schließt das Quiz-Fenster
        new MainCotroller(); // Öffnet das Hauptmenü
    }
}

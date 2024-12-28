package FileSource;
import Main.MainCotroller;

import javax.swing.*;
import java.awt.*;

/**
 * INFO-PANEL für die Dateibearbeitung
 *
 * @author David Socaciu
 * @version 2024-12-28
 */
public class FileInfoPanel extends JFrame {
    public FileInfoPanel(MainCotroller mc) {
        // Fenster-Einstellungen
        this.setTitle("Infos - Dateiablage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);

        // GridLayout: 8 Zeilen, 1 Spalte (keine Abstände angegeben)
        this.setLayout(new GridLayout(7, 1));

        // Beispiel-Sätze hinzufügen
        this.add(new JLabel("INFORMATIONEN ZUR DATEIBEARBEITUNG", SwingConstants.CENTER));
        this.add(new JLabel("- Achte auf eine sinnvolle und vollstaendige Datei!", SwingConstants.CENTER));
        this.add(new JLabel("- Speichere die Datei im Default-Ordner!", SwingConstants.CENTER));
        this.add(new JLabel("- Du kannst hoechstens 50 Fragen in deinem Pool haben", SwingConstants.CENTER));
        this.add(new JLabel("- PLATZHALTER", SwingConstants.CENTER));
        this.add(new JLabel("- PLATZHALTER", SwingConstants.CENTER));

        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        // Buttons direkt hinzufügen
        buttonPanel.add(new JButton("Weiter"));
        buttonPanel.add(new JButton("Beenden"));

        JLabel x = new JLabel("X");
        this.add(buttonPanel);

        // Fenster sichtbar machen
        this.setVisible(true);
    }
}

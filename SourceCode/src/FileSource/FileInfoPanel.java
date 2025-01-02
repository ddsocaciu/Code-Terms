package FileSource;
import Main.MainCotroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

        this.setLayout(new GridLayout(7, 1));

        JLabel ueberschrift = new JLabel("INFORMATIONEN ZUR DATEIBEARBEITUNG");
        ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));
        ueberschrift.setForeground(Color.RED);
        ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(ueberschrift);
        this.add(new JLabel("- Achte auf eine sinnvolle und vollstaendige Datei!", SwingConstants.CENTER));
        this.add(new JLabel("- Speichere die Datei im Default-Ordner!", SwingConstants.CENTER));
        this.add(new JLabel("- Du kannst hoechstens 50 Fragen in deinem Pool haben", SwingConstants.CENTER));
        this.add(new JLabel("- Jede Frage muss mit einem 'Frage:' und jede Antwort mit einem 'Antwort:' gekennzeichnet" +
                " werden!", SwingConstants.CENTER));
        this.add(new JLabel("- Um einen Fragepool aus dem Default-Ordner zu laden, gib den Dateinamen ein", SwingConstants.CENTER));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton weiter = new JButton("Weiter");
        weiter.setActionCommand("weiter_fileinfopanel");
        weiter.addActionListener(mc);
        buttonPanel.add(weiter);

        JButton beenden = new JButton("Beenden");
        beenden.setActionCommand("beenden_fileinfopanel");
        beenden.addActionListener(mc);
        buttonPanel.add(beenden);

        this.add(buttonPanel);

        // Fenster sichtbar machen
        this.setVisible(true);
    }
}

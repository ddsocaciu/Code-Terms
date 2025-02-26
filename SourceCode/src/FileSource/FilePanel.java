package FileSource;
import Main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * FilePanel für die Dateiverwaltung
 *
 * @author David Socaciu
 * @version 2025-01-02
 */
public class FilePanel extends JFrame {
    private JTextArea textArea, nameArea;
    public FilePanel(MainCotroller mc) {
        this.setTitle("Dateiverwaltung");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new GridLayout(5,1));

        JLabel ueberschrift = new JLabel("DATEIVERWALTUNG");
        ueberschrift.setFont(new Font("Arial", Font.BOLD, 20));
        ueberschrift.setForeground(Color.BLACK);
        ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(ueberschrift);

        //PLACEHOLDER
        textArea = new JTextArea();
        textArea.setEditable(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        JButton bt1 = new JButton("Fragepool laden");
        bt1.setActionCommand("laden_filepanel");
        bt1.addActionListener(mc);
        bt1.setForeground(Color.WHITE);
        bt1.setBackground(Color.BLACK);
        bt1.setFocusPainted(false);
        panel.add(bt1);

        JButton bt2 = new JButton("Fragepool speichern");
        bt2.setActionCommand("speichern_filepanel");
        bt2.addActionListener(mc);
        bt2.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        bt2.setFocusPainted(false);
        panel.add(bt2);

        JButton bt3 = new JButton("Fragepool löschen");
        bt3.setActionCommand("delete_filepanel");
        bt3.addActionListener(mc);
        bt3.setForeground(Color.WHITE);
        bt3.setBackground(Color.BLACK);
        bt3.setFocusPainted(false);
        panel.add(bt3);

        this.add(panel);
        nameArea = new JTextArea();
        nameArea.setEditable(true);
        nameArea.setText("GIB HIER DEN DATEINAMEN DER ZUR LADENEN DATEI");
        this.add(nameArea);

        JButton back = new JButton("Zurück zum Hauptmenü");
        back.setActionCommand("zurueck_filepanel");
        back.addActionListener(mc);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        this.add(back);


    }
    public void deleteFragen(){
        textArea.setText("");
    }
    public void loadFragen(String pool){
        textArea.setText(pool);
    }
    public String getText(){
        return textArea.getText();
    }
    public String getName(){
        return nameArea.getText();
    }



}

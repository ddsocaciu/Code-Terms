package Startmenue;
import Main.*;
import javax.swing.*;
import java.awt.*;

/**
 * Frontend für das Startmenü
 */
public class PanelStart extends JFrame {
    private JButton fileButton, quizButton, gameButton, exitButton;

    public PanelStart(MainCotroller controller) {
        /*
            Das Frame für das Startmenü
         */
        this.setTitle("Startmenü");
        this.setSize(800, 600);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(Color.WHITE);

        /*
            Das Grid Layout mit Inhalt
         */
        setLayout(new GridLayout(3, 3, 10, 10));
        setBackground(Color.WHITE);


        /*
            Adden der Panels sowie der leeren Panels
         */

        //leer
        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setBackground(Color.WHITE);
        add(emptyPanel1);

        // Label zum waehlen des Modus
        JLabel titleLabel = new JLabel("Wähle deinen Modus:", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        add(titlePanel);

        //leer
        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setBackground(Color.WHITE);
        add(emptyPanel2);

        //leer
        JPanel emptyPanel3 = new JPanel();
        emptyPanel3.setBackground(Color.WHITE);
        add(emptyPanel3);


        // ButtonPanel und Buttons, sowie hinzufuegen der ActionListener
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        fileButton = new JButton("Datei Bearbeitung");
        quizButton = new JButton("Quiz Modus");
        gameButton = new JButton("Game Modus");
        exitButton = new JButton("Beenden");


        buttonPanel.add(fileButton);
        buttonPanel.add(quizButton);
        buttonPanel.add(gameButton);
        buttonPanel.add(exitButton);


        add(buttonPanel);


        /*
            Adden der Button ActionListener
         */
        this.fileButton.setActionCommand("start_file");
        this.quizButton.setActionCommand("start_quiz");
        this.gameButton.setActionCommand("start_game");
        this.exitButton.setActionCommand("start_exit");

        this.fileButton.addActionListener(controller);
        this.quizButton.addActionListener(controller);
        this.gameButton.addActionListener(controller);
        this.exitButton.addActionListener(controller);



        //leer
        JPanel emptyPanel4 = new JPanel();
        emptyPanel4.setBackground(Color.WHITE);
        add(emptyPanel4);

        //leer
        JPanel emptyPanel5 = new JPanel();
        emptyPanel5.setBackground(Color.WHITE);
        add(emptyPanel5);

        //leer
        JPanel emptyPanel6 = new JPanel();
        emptyPanel6.setBackground(Color.WHITE);
        add(emptyPanel6);

        //leer
        JPanel emptyPanel7 = new JPanel();
        emptyPanel7.setBackground(Color.WHITE);
        add(emptyPanel7);

    }
    public  void close() {
        System.exit(0);
    }
}

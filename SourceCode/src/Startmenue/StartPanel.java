package Startmenue;
import Main.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * Frontend für das Startmenü
 * @author Maximilian Mahrhofer & David Socaciu
 * @version 2024-12-02
 */
public class StartPanel extends JFrame {
    private JButton fileButton, quizButton, gameButton, exitButton;

    public StartPanel(MainCotroller controller) {
        /*
            Das Frame für das Startmenü
         */
        this.setTitle("Startmenü");

        //Standart Groeß
        this.setSize(800, 600);

        //Standart Position
        this.setLocation(550, 200);

        //Minimale Groeße
        this.setMinimumSize(new Dimension(600, 600));

        // Default Operationen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(Color.WHITE);

        /*
            Das Grid Layout mit Inhalt
         */
        setLayout(new GridLayout(3, 3));
        setBackground(Color.WHITE);


        /*
            Adden der Panels sowie der leeren Panels
         */
        //leer
        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setBackground(Color.WHITE);
        add(emptyPanel1);


        // Label zum waehlen des Modus
        JLabel titleLabel = new JLabel("Wähle deinen Modus", JLabel.LEFT);
        JLabel projectName = new JLabel("CodeTerms", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        projectName.setFont(new Font("Arial", Font.BOLD, 18));
        projectName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel buffer = new JLabel();
        JPanel titlePanel = new JPanel(new GridLayout(5,1));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(projectName);
        titlePanel.add(buffer);
        titlePanel.add(titleLabel);
        titlePanel.add(buffer);
        titlePanel.add(titleLabel);
        this.add(titlePanel);


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

        /**
         * Buttons - Gestaltung
         */
        fileButton.setForeground(Color.WHITE);
        fileButton.setBackground(Color.BLACK);
        fileButton.setBorderPainted(false);
        fileButton.setFocusPainted(false);

        quizButton.setForeground(Color.WHITE);
        quizButton.setBackground(Color.BLACK);
        quizButton.setBorderPainted(false);
        quizButton.setFocusPainted(false);

        gameButton.setForeground(Color.WHITE);
        gameButton.setBackground(Color.BLACK);
        gameButton.setBorderPainted(false);
        gameButton.setFocusPainted(false);

        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.BLACK);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
    }
    public  void close() {
        System.exit(0);
    }
}

package Startmenue;
import Main.*;
import javax.swing.*;
import java.awt.*;

public class PanelStart extends JFrame {

    public PanelStart(MainCotroller controller) {
        this.setTitle("Startmenü");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(Color.WHITE);


        setLayout(new GridLayout(3, 3, 10, 10));
        setBackground(Color.WHITE);

        JPanel emptyPanel1 = new JPanel();
        emptyPanel1.setBackground(Color.WHITE);
        add(emptyPanel1);

        JLabel titleLabel = new JLabel("Wähle deinen Modus:", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        add(titlePanel);

        JPanel emptyPanel2 = new JPanel();
        emptyPanel2.setBackground(Color.WHITE);
        add(emptyPanel2);


        JPanel emptyPanel3 = new JPanel();
        emptyPanel3.setBackground(Color.WHITE);
        add(emptyPanel3);



        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton fileButton = new JButton("Datei Bearbeitung");
        JButton quizButton = new JButton("Quiz Modus");
        JButton gameButton = new JButton("Game Modus");
        JButton exitButton = new JButton("Beenden");

        buttonPanel.add(fileButton);
        buttonPanel.add(quizButton);
        buttonPanel.add(gameButton);
        buttonPanel.add(exitButton);

        add(buttonPanel);

        JPanel emptyPanel4 = new JPanel();
        emptyPanel4.setBackground(Color.WHITE);
        add(emptyPanel4);

        JPanel emptyPanel5 = new JPanel();
        emptyPanel5.setBackground(Color.WHITE);
        add(emptyPanel5);



        JPanel emptyPanel6 = new JPanel();
        emptyPanel6.setBackground(Color.WHITE);
        add(emptyPanel6);

        JPanel emptyPanel7 = new JPanel();
        emptyPanel7.setBackground(Color.WHITE);
        add(emptyPanel7);

        exitButton.addActionListener(e -> System.exit(0));
    }
}

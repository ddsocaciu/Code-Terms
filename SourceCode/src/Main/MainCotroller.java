package Main;

import Startmenue.PanelStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainCotroller implements ActionListener {
    private PanelStart startPanel= new PanelStart(this);


    public MainCotroller() {
        this.startPanel.setVisible(true);
        this.startPanel.setLocation(500, 200);

    }

    public static void main(String[] args) {
        new MainCotroller();
    }

    // Hier findet die Steuerung der Klassen statt
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if ("start_file".equals(action)) {

        } else if ("start_quiz".equals(action)) {

        } else if ("start_game".equals(action)) {

        }else if ("start_exit".equals(action)) {
            startPanel.close();
        }
    }
}
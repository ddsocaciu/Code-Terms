package Main;

import Startmenue.*;

import javax.swing.*;
import java.awt.*;

public class MainCotroller {
    private PanelStart startPanel= new PanelStart(this);


    public MainCotroller() {
        this.startPanel.setVisible(true);
        this.startPanel.setLocation(400, 200);

    }

    public static void main(String[] args) {
        new MainCotroller();
    }
}
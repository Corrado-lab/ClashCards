package it.unicam.cs.mpgc.rpg119746;

import it.unicam.cs.mpgc.rpg119746.view.window.StartWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StartWindow();
        });
    }
}

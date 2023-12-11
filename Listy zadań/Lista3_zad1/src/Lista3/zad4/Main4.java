package Lista3.zad4;

import javax.swing.*;

public class Main4 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PanelWithMovingSquare panel = new PanelWithMovingSquare();
                panel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                panel.setLocationRelativeTo(null);
                panel.setVisible(true);
            }
        });
    }
}


package Lista3.zad3;
import javax.swing.*;

public class Main3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PanelWithClickCounter panel = new PanelWithClickCounter();
                panel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                panel.setLocationRelativeTo(null);
                panel.setVisible(true);
            }
        });
    }
}

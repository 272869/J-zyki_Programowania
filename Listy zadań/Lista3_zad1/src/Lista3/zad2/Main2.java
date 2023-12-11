package Lista3.zad2;
import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DrawingPanel drawingPanel = new DrawingPanel();
                drawingPanel.setLocationRelativeTo(null);
                drawingPanel.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                drawingPanel.setVisible(true);
            }
        });
    }
}

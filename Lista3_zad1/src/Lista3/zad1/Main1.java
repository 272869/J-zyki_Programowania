package Lista3.zad1;
import javax.swing.*;
public class Main1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DrawingAreaWithTextField drawingAreaWithTextField = new DrawingAreaWithTextField();
                drawingAreaWithTextField.setLocationRelativeTo(null);
                drawingAreaWithTextField.setVisible(true);
            }
        });
    }
}
package Lista3.zad5;

import javax.swing.*;

public class Main5 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame myFrame = new MyFrame();
                myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                myFrame.setLocationRelativeTo(null);
                myFrame.setVisible(true);
            }
        });
    }
}

package pl.kamilagronska;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationApp extends JFrame{
    private JPanel contentPane;
    public static JLabel jLabel;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               /* try {

                }catch () {
                }*/
                AnimationApp app = new AnimationApp();
                app.setVisible(true);
            }
        });


    }

    public AnimationApp() {
        jLabel = new JLabel("Score: 0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screen.width-600)/2, (screen.height-600)/2, 600, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //drugi panel kanwa
        jLabel.setBounds(190,530,80,23);
        contentPane.add(jLabel);
        AnimationPanel canwa = new AnimationPanel();
        canwa.setBounds(10, 11, 570, 520);
        contentPane.add(canwa);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                canwa.initialize();

            }
        });

        JButton playButton = new JButton("Graj");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canwa.startGame();

            }
        });
        playButton.setBounds(10,530,80,23);
        contentPane.add(playButton);

        JButton pauseButton = new JButton("Pauza");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canwa.pause();
            }
        });
        pauseButton.setBounds(100,530,80,23);
        contentPane.add(pauseButton);
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation extends JFrame {
    private JPanel contentPane;
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    final Simulation simulation = new Simulation();
                    simulation.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Simulation(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Road road = new Road();
        contentPane.add(road);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                road.initialize();
            }
        });

        JButton btnAdd1 = new JButton("Dodaj samochód"); //Dodanie samochodów
        btnAdd1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.addSam();
            }
        });
        btnAdd1.setBounds(10, 310, 130, 23);
        contentPane.add(btnAdd1);

        JButton btnAdd2 = new JButton("Dodaj pieszego"); //Dodanie piesi
        btnAdd2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.addPiesi();
            }
        });
        btnAdd2.setBounds(150, 310, 130, 23);
        contentPane.add(btnAdd2);
        JButton btnAnimate = new JButton("Start"); //Pokazywanje
        btnAnimate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.animate();
            }
        });
        btnAnimate.setBounds(300, 310, 80, 23);
        contentPane.add(btnAnimate);

        setSize(600,400);
        setBackground(Color.WHITE);
        setVisible(true);
    }
}
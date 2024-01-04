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
                    Simulation simulation = new Simulation();
                    simulation.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public Simulation(){
        setSize(600,400);
        setBackground(Color.WHITE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Road road = new Road();
        contentPane.add(road);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                road.initialize();
            }
        });
        JButton addCarButton = new JButton("Dodaj samochód"); //Dodanie samochodów
        addCarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.addSam();
            }
        });
        addCarButton.setBounds(10, 310, 130, 23);
        contentPane.add(addCarButton);
        JButton addPedestrianButton = new JButton("Dodaj pieszego"); //Dodanie pieszego
        addPedestrianButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.addPiesi();
            }
        });
        addPedestrianButton.setBounds(150, 310, 130, 23);
        contentPane.add(addPedestrianButton);
        JButton changeLightButton = new JButton("Zmień światło"); //Dodanie samochodów
        changeLightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.changeLights();
            }
        });
        changeLightButton.setBounds(300, 310, 130, 23);
        contentPane.add(changeLightButton);
        JButton startButton = new JButton("Start"); //start symulacji
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                road.animate();
            }
        });
        startButton.setBounds(450, 310, 80, 23);
        contentPane.add(startButton);
        setVisible(true);
    }
}
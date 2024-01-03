package Traffic;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View extends JFrame {
    JPanel horizontal_roadPanel, vertical_roadPanel1, vertical_roadPanel2;
    TrafficS trafficS = new TrafficS();
    JLayeredPane layeredPane;
    public View() {
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 600);
        add(layeredPane);

    }

    public void drawButtonsPanel(){
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(Color.lightGray);
        buttonsPanel.setBounds(800,0,200,600);

        add(buttonsPanel);
        //buttonsPanel.setLayout(new FlowLayout());
        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.pink);
        startButton.setBounds(820,100,100,50);
        JButton stopButton = new JButton("Stop");
        stopButton.setBackground(Color.pink);
        stopButton.setBounds(820,200,100,50);
        JButton addCarsButton = new JButton("Add cars");
        addCarsButton.setBackground(Color.pink);
        addCarsButton.setBounds(820,30,100,50);
        addCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trafficS.getLeftIntersection().spawnCar(direction.left);
            }
        });
        buttonsPanel.add(addCarsButton);
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
    }

    public void drawRoads() {
        horizontal_roadPanel = new JPanel();
        horizontal_roadPanel.setBackground(Color.darkGray);
        horizontal_roadPanel.setBounds(0, 240, 800, 80);
        horizontal_roadPanel.setLayout(null);
        this.add(horizontal_roadPanel);

        vertical_roadPanel1 = new JPanel();
        vertical_roadPanel1.setBackground(Color.darkGray);
        vertical_roadPanel1.setBounds(210, 0, 80, 600);
        vertical_roadPanel1.setLayout(null);
        this.add(vertical_roadPanel1);

        vertical_roadPanel2 = new JPanel();
        vertical_roadPanel2.setBackground(Color.darkGray);
        vertical_roadPanel2.setBounds(490, 0, 80, 600);
        vertical_roadPanel2.setLayout(null);
        this.add(vertical_roadPanel2);
    }
    public void drawCar(int x, int y) {
        System.out.println("Rysowanie samochodu na współrzędnych: x=" + x + ", y=" + y);
        BufferedImage carImage = null;
        try {
            carImage = ImageIO.read(new File("auto.png")); // Wczytanie obrazka samochodu
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel carLabel = new JLabel(new ImageIcon(carImage));
        carLabel.setBounds(x, y, 40, 40); // Ustawienie pozycji i rozmiaru samochodu
        //add(carLabel); // Dodanie samochodu do panelu drogi poziomej
        layeredPane.add(carLabel, JLayeredPane.PALETTE_LAYER);
        revalidate();
        repaint();
    }
    public void updateView(){
        drawRoads();
        drawButtonsPanel();
        for(Car car : trafficS.getLeftIntersection().getCars()){
            drawCar(car.getX(), car.getY());
            System.out.println("x: "+car.getX()+" y: "+car.getY());
        }
        for(Car car : trafficS.getRightIntersection().getCars()){
            drawCar(car.getX(), car.getY());
        }
        revalidate();
        repaint();
    }
}
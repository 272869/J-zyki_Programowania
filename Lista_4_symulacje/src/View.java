import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    //JPanel horizontal_roadPanel, vertical_roadPanel1, vertical_roadPanel2;
    private Traffic traffic;

    public View() {
        traffic = new Traffic();
        this.setSize(760, 480);
        this.setTitle("Traffic Simulation");
        this.setResizable(false);
        ImageIcon backgroundImage = new ImageIcon("tło.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 760, 480);
        this.setContentPane(backgroundLabel);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /*public void draw_roads(){
        horizontal_roadPanel = new JPanel();
        horizontal_roadPanel.setBackground(Color.GRAY);
        horizontal_roadPanel.setBounds(0, 180, 760, 80);
        this.add(horizontal_roadPanel);

        vertical_roadPanel1 = new JPanel();
        vertical_roadPanel1.setBackground(Color.GRAY);
        vertical_roadPanel1.setBounds(200, 0, 80, 480);
        this.add(vertical_roadPanel1);

        vertical_roadPanel2 = new JPanel();
        vertical_roadPanel2.setBackground(Color.GRAY);
        vertical_roadPanel2.setBounds(480, 0, 80, 480);
        this.add(vertical_roadPanel2);
    }*/
    public JLabel draw_car(String image_path, int position_x, int position_y, int width, int height) {
        ImageIcon original_agent = new ImageIcon(image_path); // dodanie orginalnego obrazka agenta
        Image original_agent_im = original_agent.getImage();
        Image resized_agent_im = original_agent_im.getScaledInstance(width, height, Image.SCALE_SMOOTH); //powiększenie do odpowiednich rozmiarów
        ImageIcon resized_agent_ic = new ImageIcon(resized_agent_im);
        JLabel label = new JLabel(); // dodanie Panelu
        label.setIcon(resized_agent_ic);
        label.setBounds(position_x , position_y , width, height); // ustawienie położenia obrazka
        this.getContentPane().add(label);
        this.revalidate(); //odświeża układ obiektów w oknie
        this.repaint(); // odświeża wygląd okna
        return label;
    }

    /*public void remove_cars(){
        for(int i = 0; i < 760; i++){
            for(int j = 0; j < 480; j++){
                remove_car(i, j);
            }
        }
    }
    public void remove_car(int position_x, int position_y) { //metoda usuwająca obrazka agenta
        Component[] components = this.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                int x = label.getX();
                int y = label.getY();
                int width = label.getWidth();
                int height = label.getHeight();
                if (x == position_x && y == position_y && width == 40 && height == 40) {
                    this.getContentPane().remove(label);
                    this.revalidate();
                    this.repaint();
                    break;
                }
            }
        }
    }*/

    public void update_view() { //odświeżanie widoku
        getContentPane().removeAll();
        for (int i = 0; i < 6; i++){
            Road current_road = traffic.get_road(i);
            int position_x = current_road.get_X();
            int position_y = current_road.get_Y();
            for (Car car : current_road.get_cars_array()) {
                int x = position_x, y = position_y;
                if(current_road.get_direction() == true && i < 2){
                    x = position_x + (car.getX()*8);
                }
                else if(current_road.get_direction() == false && i < 2){
                    x = position_x - (car.getX()*8);
                }
                else if(current_road.get_direction() == false){
                    y = position_y - (car.getX()*8);
                }
                else if(current_road.get_direction() == true){
                    y = position_y + (car.getX()*8);
                }
                System.out.println("x= "+x+" y: "+y);
                draw_car("auto.png",x,y,40,40);
            }
        }
        revalidate();
        repaint();
        traffic.update_roads();
    }
}


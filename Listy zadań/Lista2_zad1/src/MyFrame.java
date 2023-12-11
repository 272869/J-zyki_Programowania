import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton button;
    private JPanel panel;
    //private JLabel available_colours;
    private JLabel text;

    public MyFrame() {
        setSize(700, 300);
        setTitle("Change the background color of the window");
        setLayout(null);
        panel = new JPanel();
        textField = new JTextField("");
        button = new JButton("Change color");
        text = new JLabel("Enter the color name: ");
        //available_colours = new JLabel("Dostępne nazwy kolorów: biały, szary, czerwony, różowy, żółty, pomarańczowy, zielony, niebieski.");
        panel.setBackground(Color.gray);
        panel.setLayout(null);
        textField.setBounds(180, 70, 100, 20);
        button.setBounds(300, 70, 130, 20);
        text.setBounds(50, 70, 150, 20);
        //available_colours.setBounds(30, 30, 700, 20);
        setContentPane(panel);
        add(text);
        //add(available_colours);
        add(textField);
        add(button);
        button.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void changeColor() {
        String colorName = textField.getText().toLowerCase();
        Color color;

        try {
            color = getColorByName(colorName);
            panel.setBackground(color);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Invalid color name", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*private Color getColorByName(String colorName) {
        switch (colorName) {
            case "czerwony":
                return Color.RED;
            case "zielony":
                return Color.GREEN;
            case "niebieski":
                return Color.BLUE;
            default:
                throw new IllegalArgumentException();
        }
    }*/
    private Color getColorByName(String colorName) {
        try {
            return (Color) Color.class.getField(colorName.toUpperCase()).get(null);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        changeColor();
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class UnitConverter extends JFrame{
    private JTextField enter_unit1;
    private JTextField enter_unit2;
    private JButton convert_button;
    private JComboBox unitBox1,unitBox2;

    public UnitConverter(){
        setTitle("Unit Converter");
        setSize(400, 500);
        setLayout(null);
        enter_unit1 = new JTextField("");
        enter_unit2 = new JTextField("");
        convert_button = new JButton("Convert");
        String[] mass = {"mg","g","dg", "kg"};
        unitBox1 = new JComboBox<>(mass);
        unitBox2 = new JComboBox<>(mass);
        enter_unit1.setBounds(100,80,180,20);
        unitBox1.setBounds(100,100,80,20);
        convert_button.setBounds(130,200,80,20);
        enter_unit2.setBounds(100,300,180,20);
        unitBox2.setBounds(100,320,80,20);

        add(enter_unit1);
        add(unitBox1);
        add(enter_unit2);
        add(unitBox2);
        add(convert_button);
        convert_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void convert(){
        try {
            double value = Double.parseDouble(enter_unit1.getText());
            String unitFrom = (String) unitBox1.getSelectedItem();
            String unitTo = (String) unitBox2.getSelectedItem();

            double result = 0;

            switch (unitFrom) {
                case "mg":
                    switch (unitTo) {
                        case "mg":
                            result = value;
                            break;
                        case "g":
                            result = value / 1000;
                            break;
                        case "dg":
                            result = value / 10000;
                            break;
                        case "kg":
                            result = value / 1000000;
                            break;
                    }
                    break;

                case "g":
                    switch (unitTo) {
                        case "mg":
                            result = value * 1000;
                            break;
                        case "g":
                            result = value;
                            break;
                        case "dg":
                            result = value / 10;
                            break;
                        case "kg":
                            result = value / 1000;
                            break;
                    }
                    break;

                case "dg":
                    switch (unitTo) {
                        case "mg":
                            result = value * 10000;
                            break;
                        case "g":
                            result = value * 10;
                            break;
                        case "dg":
                            result = value;
                            break;
                        case "kg":
                            result = value / 100;
                            break;
                    }
                    break;

                case "kg":
                    switch (unitTo) {
                        case "mg":
                            result = value * 1000000;
                            break;
                        case "g":
                            result = value * 1000;
                            break;
                        case "dg":
                            result = value * 100;
                            break;
                        case "kg":
                            result = value;
                            break;
                    }
                    break;
            }

            enter_unit2.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

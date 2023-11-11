import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMI_Calculator extends JFrame {
    private JTextField weight_textField, height_textField;
    private JLabel weight_label, height_label, result_label, kg_label, cm_label,status_label;
    private JButton calcualte_button;

    public BMI_Calculator(){
        setSize(700,500);
        setTitle("BMI Calculator");
        setLayout(null);

        weight_label = new JLabel("Eneter your weight: ");
        kg_label = new JLabel("kg");
        height_label = new JLabel("Eneter your height: ");
        cm_label = new JLabel("cm");
        result_label = new JLabel("Your BMI = ");
        weight_textField = new JTextField("");
        height_textField = new JTextField("");
        calcualte_button = new JButton("Calculate");
        status_label = new JLabel("");

        weight_label.setBounds(50,50,130,20);
        height_label.setBounds(50,80,130,20);
        weight_textField.setBounds(180,50,80,20);
        height_textField.setBounds(180,80,80,20);
        kg_label.setBounds(260,50,30,20);
        cm_label.setBounds(260,80,30,20);
        calcualte_button.setBounds(300,120,100,30);
        result_label.setBounds(200,170,300,30);
        status_label.setBounds(200,200,100,30);

        add(weight_label);
        add(kg_label);
        add(height_label);
        add(cm_label);
        add(result_label);
        add(weight_textField);
        add(height_textField);
        add(calcualte_button);
        add(status_label);
        calcualte_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void calculate() {
        try {
            double weight = Double.parseDouble(weight_textField.getText());
            double height = Double.parseDouble(height_textField.getText()) / 100; // Convert height to meters
            double bmi = weight / (height * height);

            result_label.setText("Your BMI = " + String.format("%.2f", bmi));
            setStatusLabel(bmi);
        } catch (NumberFormatException ex) {
            result_label.setText("Invalid input. Please enter valid numbers.");
            status_label.setText("");
        }
    }
    private void setStatusLabel(double bmi) {
        if (bmi < 18.5) {
            status_label.setText("Underweight");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            status_label.setText("Normal weight");
        } else if (bmi >= 25 && bmi < 29.9) {
            status_label.setText("Overweight");
        } else {
            status_label.setText("Obesity");
        }
    }

}

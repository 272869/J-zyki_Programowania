import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shopping_list extends JFrame {
    private JTextField productTextField;
    private JButton addButton, removeButton;
    private DefaultListModel<String> shoppingListModel;
    private JList<String> shoppingList;

    public Shopping_list() {
        setSize(400, 200);
        setTitle("Shopping list");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        productTextField = new JTextField();
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        shoppingListModel = new DefaultListModel<>();
        shoppingList = new JList<>(shoppingListModel);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(productTextField);
        add(addButton);
        add(removeButton);
        add(new JScrollPane(shoppingList));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProduct();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addProduct() {
        String productName = productTextField.getText().trim();
        if (!productName.isEmpty()) {
            shoppingListModel.addElement(productName);
            productTextField.setText("");
        }
    }

    private void removeProduct() {
        int selectedIndex = shoppingList.getSelectedIndex();
        if (selectedIndex != -1) {
            shoppingListModel.remove(selectedIndex);
        }
    }
}

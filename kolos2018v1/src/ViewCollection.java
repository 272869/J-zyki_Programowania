
import java.awt.Dimension;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewCollection extends JScrollPane {

    private static final long serialVersionUID = -1067156638067634364L;
    private Set<Circle> group;
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel();


    public ViewCollection(Set<Circle> group, int width, int height) {
        this.group = group;
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createTitledBorder("Lista okr�g�w:"));

        String[] tableHeader = {"Nazwa", "Wsp x", "Wsp y", "Wsp r"};
        tableModel = new DefaultTableModel(tableHeader, 0);

        table = new JTable(tableModel)
        {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false;
            }
        };
        table.setSelectionMode(0);
        table.setRowSelectionAllowed(true);
        setViewportView(table);
    }

    void refreshView() {
        tableModel.setRowCount(0);
        for (Circle c : group) {
            Object[] row = { c.getName(), c.getX(), c.getY(), c.getR() };
            tableModel.addRow(row);
        }
    }

    int getSelectedIndex() {
        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "�adne ko�o nie jest zaznaczone.", "B��d", 0);
        }
        return index;
    }

}

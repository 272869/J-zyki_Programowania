import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class RogalKolo2018 extends JFrame implements ActionListener {

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    JMenu menu = new JMenu();
    JMenuBar menuBar = new JMenuBar();
    JMenuItem autorItem = new JMenuItem("Autor");
    JMenuItem aboutItem = new JMenuItem("O programie");
    JMenuItem zakonczItem = new JMenuItem("Zako�cz");
    PanelKolosa panel = new PanelKolosa();

    RogalKolo2018() {
        super("Adam Abacki 123456");
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        menuBar.add(autorItem);
        menuBar.add(aboutItem);
        menuBar.add(zakonczItem);
        autorItem.addActionListener(this);
        aboutItem.addActionListener(this);
        zakonczItem.addActionListener(this);
        this.setJMenuBar(menuBar);
        this.setContentPane(panel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new RogalKolo2018();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Object source = arg0.getSource();
        if (source == zakonczItem) {
            this.dispose();
        }
        if (source == autorItem) {
            JOptionPane.showMessageDialog(this, "Adam Abacki\n123456", "O autorze", JOptionPane.INFORMATION_MESSAGE);
        }
        if (source == aboutItem) {
            JOptionPane.showMessageDialog(this, "c: dodaje nowy obiekt klasy Circle w losowym miejscu\ndelete: usuwa wszystkie obiekty klasy cicle", "Instrukcje",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
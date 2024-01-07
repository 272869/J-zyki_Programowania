import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GlowneOkno extends JFrame implements ActionListener {


    private static final long serialVersionUID = -250916594930969986L;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Opcje");
    private JMenuItem author = new JMenuItem("Autor");
    private JMenuItem about = new JMenuItem("O Programie");
    private JMenuItem close = new JMenuItem("Zamknij");
    private DrawingPanel drawingPanel = new DrawingPanel();

    public static void main(String[] args) {
        new GlowneOkno();
    }

    public GlowneOkno() {
        super("Maciej Grzela, nr indeksu: 241257");
        this.setSize(new Dimension(700,700));

        this.setJMenuBar(menuBar);
        menuBar.add(menu);

        menu.add(author);
        menu.add(about);
        menu.add(close);

        author.addActionListener(this);
        about.addActionListener(this);
        close.addActionListener(this);

        drawingPanel.addKeyListener(new DrawingPanel());
        drawingPanel.setFocusable(true);

        this.setContentPane(drawingPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == author) {
            JOptionPane.showMessageDialog(this, "Maciej Grzela, nr indeksu: 241257", "Autor programu", JOptionPane.PLAIN_MESSAGE);
        }
        if(source == about) {
            JOptionPane.showMessageDialog(this, "Jaka� wiadomo�� przygotowana przez Rogala", "Tytu� jakiej� wiadomo��i", JOptionPane.PLAIN_MESSAGE);
        }
        if(source == close) {
            System.exit(0);
        }
    }

}
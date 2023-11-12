import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad extends JFrame {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public Notepad(){
        setSize(800,500);
        setTitle("Notepad");
        textArea = new JTextArea("");
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        createMenu();
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files","txt");
        fileChooser.setFileFilter(filter);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu file_menu = new JMenu("File");
        setJMenuBar(menuBar);
        menuBar.add(file_menu);
        JMenuItem new_menuItem = new JMenuItem("New");
        JMenuItem save_menuItem = new JMenuItem("Save");
        JMenuItem open_menuItem = new JMenuItem("Open");
        JMenuItem close_menuItem = new JMenuItem("Close");
        file_menu.add(new_menuItem);
        file_menu.add(save_menuItem);
        file_menu.add(open_menuItem);
        file_menu.add(close_menuItem);

        new_menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new_document();
            }
        });
        save_menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_document();
            }
        });
        open_menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open_document();
            }
        });
        close_menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void new_document(){
        textArea.setText("");
    }
    private void open_document(){
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            File selected_file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try{
                BufferedReader reader = new BufferedReader(new FileReader(selected_file));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                textArea.setText(content.toString());
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
    private void save_document(){
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                writer.write(textArea.getText());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

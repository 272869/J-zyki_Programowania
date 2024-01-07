import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
class ImageProcessingApp extends JFrame {
    private JLabel imageLabel;
    private JButton loadImageButton, grayscaleButton, sepiaButton, cancelButton;
    private BufferedImage originalImage, processedImage;
    private volatile boolean isProcessing;
    private SwingWorker<Void, Void> currentWorker;
    public ImageProcessingApp() {
        setTitle("Image Processing App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        loadImageButton = new JButton("Load Image");
        loadImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    loadImage(selectedFile);
                    displayLoadedImage();
                }
            }
        });
        add(loadImageButton, BorderLayout.NORTH);

        grayscaleButton = new JButton("Grayscale");
        grayscaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (originalImage != null && !isProcessing) {
                    applyGrayscale();
                } else if (isProcessing) {
                    JOptionPane.showMessageDialog(null, "Processing is in progress. Please wait or cancel.");
                } else {
                    JOptionPane.showMessageDialog(null, "Load an image first.");
                }
            }
        });
        add(grayscaleButton, BorderLayout.SOUTH);

        sepiaButton = new JButton("Sepia");
        sepiaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (originalImage != null && !isProcessing) {
                    applySepia();
                } else if (isProcessing) {
                    JOptionPane.showMessageDialog(null, "Processing is in progress. Please wait or cancel.");
                } else {
                    JOptionPane.showMessageDialog(null, "Load an image first.");
                }
            }
        });
        add(sepiaButton, BorderLayout.EAST);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelProcessing();
            }
        });
        add(cancelButton, BorderLayout.WEST);
    }
    private synchronized void loadImage(File file) {
        try {
            originalImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayLoadedImage() {
        if (originalImage != null) {
            ImageIcon imageIcon = new ImageIcon(originalImage);
            imageLabel.setIcon(imageIcon);
            imageLabel.repaint();
        }
    }
    private void applyGrayscale() {
        isProcessing = true;
        currentWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                synchronized (ImageProcessingApp.this) {
                    processedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    for (int y = 0; y < originalImage.getHeight(); y++) {
                        for (int x = 0; x < originalImage.getWidth(); x++) {
                            int rgb = originalImage.getRGB(x, y);
                            int gray = (int) (0.3 * ((rgb >> 16) & 0xFF) + 0.59 * ((rgb >> 8) & 0xFF) + 0.11 * (rgb & 0xFF));
                            int grayPixel = (gray << 16) | (gray << 8) | gray;
                            processedImage.setRGB(x, y, grayPixel);
                        }
                    }
                }
                return null;
            }
            @Override
            protected void done() {
                isProcessing = false;
                displayProcessedImage();
            }
        };
        currentWorker.execute();
    }
    private void applySepia() {
        isProcessing = true;
        currentWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                synchronized (ImageProcessingApp.this) {
                    processedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    for (int y = 0; y < originalImage.getHeight(); y++) {
                        for (int x = 0; x < originalImage.getWidth(); x++) {
                            int rgb = originalImage.getRGB(x, y);
                            int r = (rgb >> 16) & 0xFF;
                            int g = (rgb >> 8) & 0xFF;
                            int b = rgb & 0xFF;

                            int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                            int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                            int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);

                            if (tr > 255) tr = 255;
                            if (tg > 255) tg = 255;
                            if (tb > 255) tb = 255;

                            int sepiaPixel = (tr << 16) | (tg << 8) | tb;
                            processedImage.setRGB(x, y, sepiaPixel);
                        }
                    }
                }
                return null;
            }
            @Override
            protected void done() {
                isProcessing = false;
                displayProcessedImage();
            }
        };
        currentWorker.execute();
    }
    private synchronized void cancelProcessing() {
        if (isProcessing && currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
            isProcessing = false;
            JOptionPane.showMessageDialog(null, "Image processing canceled.");
        }
    }
    private void displayProcessedImage() {
        if (processedImage != null) {
            ImageIcon imageIcon = new ImageIcon(processedImage);
            imageLabel.setIcon(imageIcon);
            imageLabel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ImageProcessingApp app = new ImageProcessingApp();
                app.setVisible(true);
            }
        });
    }
}

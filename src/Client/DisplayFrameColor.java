package Client;

import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Analasyis.BMPREAD;

public class DisplayFrameColor extends JComponent{
    BufferedImage image;
    BMPREAD rc = new BMPREAD("D:\\iamges\\timg.bmp");

    public DisplayFrameColor() throws IOException {
    }

    public void initialize() {
        int width = rc.get_biwidth();
        int height = rc.get_biheight();
        int[] data = new int[width * height];
        int index = 0;
        int[][] clrR = rc.rgb2grayA().get("clrR");
        int[][] clrG = rc.rgb2grayA().get("clrG");
        int[][] clrB = rc.rgb2grayA().get("clrB");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[index++] = clrR[i][j] << 16 | clrG[i][j] << 8 | clrB[i][j];
            }
        }
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, data, 0, width);
    }

    public void paint(Graphics g) {
        if (image == null)
            initialize();
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Display Colours");
        try {
            f.getContentPane().add(new DisplayFrameColor());
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.setSize(900, 900);
        f.setLocation(100, 100);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}

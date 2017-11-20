package Client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JComponent;
import Analasyis.BMPREAD;
import Analasyis.SzoneT;

public class DisplayFrameColor extends JComponent{
    BufferedImage image;
    BMPREAD rcb;
    SzoneT rcs;
    int width;
    int height;
    HashMap<String,int[][]> clrmap;

    public DisplayFrameColor(String path) throws IOException {
         rcs = new SzoneT(path);
         width = rcs.rc.get_biwidth();
         height = rcs.rc.get_biheight();
         clrmap = rcs.mirror("vertical");
    }

    public void switchtype(String type){
        switch (type){
            case "max":
                break;
            case "average":
                break;
            case "weight":
                break;
            default:
                break;
        }
    }

    public void initialize() {
        int[] data = new int[width * height];
        int index = 0;
        int[][] clrR = clrmap.get("clrR");
        int[][] clrG = clrmap.get("clrG");
        int[][] clrB = clrmap.get("clrB");

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

}

package Client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;

import Analasyis.SzoneT;

public class DisplayFrameColor extends JPanel{
    BufferedImage image;
    SzoneT rcs;
    int width;
    int height;
    HashMap<String,int[][]> clrmap;
    String type;
    String path;

    public DisplayFrameColor(String path,String type) throws IOException {
        this.type=type;
        this.path=path;
        rcs = new SzoneT(path);
        width = rcs.rc.get_biwidth();
        height = rcs.rc.get_biheight();
        switchtype();
    }

    public void switchtype(){
        switch (type){
            case "raw":
                clrmap = rcs.rc.get_rgbdata();
                break;
            case "max":
                clrmap = rcs.rc.rgb2gray("max");
                break;
            case "average":
                clrmap = rcs.rc.rgb2gray("average");
                break;
            case "weight":
                clrmap = rcs.rc.rgb2gray("weight");
                break;
            case "vertical":
                clrmap = rcs.mirror("vertical");
                break;
            case "level":
                clrmap = rcs.mirror("level");
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
        initialize();
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }


}

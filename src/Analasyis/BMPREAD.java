package Analasyis;

import java.io.IOException;
import java.util.HashMap;

public class BMPREAD {

    private String path = "";
    private int[] imconr;
    private int[] bftype = new int[2];
    private int[] bfsize = new int[4];
    private int[] bfoffbits = new int[4];
    private int[] bisize = new int[4];
    private int[] biwidth = new int[4];
    private int[] biheight = new int[4];
    private int[] biplanes = new int[2];
    private int[] bibitcount = new int[2];
    private int[] bicompression = new int[4];
    private int[] bisizeimage = new int[4];
    private int[] biXpelspermeter = new int[4];
    private int[] biYpelspermeter = new int[4];
    private int[] biclrused = new int[4];
    private int[] biclrimportant = new int[4];
    private int[] rgbdata;

    public BMPREAD(String path) throws IOException {
        this.path = path;
        readbmp();
        datainput();
    }

    public void readbmp() throws IOException {
        int tsize = 30246000; //
        java.io.FileInputStream fin = new java.io.FileInputStream(this.path);
        java.io.BufferedInputStream bis = new java.io.BufferedInputStream(fin);
        byte[] imcon = new byte[tsize];
        bis.read(imcon);
        this.imconr = new int[tsize];
        for (int i = 0; i < this.imconr.length; i++) {
            if (imcon[i] < 0) {
                this.imconr[i] = imcon[i] + 256;
            } else {
                this.imconr[i] = imcon[i];
            }
        }
        fin.close();
        bis.close();
    }

    public int dataconvert(int[] type) {
        int data = 0;
        if (type.length == 4) {
            data = type[0] + (type[1] << 8) + (type[2] << 16) + (type[3] << 24);
        } else {
            data = type[0] + (type[1] << 8);
        }
        return data;
    }

    public void datainput() {
        System.arraycopy(this.imconr, 0, bftype, 0, 2);
        System.arraycopy(this.imconr, 2, bfsize, 0, 4);
        System.arraycopy(this.imconr, 10, bfoffbits, 0, 4);
        System.arraycopy(this.imconr, 14, bisize, 0, 4);
        System.arraycopy(this.imconr, 18, biwidth, 0, 4);
        System.arraycopy(this.imconr, 22, biheight, 0, 4);
        System.arraycopy(this.imconr, 26, biplanes, 0, 2);
        System.arraycopy(this.imconr, 28, bibitcount, 0, 2);
        System.arraycopy(this.imconr, 30, bicompression, 0, 4);
        System.arraycopy(this.imconr, 34, bisizeimage, 0, 4);
        System.arraycopy(this.imconr, 38, biXpelspermeter, 0, 4);
        System.arraycopy(this.imconr, 42, biYpelspermeter, 0, 4);
        System.arraycopy(this.imconr, 46, biclrused, 0, 4);
        System.arraycopy(this.imconr, 50, biclrimportant, 0, 4);
    }

    public int get_bftype() {
        return dataconvert(this.bftype);
    }

    public int get_bfsize() {
        return dataconvert(this.bfsize);
    }

    public int get_bfoffbits() {
        return dataconvert(this.bfoffbits);
    }

    public int get_bisize() {
        return dataconvert(this.bisize);
    }

    public int get_biwidth() {
        return dataconvert(this.biwidth);
    }

    public int get_biheight() {
        return dataconvert(this.biheight);
    }

    public int get_biplanes() {
        return dataconvert(this.biplanes);
    }

    public int get_bibitcount() {
        return dataconvert(this.bibitcount);
    }

    public int get_bicompression() {
        return dataconvert(this.bicompression);
    }

    public int get_bisizeimage() {
        return dataconvert(this.bisizeimage);
    }

    public int get_biXpelspermeter() {
        return dataconvert(this.biXpelspermeter);
    }

    public int get_biYpelspermeter() {
        return dataconvert(this.biYpelspermeter);
    }

    public int get_biclrused() {
        return dataconvert(this.biclrused);
    }

    public int get_biclrimportant() {
        return dataconvert(this.biclrimportant);
    }

    public HashMap<String, int[][]> get_rgbdata() {
        int width = get_biwidth();
        int height = get_biheight();
        int biBitCount = get_bibitcount();
        int scanbyte = (width * biBitCount + 31) / 32 * 4;
        this.rgbdata = new int[get_bfsize() - 54];
        System.arraycopy(this.imconr, 54, rgbdata, 0, this.rgbdata.length);
        int[][] clrR = new int[height][width];
        int[][] clrG = new int[height][width];
        int[][] clrB = new int[height][width];
        for (int i = 0; i < rgbdata.length - scanbyte; i += scanbyte) {
            int theight = height - i / scanbyte - 1;
            for (int j = 0; j < scanbyte - 3; j += 3) {
                clrB[theight][j / 3] = rgbdata[i + j];
                clrG[theight][j / 3] = rgbdata[i + j + 1];
                clrR[theight][j / 3] = rgbdata[i + j + 2];
            }
        }
        HashMap<String, int[][]> clrmap = new HashMap();
        clrmap.put("clrB", clrB);
        clrmap.put("clrR", clrR);
        clrmap.put("clrG", clrG);
        return clrmap;
    }

    public HashMap<String, int[][]> rgb2gray(String type) {
        HashMap<String, int[][]> clrmapRGB = get_rgbdata();
        HashMap<String, int[][]> clrmapGray = new HashMap<>();
        int[][] clrR = clrmapRGB.get("clrR");
        int[][] clrG = clrmapRGB.get("clrG");
        int[][] clrB = clrmapRGB.get("clrB");
        switch (type) {
            case "max":
                for (int i = 0; i < clrR.length; i++) {
                    for (int j = 0; j < clrR[0].length; j++) {
                        int max = Math.max(clrR[i][j], clrG[i][j]);
                        max = Math.max(max, clrB[i][j]);
                        clrR[i][j] = clrG[i][j] = clrB[i][j] = max;
                    }
                }
                break;
            case "average":
                for (int i = 0; i < clrR.length; i++) {
                    for (int j = 0; j < clrR[0].length; j++) {
                        int avg = (clrR[i][j] + clrG[i][j] + clrB[i][j]) / 3;
                        clrR[i][j] = clrG[i][j] = clrB[i][j] = avg;
                    }
                }
                break;
            case "weight":
                for (int i = 0; i < clrR.length; i++) {
                    for (int j = 0; j < clrR[0].length; j++) {
                        int sum = (int) (0.299 * clrR[i][j] + 0.587 * clrG[i][j] + 0.114 * clrB[i][j]);
                        clrR[i][j] = clrG[i][j] = clrB[i][j] = sum;
                    }
                }
                break;
            default:
                break;
        }
        clrmapGray.put("clrB", clrB);
        clrmapGray.put("clrR", clrR);
        clrmapGray.put("clrG", clrG);
        return clrmapGray;
    }
}

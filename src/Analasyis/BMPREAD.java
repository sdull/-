package Analasyis;

import java.io.IOException;

public class BMPREAD {

    private String path = "D://iamges";
    private int[] imcont;
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

    public BMPREAD(String path) throws IOException {
        this.path=path;
        this.imcont=readbmp();
    }
    public int[] readbmp() throws IOException{
        java.io.FileInputStream fin = new java.io.FileInputStream(this.path);
        java.io.BufferedInputStream bis = new java.io.BufferedInputStream(fin);
        byte[] imcon = new byte[25600000];
        bis.read(imcon);
        int[] imconr = new int[imcon.length];
        for (int i=0;i<imconr.length;i++){
            if (imconr[i]<0) {
                imconr[i] = imcon[i]+256;
            }else{
                imconr[i] = imcon[i];
            }
        }
        fin.close();
        bis.close();
        return imconr;
    }

}

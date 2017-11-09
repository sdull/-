package Analasyis;

import java.io.IOException;

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
    private int[] rgbs;

    public BMPREAD(String path) throws IOException {
        this.path=path;
        readbmp();
    }
    public void readbmp() throws IOException{
        java.io.FileInputStream fin = new java.io.FileInputStream(this.path);
        java.io.BufferedInputStream bis = new java.io.BufferedInputStream(fin);
        byte[] imcon = new byte[25600000];
        bis.read(imcon);
        this.imconr = new int[imcon.length];
        for (int i=0;i<this.imconr.length;i++){
            if (this.imconr[i]<0) {
                this.imconr[i] = imcon[i]+256;
            }else{
                this.imconr[i] = imcon[i];
            }
        }
        fin.close();
        bis.close();
    }
    public int dataconvert(int[] type) {
        int data = type[0] + type[1] << 8 + type[2] << 16 + type[3] << 24;
        return data;
    }

    public void datainput(){
        System.arraycopy(this.imconr,0,bftype,0,2);
        System.arraycopy(this.imconr,2,bfsize,0,4);
        System.arraycopy(this.imconr,10,bfoffbits,0,4);
        System.arraycopy(this.imconr,14,bisize,0,4);
        System.arraycopy(this.imconr,18,biwidth,0,4);
        System.arraycopy(this.imconr,22,biheight,0,4);
        System.arraycopy(this.imconr,26,biplanes,0,2);
        System.arraycopy(this.imconr,28,bibitcount,0,2);
        System.arraycopy(this.imconr,30,bicompression,0,4);
        System.arraycopy(this.imconr,34,bisizeimage,0,4);
        System.arraycopy(this.imconr,38,biXpelspermeter,0,4);
        System.arraycopy(this.imconr,42,biYpelspermeter,0,4);
        System.arraycopy(this.imconr,46,biclrused,0,4);
        System.arraycopy(this.imconr,50,biclrimportant,0,4);
    }

    public int get_bftype(){
        return dataconvert(this.bftype);
    }
}

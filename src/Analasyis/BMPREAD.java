package Analasyis;

import java.io.IOException;

public class BMPREAD {
    private String path = "D://iamges";

    public BMPREAD(String path){
        this.path=path;
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

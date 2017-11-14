package Analasyis;

import java.io.IOException;
import java.util.HashMap;

public class SzoneT {

    HashMap<String, int[][]> clrmap;
    int[][] clrR;
    int[][] clrG;
    int[][] clrB;

    public SzoneT(String path) throws IOException{
        BMPREAD rc =new BMPREAD(path);
        this.clrmap = rc.get_rgbdata();
        this.clrR = this.clrmap.get("clrR");
        this.clrG = this.clrmap.get("clrG");
        this.clrB = this.clrmap.get("clrB");
    }
    public void mirror(String type){
        HashMap<String, int[][]> clrmapM = new HashMap<>();
        int r=this.clrR.length;int c=this.clrR[0].length;
        int rr=r;int cc=c;
        int[][] clrR = new int[r][c];
        int[][] clrG = new int[r][c];
        int[][] clrB = new int[r][c];
        switch (type){
            case "level":
                for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                        clrR[i][j]=this.clrR[i][--cc];
                        clrG[i][j]=this.clrG[i][--cc];
                        clrB[i][j]=this.clrB[i][--cc];
                    }
                }
                break;
            case "vertical":
                for(int i=0;i<r;i++){
                    for(int j=0;j<c;j++){
                        clrR[i][j]=this.clrR[--rr][j];
                        clrG[i][j]=this.clrG[--rr][j];
                        clrB[i][j]=this.clrB[--rr][j];
                    }
                }
                break;
        }
        clrmapM.put("clrB", this.clrB);
        clrmapM.put("clrR", this.clrR);
        clrmapM.put("clrG", this.clrG);
    }
    public void panning(int X,int Y){

    }
    public void cut(int Xs,int Ys,int Xe,int Ye){

    }
    public void rotate(int angle){

    }
    public void zoom(String type,int times){
        switch (type){
            case "near":
                break;
            case "double":
                break;
            default:
                break;
        }
    }

}

package Analasyis;

import java.io.IOException;
import java.util.HashMap;

public class SzoneT {
    public BMPREAD rc;
    HashMap<String, int[][]> clrmap;
    int[][] clrR;
    int[][] clrG;
    int[][] clrB;

    public SzoneT(String path) throws IOException{
        rc =new BMPREAD(path);
        clrmap = rc.get_rgbdata();
        clrR = clrmap.get("clrR");
        clrG = clrmap.get("clrG");
        clrB = clrmap.get("clrB");
    }
    public HashMap<String, int[][]> mirror(String type){
        HashMap<String, int[][]> clrmapM = new HashMap<>();
        int r=clrR.length;int c=clrR[0].length;
        int rr=r-1;int cc=0;
        int[][] clrR = new int[r][c];
        int[][] clrG = new int[r][c];
        int[][] clrB = new int[r][c];

                for(int i=0;i<r;i++) {
                    cc = c - 1;
                    for (int j = 0; j < c; j++) {
                        switch (type) {
                            case "level":
                                clrR[i][j] = this.clrR[i][cc];
                                clrG[i][j] = this.clrG[i][cc];
                                clrB[i][j] = this.clrB[i][cc];
                                cc--;
                                continue;
                            case "vertical":
                                clrR[i][j] = this.clrR[rr][j];
                                clrG[i][j] = this.clrG[rr][j];
                                clrB[i][j] = this.clrB[rr][j];
                                continue;
                        }
                    }
                    rr--;
                }
        clrmapM.put("clrB", clrB);
        clrmapM.put("clrR", clrR);
        clrmapM.put("clrG", clrG);
        return clrmapM;
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

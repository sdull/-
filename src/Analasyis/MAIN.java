package Analasyis;
import java.io.IOException;
import java.util.HashMap;

public class MAIN {
    public static final void main(String[] args) throws IOException {
    BMPREAD rc =new BMPREAD("D:\\iamges\\33.bmp");
    HashMap<String,int[][]> clr = rc.get_rgbdata();
    int clrR =clr.get("clrR")[0][2];
    int clrG =clr.get("clrG")[0][2];
    int clrB =clr.get("clrB")[0][2];
    System.out.println(clrR);
    System.out.println(clrG);
    System.out.println(clrB);
    }
}

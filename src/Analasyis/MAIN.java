package Analasyis;
import java.io.IOException;

public class MAIN {
    public static final void main(String[] args) throws IOException {
    BMPREAD rc =new BMPREAD("D:\\iamges\\33.bmp");
    int[] zz = rc.readbmp();
    System.out.println(zz.length);
    for (int i=0;i<100;i++){
        System.out.println(zz[i]);
    }
    }
}

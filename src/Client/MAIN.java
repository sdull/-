package Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class MAIN {
    public static void main(String[] args) {
        String path="D:\\iamges\\timg.bmp";
        JFrame f = new JFrame("Display Colours");
        try {
            DisplayFrameColor j1 =new DisplayFrameColor(path,"raw");
            j1.setBounds(0,0,700,400);
            DisplayFrameColor j2 =new DisplayFrameColor(path,"weight");
            j2.setBounds(0,450,700,400);
            DisplayFrameColor j3 =new DisplayFrameColor(path,"level");
            j3.setBounds(800,0,700,400);
            DisplayFrameColor j4 =new DisplayFrameColor(path,"vertical");
            j4.setBounds(800,450,700,400);
            f.setSize(1800, 900);
            f.add(j1);
            f.add(j2);
            f.add(j3);
            f.add(j4);

        } catch (IOException e) {
            e.printStackTrace();
        }
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}

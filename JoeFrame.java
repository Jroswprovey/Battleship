import javax.swing.*;
import java.awt.*;

public class JoeFrame extends JFrame {

    private int Len;
    private int Hei;

    public JoeFrame(int len, int hei){
        //constructor method
        Len = len;
        Hei = hei;
        setTitle("Battleship");

        setSize(Len, Hei);




        setVisible(true);
    }

    public JoeFrame(){
        Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());


    }

    public int getHei() {
        return Hei;
    }

    public int getLen() {
        return Len;
    }
}

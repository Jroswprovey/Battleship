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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //dynamically sizes window to screen size
        Len = ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())/2;
        Hei = ((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())/2;

        setSize(Len,Hei);


        setVisible(true);




    }

    public int getHei() {
        return Hei;
    }

    public int getLen() {
        return Len;
    }
}

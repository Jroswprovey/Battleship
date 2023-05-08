import javax.swing.*;
import java.awt.*;

public class JoeFrame extends JFrame {

    private int Len;
    private int Hei;

    public JoeFrame(int len, int hei){
        //constructor method
        setTitle("Battleship");

        Len = len;
        Hei = hei;
        setSize(Len, Hei);


        JMenuBar MenuBar = new JMenuBar();
        JMenu GameMenu = new JMenu("Game Menu");

        JMenuItem StartItem = new JMenuItem("Start Game");
        JMenuItem ConnectItem = new JMenuItem("Connect");





        MenuBar.add(GameMenu);

        this.setJMenuBar(MenuBar);








        setVisible(true);
    }



    public int getHei() {
        return Hei;
    }

    public int getLen() {
        return Len;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoeFrame extends JFrame implements ActionListener {

    private int Len;
    private int Hei;

    JMenuItem StartItem;
    JMenuItem ConnectItem;
    JMenuBar MenuBar;
    JMenu GameMenu;
    JPanel Game;
    JPanel Chat;



    public JoeFrame(int len, int hei){
        //constructor method
        setTitle("Battleship");

        Len = len;
        Hei = hei;
        setSize(Len, Hei);


        MenuBar = new JMenuBar();
        GameMenu = new JMenu("Game Menu");

        Game = new JPanel();
        Chat = new JPanel();

        StartItem = new JMenuItem("Start Game");
        ConnectItem = new JMenuItem("Connect");

        StartItem.addActionListener(this);


        GameMenu.add(StartItem);
        GameMenu.add(ConnectItem);



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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==StartItem){
            System.out.println("Clicked");

        }
    }
}

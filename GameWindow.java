import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class GameWindow extends JFrame implements ActionListener {

    private int Len;
    private int Hei;


    JMenuItem StartServerItem;
    JMenuItem StartItem;
    JMenuItem ConnectItem;
    JMenuBar MenuBar;
    JMenu GameMenu;

    JPanel Game;
    JPanel Chat;



    public GameWindow(int len, int hei) {
        //constructor method
        setTitle("Battleship");

        Len = len;
        Hei = hei;
        setSize(Len, Hei);

        MenuBar = new JMenuBar();
        GameMenu = new JMenu("Game Menu");

        Chat = new JPanel();

        StartItem = new JMenuItem("Start Game");
        ConnectItem = new JMenuItem("Connect");
        StartServerItem = new JMenuItem("Menu");

        StartItem.addActionListener(this);
        ConnectItem.addActionListener(this);
        StartServerItem.addActionListener(this);

        GameMenu.add(StartItem);
        GameMenu.add(ConnectItem);
        GameMenu.add(StartServerItem);

        MenuBar.add(GameMenu);
        setJMenuBar(MenuBar);

        setLayout(new BorderLayout()); // Use BorderLayout for the JFrame

        JPanel gridPanel = new JPanel(new GridLayout(0, 11)); // Create a grid panel with 11 columns

        // Add a null space in the top right corner
        gridPanel.add(new JLabel("", SwingConstants.CENTER));

        // Add labels for letters A to J at the top
        for (char ch = 'A'; ch <= 'J'; ch++) {
            JLabel label = new JLabel(String.valueOf(ch), SwingConstants.CENTER);
            gridPanel.add(label);
        }

        // Add labels for numbers 1 to 10 on the left side and buttons for the cells
        for (int i = 1; i <= 10; i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            gridPanel.add(label);
            for (int j = 1; j <= 10; j++) {
                JButton button = new JButton(String.valueOf((i - 1) * 10 + j));
                button.addActionListener(this); // Assign ActionListener to the button
                gridPanel.add(button);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
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
        if (e.getSource() == StartItem) {
            System.out.println("Clicked");
        }

        if (e.getSource() == ConnectItem) {
            System.out.println("ConnectItem Clicked");
            ConnectPopupWindow ConnectWindow = new ConnectPopupWindow();
            ConnectWindow.setVisible(true);
        }

        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();

            ((JButton) e.getSource()).setForeground(Color.red);

            String buttonText = clickedButton.getText();
            System.out.println("Button Clicked: " + buttonText);

            if (e.getSource() == StartServerItem){
                //Server.startServer();

            }

        }
    }


}

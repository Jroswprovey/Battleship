import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectPopupWindow extends JFrame implements ActionListener {
    private JTextField pleaseEnterIPTextField;
    private JPanel panel1;
    private JButton connectButton;

    public ConnectPopupWindow(){
        setLocationRelativeTo(null);
        setTitle("Connect");
        setResizable(false);
        setVisible(true);
        setSize(200,150);
        add(panel1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);




    }

    public void setVisiblecool(boolean b) {
        super.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.equals(connectButton)){
            System.out.println("connectButton Clicked");


        }

    }
}

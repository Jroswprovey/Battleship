import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpWindow extends JFrame implements ActionListener {


    public PopUpWindow(String title, String text, String buttonText){

        JLabel Label = new JLabel(text);
        JTextField TextField = new JTextField();
        JButton Button = new JButton(buttonText);

        this.add(Button);







    }

    @Override
    public void setVisible(boolean b) {
        this.setVisible(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

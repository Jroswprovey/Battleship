import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerInfo extends JFrame implements ActionListener {
    private JLabel ServerIPLabel;

    public ServerInfo() {
        add(ServerIPLabel);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

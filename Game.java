import javax.swing.*;

public class Game {

    public static void start(){
        System.out.println("Starting Game...");

        JoeFrame JoeGame = new JoeFrame();



        JPanel Chat = new JPanel();
        Chat.setSize(JoeGame.getHei()/4,JoeGame.getLen());
        Chat.setBackground(new java.awt.Color(44, 44, 52));

        JoeGame.add(Chat);






    }


}

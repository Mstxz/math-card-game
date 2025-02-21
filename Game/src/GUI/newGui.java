package GUI;

import javax.swing.*;
import java.awt.*;

public class newGui {
    private JFrame frame;
    private JPanel panel;
    private JPanel panel2;
    private JPanel profileSelf;
    private JLabel name1;

    public newGui() {
        frame = new JFrame();
        panel = new JPanel();
        panel2 = new JPanel();
        name1 = new JLabel("Bot 1");
        profileSelf = new JPanel();
        profileSelf.setBackground(Color.RED);
        //profileSelf.setSize(165,165);
        frame.setTitle("New Game");
        panel.setLayout(new GridLayout(3,3));
        //panel2.setLayout(new BorderLayout());
        panel.add(name1);
        panel.add(new JLabel("Bot 2"));
        panel.add(new JLabel("Bot 3"));
        panel.add(new JLabel("Bot 4"));
        panel.add(new JLabel("Bot 5"));
        panel.add(new JLabel("Bot 6"));
        panel.add(new JLabel("Bot 7"));
        panel.add(new JLabel("Bot 8"));
        panel.add(new JLabel("Bot 9"));
//        panel2.add(profileSelf, BorderLayout.CENTER);
//        panel2.add(name1, BorderLayout.NORTH);

        frame.add(panel);
        //panel.setSize(1920,1080);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        panel2.setVisible(true);
        panel.setVisible(true);
        frame.setVisible(true);
        profileSelf.setVisible(true);
        name1.setVisible(true);
    }
    public static void main(String[] args) {
        new newGui();
    }
}

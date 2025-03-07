package GUI.Component;

import Gameplay.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectOpponent extends JDialog  implements ActionListener {
    private JButton selfButton;
    private JButton opponentButton;
    private JButton doneButton;

    private Player self;
    private Player opponent;

    private Player reciever;
    public SelectOpponent(Player self, Player opponent) {
        super();
        setTitle("Select Opponent");
        setBounds(100, 100, 450, 300);

        selfButton = new JButton(self.getName());
        opponentButton = new JButton(opponent.getName());

        this.add(new JLabel("Select Target"), BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(selfButton);
        p.add(opponentButton);

        this.add(p, BorderLayout.CENTER);

        doneButton = new JButton("Done");

        this.add(doneButton, BorderLayout.SOUTH);

        this.self = self;
        this.opponent = opponent;

        selfButton.addActionListener(this);
        opponentButton.addActionListener(this);
        doneButton.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selfButton) {
            reciever = self;
        }
        else if (e.getSource() == opponentButton) {
            reciever = opponent;
        }
        else if (e.getSource() == doneButton) {
            this.setVisible(false);
        }
    }

    
}

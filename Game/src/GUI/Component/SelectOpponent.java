package GUI.Component;

import Gameplay.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
        this.setModal(true);
        this.setUndecorated(true);
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

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selfButton) {
            reciever = self;
            this.selfButton.setBorder(new LineBorder(Color.ORANGE,5));
            this.opponentButton.setBorder(null);
        }
        else if (e.getSource() == opponentButton) {
            reciever = opponent;
            this.selfButton.setBorder(null);
            this.opponentButton.setBorder(new LineBorder(Color.ORANGE,5));
        }
        else if (e.getSource() == doneButton) {
            if(reciever != null){
                this.setVisible(false);
            }

        }
    }

    public Player getReciever() {
        return reciever;
    }

    public void setReciever(Player reciever) {
        this.reciever = reciever;
    }
}

package GUI.Component;

import GUI.Page.AvengerAssembleGUI;
import GUI.Page.Page;
import GUI.Router;
import Gameplay.Card;
import Gameplay.Player;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectOpponent extends JPanel  implements ActionListener {
    private JButton selfButton;
    private JButton opponentButton;
    private JButton doneButton;
    private AvengerAssembleGUI avengerAssembleGUI;
    private Player self;
    private Player opponent;

    private Player reciever;
    private Card card;

    public SelectOpponent(Player self, Player opponent, Card card, AvengerAssembleGUI gui) {
        super();
        this.card = card;
        this.avengerAssembleGUI = gui;
        gui.setBackdropDim(true);

        this.setLayout(new BorderLayout());
        setBounds((Router.getMainFrame().getWidth() - 450)/2, (Router.getMainFrame().getHeight() - 300)/2, 450, 300);
        selfButton = new JButton(self.getName());
        opponentButton = new JButton(opponent.getName());
        JLabel selectTarget = new JLabel("Select Target");
        selectTarget.setFont(SharedResource.getCustomSizeFont(36));
        selectTarget.setForeground(SharedResource.SIAMESE_BRIGHT);
        this.add(selectTarget, BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setOpaque(false);
        p.add(selfButton);
        p.add(opponentButton);

        this.add(p, BorderLayout.CENTER);

        doneButton = new JButton("Done");

        this.add(doneButton, BorderLayout.SOUTH);

        this.self = self;
        this.opponent = opponent;
        this.setBackground(SharedResource.SIAMESE_BASE);
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
                this.card.action(self, reciever);
                self.getDeck().addDispose(card);
                avengerAssembleGUI.getUserPanel().updatePlayable(opponent);
                avengerAssembleGUI.updatePlayerHUD();
                avengerAssembleGUI.initCard();
                this.setVisible(false);
                avengerAssembleGUI.setBackdropDim(false);
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

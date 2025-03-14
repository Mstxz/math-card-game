package GUI.Component;

import GUI.Page.AvengerAssembleGUI;
import GUI.Page.Page;
import GUI.Router;
import Gameplay.Card;
import Gameplay.Player;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectOpponent extends JPanel implements MouseListener,ActionListener {
    private PlayerProfile selfButton;
    private PlayerProfile opponentButton;
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
        this.self = self;
        this.opponent = opponent;
        gui.setBackdropDim(true);

        this.setLayout(new BorderLayout());
        setBounds((Router.getMainFrame().getWidth() - 850)/2, (Router.getMainFrame().getHeight() - 400)/2, 850, 400);
        JLabel selectTarget = new JLabel("Select Target");
        selectTarget.setFont(SharedResource.getCustomSizeFont(36));
        selectTarget.setForeground(SharedResource.SIAMESE_BRIGHT);
        selectTarget.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(selectTarget, BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.setOpaque(false);
        selfButton = new PlayerProfile(self.getName(),self.getProfilePicture());
        opponentButton = new PlayerProfile(opponent.getName(),opponent.getProfilePicture());
        p.add(selfButton);
        p.add(opponentButton);

        this.add(p, BorderLayout.CENTER);

        doneButton = new JButton("Done");

        this.add(doneButton, BorderLayout.SOUTH);


        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(SharedResource.SIAMESE_BASE);
        selfButton.addMouseListener(this);
        opponentButton.addMouseListener(this);
        doneButton.addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doneButton) {
            if(reciever != null){
                this.card.action(self, reciever);
                self.getDeck().addDispose(card);
                avengerAssembleGUI.getUserPanel().updatePlayable(opponent);
                avengerAssembleGUI.updatePlayerHUD();
                avengerAssembleGUI.initCard();
                this.setVisible(false);
                avengerAssembleGUI.setBackdropDim(false);
                if (Player.checkWin(self,opponent) != null){
                    avengerAssembleGUI.result(Player.checkWin(self,opponent));
                }
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

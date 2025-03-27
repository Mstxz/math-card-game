package GUI.Component;

import AudioPlayer.SFXPlayer;
import GUI.Page.AvengerAssembleGUI;
import Gameplay.Card;
import Gameplay.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import utils.UIManager.RoundPanelUI;
import utils.SharedResource;


public class SelectOpponent extends JPanel implements MouseListener,ActionListener {
    private PlayerProfile selfButton;
    private PlayerProfile opponentButton;
    private JButton doneButton;
    private AvengerAssembleGUI avengerAssembleGUI;
    private Player self;
    private ArrayList<Player> opponentList;
    private final int WIDTH = 850;
    private final int HEIGHT = 500;

    private Player reciever;
    private Card card;

    public SelectOpponent(Player self, ArrayList<Player> opponentList, Card card, AvengerAssembleGUI gui) {
        super();
        this.card = card;
        this.avengerAssembleGUI = gui;
        this.self = self;
        this.opponentList = opponentList;
        gui.setBackdropDim(true);

        this.setLayout(new BorderLayout());
        setSize(WIDTH,HEIGHT);
        JLabel selectTarget = new JLabel("Select Target");
        selectTarget.setFont(SharedResource.getCustomSizeFont(36));
        selectTarget.setForeground(SharedResource.SIAMESE_BRIGHT);
        selectTarget.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(selectTarget, BorderLayout.NORTH);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
        p.setOpaque(false);
        selfButton = new PlayerProfile(self);
        selfButton.setBorder(new LineBorder(SharedResource.SIAMESE_BASE,5));
        p.add(selfButton);
        for (Player opponent:opponentList){
            opponentButton = new PlayerProfile(opponent);
            opponentButton.setBorder(new LineBorder(SharedResource.SIAMESE_BASE,5));
            opponentButton.addMouseListener(this);
            p.add(opponentButton);
        }

        this.add(p, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        doneButton = new JButton("Done");
        doneButton.setPreferredSize(new Dimension(250, 76));
        doneButton.setFont(SharedResource.getCustomSizeFont(28));
        doneButton.setForeground(new Color(72, 62, 56)); 

        doneButton.setContentAreaFilled(false);
        doneButton.setOpaque(false);    
        doneButton.setForeground(Color.BLACK);

        doneButton.setBorder(BorderFactory.createCompoundBorder(
        new RoundBorder(new Color(72, 62, 56), new Color(98, 86, 77), 4, 36),  // ขอบนอก (เข้ม)
        BorderFactory.createCompoundBorder(
        new RoundBorder(new Color(191, 180, 168), new Color(191, 180, 168), 12, 28), // ขอบกลาง (กลาง)
        new RoundBorder(new Color(221, 218, 210), new Color(221, 218, 210), 2, 12)   // ขอบใน (อ่อน)
        )
        ));
        doneButton.setLayout(null);

        //Label แปะบนปุ่ม 
        JLabel buttonLabel = new JLabel("Done", SwingConstants.CENTER);
        buttonLabel.setFont(SharedResource.getCustomSizeFont(28));
        buttonLabel.setForeground(Color.BLACK);
        buttonLabel.setBounds(0, 0, doneButton.getPreferredSize().width, doneButton.getPreferredSize().height);
        doneButton.add(buttonLabel);
        buttonPanel.add(doneButton);

        this.add(buttonPanel, BorderLayout.SOUTH);


        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BASE,50,50));
        selfButton.addMouseListener(this);
        doneButton.addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(selfButton)) {
            reciever = self;
            this.selfButton.setBorder(new LineBorder(Color.ORANGE,5));
            this.opponentButton.setBorder(new LineBorder(SharedResource.SIAMESE_BASE,5));
        }
        else if (e.getSource() instanceof PlayerProfile) {
            reciever = ((PlayerProfile) e.getSource()).getOwner();
            this.selfButton.setBorder(new LineBorder(SharedResource.SIAMESE_BASE,5));
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
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Play_Click.wav");
                this.setVisible(false);
                avengerAssembleGUI.setBackdropDim(false);
                avengerAssembleGUI.playCard(card,reciever);

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

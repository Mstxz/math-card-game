package GUI.Component;

import AudioPlayer.SFXPlayer;
import GUI.Page.AvengerAssembleGUI;
import GUI.Router;
import Gameplay.Card;
import Gameplay.Player;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import utils.SharedResource;


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
        new RoundBorder(new Color(72, 62, 56), new Color(98, 86, 77), 4, 30),  // ขอบนอก (เข้ม)
        BorderFactory.createCompoundBorder(
        new RoundBorder(new Color(191, 180, 168), new Color(191, 180, 168), 12, 30), // ขอบกลาง (กลาง)
        new RoundBorder(new Color(221, 218, 210), new Color(221, 218, 210), 1, 30)   // ขอบใน (อ่อน)
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
        this.setBackground(SharedResource.SIAMESE_BASE);
        selfButton.addMouseListener(this);
        opponentButton.addMouseListener(this);
        doneButton.addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(selfButton)) {
            reciever = self;
            this.selfButton.setBorder(new LineBorder(Color.ORANGE,5));
            this.opponentButton.setBorder(null);
        }
        else if (e.getSource().equals(opponentButton)) {
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
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Card_Play_Click.wav", 0f);
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

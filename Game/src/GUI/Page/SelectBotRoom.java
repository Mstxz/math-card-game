package GUI.Page;


import GUI.Router;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import utils.SharedResource;

public class SelectBotRoom extends Page implements ActionListener {
    public SelectBotRoom() {
        super();
        JPanel panelA = new JPanel();
        JPanel panelB = new JPanel();
        JPanel panelC = new JPanel();
        JPanel panelD = new JPanel();
        JPanel panelE = new JPanel();
        JButton exit = new JButton("< Exit");
        JButton previousBotButton = new JButton("<");
        JButton nextBotButton = new JButton(">");
        JButton decksButton = new JButton("Decks");
        JButton startButton = new JButton("Start");
        JLabel chooseOpponent = new JLabel("Choose Your Opponent");
        JLabel selectingBotName = new JLabel("Bot Name");
        JLabel selectingBotProfileImage = new JLabel("Bot PFP");
        JLabel selectingBotDescription = new JLabel("Bot description for explaining its behavior, pattern, and lore add-on");

        chooseOpponent.setForeground(SharedResource.SIAMESE_BASE);
        chooseOpponent.setFont(SharedResource.getCustomSizeFont(80));
        selectingBotName.setForeground(SharedResource.SIAMESE_BASE);
        selectingBotName.setFont(SharedResource.getCustomSizeFont(40));


        exit.addActionListener(this);

        this.mainPanel.setLayout(new GridLayout(5,1));
        this.mainPanel.add(panelA);
        this.mainPanel.add(panelB);
        this.mainPanel.add(panelC);
        this.mainPanel.add(panelD);
        this.mainPanel.add(panelE);

        panelA.setLayout(new FlowLayout());
        panelA.add(exit);
        panelA.add(chooseOpponent);

        panelB.add(selectingBotName);

        panelC.add(previousBotButton);
        panelC.add(selectingBotProfileImage);
        panelC.add(nextBotButton);

        panelD.add(selectingBotDescription);

        panelE.add(decksButton);
        panelE.add(startButton);

        setButton(previousBotButton);
        setButton(nextBotButton);
        setButton(exit);
        setButton(decksButton);
        setButton(startButton);

        this.mainPanel.setVisible(true);

    }

    public void setButton(JButton button) {
        //setแล้วมันบั๊คครับ(textจะซ้อนทับเวลาHoverเมาส์ที่ปุ่ม)
        //button.setForeground(SharedResource.SIAMESE_BASE);
        //button.setFont(SharedResource.getCustomSizeFont(40));
        //button.setBackground(new Color(255, 255, 255, 0));
        //button.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void actionPerformed(ActionEvent e) {
        Router.setRoute("MainMenu",null);

    }
}

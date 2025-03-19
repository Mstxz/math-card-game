package GUI.Component;
import Gameplay.Player;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Gameplay.Number;

public class PlayerInfo extends JPanel {
    private Number hp;
    private int mana;
    private String name;
    private int deckSize;
    private Player player;

    private JLabel hpLabel;
    private JLabel hpField;
    private JLabel manaLabel;
    private JPanel manaField;
    private JLabel manaLeft;
    private JPanel hpPanel;
    private JPanel manaPanel;
    private JPanel manaZone;
    private DeckIconPanel deckIconPanel;

    private ManaIcon manaIconList[] = new ManaIcon[10];

    public PlayerInfo(Player player,boolean isUser) {
        super();
        this.player = player;
        this.hp = player.getHp();
        this.mana = player.getMana();
        this.name = player.getName();
        this.deckSize = player.getDeck().getCards().size();

        if (isUser){
            hpLabel = new JLabel("Your Hp");
            manaLabel = new JLabel("Your Mana");
        }
        else{
            hpLabel = new JLabel(name+"'s Hp");
            manaLabel = new JLabel(name+"'s Mana");
        }
        hpLabel.setAlignmentX(LEFT_ALIGNMENT);
        manaLabel.setAlignmentX(LEFT_ALIGNMENT);

        hpField = new JLabel(player.getHp().toString());
        hpField.setFont(SharedResource.getFont48());
        hpField.setBackground(SharedResource.SIAMESE_LIGHT);
        hpField.setOpaque(true);
        hpField.setBorder(new EmptyBorder(20,20,20,20));
        hpField.setAlignmentX(LEFT_ALIGNMENT);

        manaLeft = new JLabel(player.getMana()+"/"+player.getMaxMana());
        manaLeft.setForeground(SharedResource.SIAMESE_LIGHT);
        manaLeft.setBackground(SharedResource.SIAMESE_DARK);
        manaLeft.setOpaque(true);
        manaLeft.setBorder(new EmptyBorder(0,10,0,10));

        manaField = new JPanel(new BorderLayout());
        manaField.setBackground(SharedResource.SIAMESE_LIGHT);
        manaField.add(manaLeft,BorderLayout.EAST);
        manaField.setAlignmentX(LEFT_ALIGNMENT);
        manaField.setMaximumSize(new Dimension(350,50));
        manaField.setBorder(new EmptyBorder(0,10,0,0));

        manaZone = new JPanel();
        manaZone.setLayout(new GridLayout(1,10));
        manaZone.setBackground(SharedResource.SIAMESE_LIGHT);
        for (int i = 0;i<10;i++){
            if (i<=player.getMana()-1){
                manaIconList[i] = new ManaIcon(false,true);
            }
            else if (i <= player.getMaxMana() - 1){
                manaIconList[i] = new ManaIcon(true,true);
            }
            else {
                manaIconList[i] = new ManaIcon(false,false);
            }
            //manaIconList[i].setPreferredSize(new Dimension(40,40));
            manaZone.add(manaIconList[i]);
        }
        manaField.add(manaZone,BorderLayout.CENTER);

        deckIconPanel = new DeckIconPanel(player);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.hpLabel);
        JPanel tmp = new JPanel();
        tmp.setAlignmentX(LEFT_ALIGNMENT);
        tmp.setMaximumSize(new Dimension(200,110));
        tmp.setBackground(SharedResource.SIAMESE_BRIGHT);
        tmp.add(hpField);
        tmp.add(deckIconPanel);
        this.add(tmp);
        this.add(this.manaLabel);
        this.add(this.manaField);
        this.setBackground(SharedResource.SIAMESE_BRIGHT);
        this.setPreferredSize(new Dimension(350,250));

    }

    public void updateInfo(){
        this.hpField.setText(player.getHp().toString());
        this.manaLeft.setText(player.getMana()+"/"+player.getMaxMana());
        for (int i = 0;i<10;i++){
            if (i<=player.getMana()-1){
                manaIconList[i].setIsUseable(true);
                manaIconList[i].setIsUSe(false);
            }
            else if (i <= player.getMaxMana() - 1){
                manaIconList[i].setIsUseable(true);
                manaIconList[i].setIsUSe(true);
            }
            else {
                manaIconList[i].setIsUseable(false);
            }
        }
        manaField.repaint();
        deckIconPanel.repaint();
    }

    public Number getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
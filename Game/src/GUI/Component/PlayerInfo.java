package GUI.Component;
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

    //TODO:Can someone pls fix the parameter to Player
    public PlayerInfo(Number hp, int mana, String name/*,int deckSize*/) {
        super();
        this.hp = hp;
        this.mana = mana;
        this.name = name;
        this.deckSize = deckSize;

        if (name.isEmpty()){
            hpLabel = new JLabel("Your Hp");
            manaLabel = new JLabel("Your Mana");
        }
        else{
            hpLabel = new JLabel(name+"'s Hp");
            manaLabel = new JLabel(name+"'s Mana");
        }
        hpLabel.setAlignmentX(LEFT_ALIGNMENT);
        manaLabel.setAlignmentX(LEFT_ALIGNMENT);

        hpField = new JLabel(hp.toString());
        hpField.setFont(SharedResource.getFont48());
        hpField.setBackground(SharedResource.SIAMESE_LIGHT);
        hpField.setOpaque(true);
        hpField.setBorder(new EmptyBorder(20,20,20,20));
        hpField.setAlignmentX(LEFT_ALIGNMENT);

        manaLeft = new JLabel(this.mana+"/10");
        manaLeft.setForeground(SharedResource.SIAMESE_LIGHT);
        manaLeft.setBackground(SharedResource.SIAMESE_DARK);
        manaLeft.setOpaque(true);
        manaLeft.setBorder(new EmptyBorder(0,10,0,10));

        manaField = new JPanel(new BorderLayout());
        manaField.setBackground(SharedResource.SIAMESE_LIGHT);
        manaField.add(manaLeft,BorderLayout.EAST);
        manaField.setAlignmentX(LEFT_ALIGNMENT);
        manaField.setMaximumSize(new Dimension(300,50));
        manaField.setBorder(new EmptyBorder(0,10,0,0));

        manaZone = new JPanel();
        manaZone.setLayout(new GridLayout(1,10));
        manaZone.setBackground(SharedResource.SIAMESE_LIGHT);
        for (int i =0;i<10;i++){
            if (i<=mana-1){
                manaIconList[i] = new ManaIcon(false);
            }
            else {
                manaIconList[i] = new ManaIcon(true);
            }
            manaZone.add(manaIconList[i]);
        }
        manaField.add(manaZone,BorderLayout.CENTER);



        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.hpLabel);
        JPanel tmp = new JPanel();
        //tmp.add(hpField);
        this.add(this.hpField);
        this.add(this.manaLabel);
        this.add(this.manaField);
        this.setBackground(SharedResource.SIAMESE_BRIGHT);
        this.setPreferredSize(new Dimension(300,250));

    }

    public Number getHp() {
        return hp;
    }

    public void setHp(Number hp) {
        this.hp = hp;
        this.hpField.setText(hp.toString());
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
        this.manaLeft.setText(this.mana+"/10");
        for (int i = 0;i<10;i++){
            if (i<=mana-1){
                manaIconList[i].setIsUSe(false);
            }
            else {
                manaIconList[i].setIsUSe(true);
            }
        }
        manaField.repaint();
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
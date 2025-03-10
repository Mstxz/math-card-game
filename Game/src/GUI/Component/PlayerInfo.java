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
    private JLabel hpLabel;
    private JLabel hpField;
    private JLabel manaLabel;
    private JPanel manaField;
    private JLabel manaLeft;
    private JPanel hpPanel;
    private JPanel manaPanel;
    public PlayerInfo(Number hp, int mana, String name) {
        super();
        this.hp = hp;
        this.mana = mana;
        this.name = name;
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

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.hpLabel);
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
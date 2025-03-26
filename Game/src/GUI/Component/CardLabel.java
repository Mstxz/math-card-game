package GUI.Component;

import Gameplay.CardType;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Objects;

public class CardLabel extends JPanel {

    private String name;
    private int amount;
    private CardType cardType;
    private JLabel miniPicture;
    private JLabel cardNameLabel;
    private JLabel cardAmountLabel;
    public CardLabel(String name,CardType type,int amount,String cardPicture){
        super();
        cardNameLabel = new JLabel(name);
        cardAmountLabel = new JLabel(String.valueOf(amount));
        miniPicture = new JLabel(ResourceLoader.loadPicture(cardPicture,59,80));
        this.name = name;
        this.amount = amount;
        this.cardType = type;
        this.setLayout(new BorderLayout(20,0));
        this.add(cardNameLabel);
        this.add(miniPicture,BorderLayout.WEST);
        this.add(cardAmountLabel,BorderLayout.EAST);
        this.setBackground(SharedResource.SIAMESE_LIGHT);
        this.setPreferredSize(new Dimension(300,100));
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.cardAmountLabel.setText(String.valueOf(amount));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardLabel cardLabel)) return false;
        return Objects.equals(name, cardLabel.name) && cardType == cardLabel.cardType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cardType);
    }
}

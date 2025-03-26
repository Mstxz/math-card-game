package GUI.Component;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Arrays;

import Gameplay.Card;
import Gameplay.CardType;
import utils.ResourceLoader;

public class CardButton extends JPanel implements MouseListener {
    protected String name;
    protected int amount = 0;
    protected CardLabel cardLabel = null;
    protected TempDeckZone deckZonePanel;
    protected String cardPicture;
    protected CardType cardType;

    private int index;

    private JPanel panel1;

    protected JButton addButton;
    protected JLabel nameButton;
    protected JButton removeButton;

    public CardButton(String name,TempDeckZone deckZonePanel){
        super();
        this.deckZonePanel = deckZonePanel;
        addButton = new JButton("+");
        nameButton = new JLabel();
        String[] temp = name.split(" ");
        Card tempCard;
        if (temp.length == 3){
            this.name = temp[0] + " " + temp[1];
            //System.out.println(temp[0]+"/"+temp[1]);
            tempCard = Card.createCard(temp[0],Integer.valueOf(temp[1]),temp[2]);
        }
        else {
            this.name = name;
            tempCard = Card.createCard(temp[0]);
        }
        cardType = tempCard.getType();
        nameButton.setIcon(ResourceLoader.loadPicture(tempCard.getPicture(),200,250));
        cardPicture = tempCard.getPicture();
        cardLabel = new CardLabel(tempCard,0);
        deckZonePanel.registerButton(cardLabel,this);
        removeButton = new JButton("-");

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,2));
        panel1.add(addButton);
        panel1.add(removeButton);

        this.setLayout(new BorderLayout());
        this.add(nameButton,BorderLayout.CENTER);
        this.addMouseListener(this);

        this.setSize(200,280);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public JLabel getNameButton() {
        return nameButton;
    }

    public void setNameButton(JLabel nameButton) {
        this.nameButton = nameButton;
    }

    public CardLabel getCardLabel() {
        return cardLabel;
    }

    public void setCardLabel(CardLabel cardLabel) {
        this.cardLabel = cardLabel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3){
            if (cardLabel.getAmount() == 0){
                return;
            }
            cardLabel.setAmount(cardLabel.getAmount() - 1);
            if (cardLabel.getAmount() == 0){
                deckZonePanel.removeCard(cardLabel);
                deckZonePanel.revalidate();
                deckZonePanel.repaint();
                return;
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON1){
            if (cardLabel.getAmount() == 0){
                deckZonePanel.addCard(cardLabel);
                index = deckZonePanel.getAllCardLabel().size()-1;
            }
            cardLabel.setAmount(cardLabel.getAmount() + 1);
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
}

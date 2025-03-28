package GUI.Component;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Arrays;

import AudioPlayer.SFXPlayer;
import AudioPlayer.SFXSwitcher;
import Gameplay.Card;
import Gameplay.CardType;
import utils.ResourceLoader;

public class CardButton extends JPanel implements MouseListener,Comparable {
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

        nameButton.setBackground(nameButton.getParent().getBackground());
        this.setSize(200,280);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(this.getParent().getBackground());
        repaint();
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
            SFXSwitcher.deckSelectSwitcher(cardLabel.getName(), e);
            if (cardLabel.getAmount() == 0){
                deckZonePanel.removeCard(cardLabel);
                deckZonePanel.revalidate();
                deckZonePanel.repaint();
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON1){
            SFXSwitcher.deckSelectSwitcher(cardLabel.getName(), e);
            if (cardLabel.getAmount() == 0){
                deckZonePanel.addCard(cardLabel);
                index = deckZonePanel.getAllCardLabel().size()-1;

            }
            cardLabel.setAmount(cardLabel.getAmount() + 1);
            deckZonePanel.revalidate();
            deckZonePanel.repaint();
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

    @Override
    public int compareTo(Object o) {
        //System.out.println(this.getCardLabel().getCard().getName());
        if ((this.getCardLabel().getCard().getName().contains("Minus") && ((CardButton)(o)).getCardLabel().getCard().getName().contains("Minus")))
        {
            int a1 = Integer.valueOf(this.getCardLabel().getCard().getName().split(" ")[1]);
            int a2 = Integer.valueOf(((CardButton)(o)).getCardLabel().getCard().getName().split(" ")[1]);
            System.out.println(a1+" "+a2);
            //return Integer.compare(a1,a2);
        }
        if ((this.getCardLabel().getCard().getName().contains("Plus") && ((CardButton)(o)).getCardLabel().getCard().getName().contains("Plus"))){
            int a1 = Integer.valueOf(this.getCardLabel().getCard().getName().split(" ")[1]);
            int a2 = Integer.valueOf(((CardButton)(o)).getCardLabel().getCard().getName().split(" ")[1]);
            //System.out.println(a1+a2+"!");
            return Integer.compare(a1,a2);
        }
        return this.getCardLabel().getCard().getName().compareTo(((CardButton)(o)).getCardLabel().getCard().getName());
    }
}

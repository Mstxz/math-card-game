package GUI.Component;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.Arrays;

import AudioPlayer.SFXPlayer;
import AudioPlayer.SFXSwitcher;
import GUI.Page.DeckCreatorPage;
import Gameplay.Card;
import Gameplay.CardType;
import utils.ResourceLoader;
import utils.SharedResource;

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

    private ImageIcon img;
    private BufferedImage buffImg;

    private boolean isEnter = false;

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
            tempCard = Card.createCard(temp[0],Integer.parseInt(temp[1]),temp[2]);
        }
        else if (temp.length == 2){
            this.name = temp[0]+" "+temp[1];
            tempCard = Card.createCard(temp[0],temp[1]);
        }
        else {
            this.name = name;
            tempCard = Card.createCard(temp[0]);
        }
        cardType = tempCard.getType();
        img = ResourceLoader.loadPicture(tempCard.getPicture(),200,250);
        buffImg = ResourceLoader.loadBufferedPicture(img.getImage());
        nameButton.setIcon(img);
        cardPicture = tempCard.getPicture();
        cardLabel = new CardLabel(tempCard,0);
        deckZonePanel.registerButton(cardLabel,this);
        removeButton = new JButton("-");

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,2));
        panel1.add(addButton);
        panel1.add(removeButton);

        this.setLayout(new FlowLayout());
        this.add(nameButton,BorderLayout.CENTER);
        this.addMouseListener(this);

        nameButton.setBackground(nameButton.getParent().getBackground());
        this.setSize(200,280);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g2);
        if (isEnter){

//            g2.setColor(SharedResource.SKYBLUE_BASE);
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER).derive(0.3f));
//            g2.fillRoundRect(0,0,getWidth(),getHeight(),20,20);

            g2.setColor(SharedResource.SIAMESE_DARK);
            g2.setStroke(new BasicStroke(7));
            g2.drawRoundRect(3,3,getWidth()-7,getHeight()-7,20,20);
        }
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
        if (e.getButton() == MouseEvent.BUTTON3){ //right click
            if (cardLabel.getAmount() == 0){
                return;
            }
            cardLabel.setAmount(cardLabel.getAmount() - 1);
            DeckCreatorPage.showCardAmount.setCardAmount(DeckCreatorPage.showCardAmount.getCardAmount()-1);
            SFXSwitcher.deckSelectSwitcher(cardLabel.getName(), e);
            if (cardLabel.getAmount() == 0){
                deckZonePanel.removeCard(cardLabel);
                deckZonePanel.revalidate();
                deckZonePanel.repaint();
            }
        }
        else if (e.getButton() == MouseEvent.BUTTON1){ // left click
            SFXSwitcher.deckSelectSwitcher(cardLabel.getName(), e);
            if (DeckCreatorPage.showCardAmount.getCardAmount() == CardShowDeckZone.MAX_CARD){
                return;
            }
            if (cardLabel.getAmount() == 0){
                deckZonePanel.addCard(cardLabel);
                index = deckZonePanel.getAllCardLabel().size()-1;
            }
            if (cardLabel.getAmount() < 3){
                cardLabel.setAmount(cardLabel.getAmount() + 1);
                DeckCreatorPage.showCardAmount.setCardAmount(DeckCreatorPage.showCardAmount.getCardAmount()+1);
            }
            deckZonePanel.revalidate();
            deckZonePanel.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isEnter = true;
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isEnter = false;
        repaint();
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
            //System.out.println(a1+" "+a2);
            return Integer.compare(a1,a2);
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

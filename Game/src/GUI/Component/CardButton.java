package GUI.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gameplay.Card;
import utils.ResourceLoader;

public class CardButton extends JPanel implements ActionListener {
    protected String name;
    protected int amount = 0;
    protected CardLabel cardLabel = null;
    protected TempDeckZone deckZonePanel;
    protected String cardPicture;

    private int index;

    private JPanel panel1;

    protected JButton addButton;
    protected JLabel nameButton;
    protected JButton removeButton;

    public CardButton(String name,TempDeckZone deckZonePanel){
        super();
        this.name = name;
        this.deckZonePanel = deckZonePanel;

        addButton = new JButton("+");
        nameButton = new JLabel();
        String[] temp = name.split(" ");
        for (String t : temp){
            System.out.println(t);
        }
        Card tempCard;
        if (temp.length == 3){

            //System.out.println(temp[0]+"/"+temp[1]);
            tempCard = Card.createCard(temp[0],Integer.valueOf(temp[2]),temp[1]);
        }
        else {
            tempCard = Card.createCard(temp[0]);
        }
        nameButton.setIcon(ResourceLoader.loadPicture(tempCard.getPicture(),200,200));
        removeButton = new JButton("-");

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1,2));
        panel1.add(addButton);
        panel1.add(removeButton);

        this.setLayout(new BorderLayout());
        this.add(nameButton,BorderLayout.CENTER);
        this.add(panel1,BorderLayout.SOUTH);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);

        this.setSize(200,this.getHeight());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)){
            if (amount==0){
                cardLabel = new CardLabel(this.name,0);
                deckZonePanel.add(cardLabel);
                deckZonePanel.getAllCardLabel().add(cardLabel);
                index = deckZonePanel.getAllCardLabel().size()-1;
            }
            amount+=1;
            cardLabel.setText(this.name+" : "+amount);
        }
        else if (e.getSource().equals(removeButton)) {
            if (amount==0){
                return;
            }
            amount-=1;
            System.out.println(amount);
            if (amount==0){
                deckZonePanel.removeCard(cardLabel);
                deckZonePanel.revalidate();
                deckZonePanel.repaint();
//                for (int i = 0;i<DeckCreater.p1CardLabel.size();i++){
//                    DeckCreater.p1CardLabel.get(i).repaint();
//                }
                return;
            }
            cardLabel.setText(this.name+" : "+amount);
        }
        cardLabel.setAmount(this.amount);
        //this.amount = cardLabel.amount;
    }
}

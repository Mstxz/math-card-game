package GUI.Component;

import Gameplay.Card;

import javax.swing.*;
import java.util.*;

public class TempDeckZone extends JPanel {
    public HashSet<CardLabel> allCardLabel = new HashSet<CardLabel>();
    public HashMap<CardLabel,CardButton> cardLabelHashMap = new HashMap<>();
    public TempDeckZone(){
        super();
    }

    public void registerButton(CardLabel c, CardButton owner){
        cardLabelHashMap.put(c,owner);
    }

    public void addCard(CardLabel c){
        allCardLabel.add(c);
        this.add(c);
    }

    public void removeCard(CardLabel c){
        this.remove(c);
        this.allCardLabel.remove(c);
    }

    public HashSet<CardLabel> createCardLabelSet(HashMap<Card,Integer> cardIntegerHashMap){
        HashSet<CardLabel> tmp = new HashSet<CardLabel>();
        Iterator i = cardIntegerHashMap.keySet().iterator();
        while (i.hasNext()){
            Card j = (Card)(i.next());
            tmp.add(new CardLabel(j,cardIntegerHashMap.get(j)));
        }
        return tmp;
    }

    public HashSet<CardLabel> getAllCardLabel() {
        return allCardLabel;
    }

    public void setAllCardLabel(HashSet<CardLabel> allCardLabel) {
        for (CardLabel c:this.allCardLabel){
            c.setAmount(0);
        }
        this.removeAll();
        if (allCardLabel.isEmpty()){
            return;
        }
        for (CardLabel c:allCardLabel){
            CardButton cardButton = cardLabelHashMap.get(c);
            cardButton.setCardLabel(c);
        }
        this.allCardLabel = allCardLabel;
    }

    public void update(){
        this.removeAll();
        Iterator i = allCardLabel.iterator();
        while (i.hasNext()){
            this.add((CardLabel)i.next());
        }
    }
}

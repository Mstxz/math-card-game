package GUI.Component;

import Gameplay.Card;

import javax.swing.*;
import java.util.*;

public class TempDeckZone extends JPanel {
    public HashSet<CardLabel> allCardLabel = new HashSet<CardLabel>();
    public TempDeckZone(){
        super();
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
            tmp.add(new CardLabel(j.getName(),j.getType(),cardIntegerHashMap.get(j),j.getPicture()));
        }
        return tmp;
    }

    public HashSet<CardLabel> getAllCardLabel() {
        return allCardLabel;
    }

    public void setAllCardLabel(HashSet<CardLabel> allCardLabel) {
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

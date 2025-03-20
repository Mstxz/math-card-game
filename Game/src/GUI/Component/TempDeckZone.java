package GUI.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class TempDeckZone extends JPanel {
    private HashSet<CardLabel> allCardLabel = new HashSet<CardLabel>();
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
    public HashSet<CardLabel> getAllCardLabel() {
        return allCardLabel;
    }

    public void setAllCardLabel(HashSet<CardLabel> allCardLabel) {
        this.allCardLabel = allCardLabel;
    }


}

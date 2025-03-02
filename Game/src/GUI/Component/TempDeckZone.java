package GUI.Component;

import javax.swing.*;
import java.util.ArrayList;

public class TempDeckZone extends JPanel {
    private ArrayList<CardLabel> allCardLabel = new ArrayList<CardLabel>();

    public TempDeckZone(){
        super();
    }

    public ArrayList<CardLabel> getAllCardLabel() {
        return allCardLabel;
    }

    public void setAllCardLabel(ArrayList<CardLabel> allCardLabel) {
        this.allCardLabel = allCardLabel;
    }
}

package GUI.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CardLabel extends JLabel{
    private String name;
    private int amount;
    public CardLabel(String name,int amount){
        super(name+" : "+amount);
        this.name = name;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

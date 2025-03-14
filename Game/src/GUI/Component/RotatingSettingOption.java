package GUI.Component;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//Component for switching option in SettingPage
public class RotatingSettingOption extends JLabel implements MouseListener {
    private ArrayList<String> optionList;
    private int currentIndex;

    public RotatingSettingOption(ArrayList<String> optionList){
        this(optionList,0);
    }

    public RotatingSettingOption(ArrayList<String> optionList,int currentIndex){
        this.optionList = optionList;
        this.setText(optionList.get(currentIndex));
        this.addMouseListener(this);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentIndex = (currentIndex+1)%optionList.size();
        this.setText(optionList.get(currentIndex));
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

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
        this.optionList = optionList;
        currentIndex = 0;
        this.setText(optionList.get(currentIndex));
        this.addMouseListener(this);
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

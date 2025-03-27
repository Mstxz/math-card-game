package GUI.Component;

import AudioPlayer.SFXPlayer;
import utils.ResourceLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

//Component for switching option in SettingPage
public class RotatingSettingOption extends JPanel implements MouseListener {
    private ArrayList<String> optionList;
    private int currentIndex;
    private JLabel leftArrow;
    private JLabel rightArrow;
    private JLabel currentOptionText;
    public RotatingSettingOption(ArrayList<String> optionList){
        this(optionList,0);
    }

    public RotatingSettingOption(ArrayList<String> optionList,int currentIndex){
        this.currentIndex = currentIndex;
        leftArrow = new JLabel(ResourceLoader.loadPicture("assets/LeftArrow.png"));
        rightArrow = new JLabel(ResourceLoader.loadPicture("assets/RightArrow.png"));
        currentOptionText = new JLabel(optionList.get(currentIndex));
        currentOptionText.setHorizontalAlignment(SwingConstants.CENTER);
        this.optionList = optionList;

        leftArrow.addMouseListener(this);
        rightArrow.addMouseListener(this);
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(0,25,0,20));
        this.setLayout(new BorderLayout());
        this.add(leftArrow,BorderLayout.WEST);
        this.add(currentOptionText);
        this.add(rightArrow,BorderLayout.EAST);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(rightArrow)){
            if ( currentIndex != optionList.size() -1){
                currentIndex += 1;
            }
        } else if (e.getSource().equals(leftArrow)) {
            if (currentIndex != 0){
                currentIndex -= 1;
            }
        }
        this.currentOptionText.setText(optionList.get(currentIndex));
        SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
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

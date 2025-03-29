package GUI.Component;

import AudioPlayer.SFXPlayer;
import utils.ResourceLoader;
import utils.SharedResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class FilterButton extends JButton implements ActionListener {
    private HashSet<CardButton> cardButtons;
    private boolean isSelected = false;
    private BufferedImage img;

    public FilterButton(String name,String icon){
        super(name);
        img = ResourceLoader.loadBufferedPicture(ResourceLoader.loadPicture(icon,65,67).getImage());
        this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.addActionListener(this);
        cardButtons = new HashSet<CardButton>();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (!isSelected){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        }
        else {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
        }
        g2.drawImage((BufferedImage) img,0,0,img.getWidth(),img.getHeight(),null);
    }

    public void update(boolean isSelected){
        this.isSelected = isSelected;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof FilterButton) {
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
            FilterZone.update((FilterButton) e.getSource());
        }
    }

    public HashSet<CardButton> getCardButtons() {
        return cardButtons;
    }

    public void setCardButtons(HashSet<CardButton> cardButtons) {
        this.cardButtons = cardButtons;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

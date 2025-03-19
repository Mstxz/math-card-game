package GUI.Component;

import utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class ManaIcon extends JPanel {
    private static Image mana = ResourceLoader.loadPicture("assets/Component/ManaIcon.png").getImage();
    private boolean isUse = false;
    private boolean isUseable = true;

    public ManaIcon(boolean isUse,boolean isUseable){
        this.isUse = isUse;
        this.isUseable = isUseable;
        this.repaint();
    }

    public ManaIcon(boolean isUse){
        this.isUse = isUse;
        this.repaint();
    }

    public void setIsUSe(boolean isUse){
        this.isUse = isUse;
        this.repaint();
    }

    public void setIsUseable(boolean isUseable){
        this.isUseable = isUseable;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!isUseable){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0));
        }
        else if (isUse){

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
        }
        else{

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
        }
        //System.out.println(this.getWidth()+", " + this.getHeight());
        int availableSize = Math.min(this.getWidth(),this.getHeight());
        g2.drawImage(mana,(this.getWidth() - availableSize) / 2,(this.getHeight() - availableSize) / 2, availableSize, availableSize,this);
    }
}

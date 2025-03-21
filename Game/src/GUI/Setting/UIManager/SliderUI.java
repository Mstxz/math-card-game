package GUI.Setting.UIManager;

import utils.ResourceLoader;

import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class SliderUI extends BasicSliderUI {

    @Override
    public void paintThumb(Graphics g) {
        //super.paintThumb(g);
        Image img = ResourceLoader.loadPicture("assets/Component/PawSlider.png",30,30).getImage();
        g.drawImage(img,xPositionForValue(slider.getValue())-img.getWidth(null)/2,slider.getHeight()/2 - img.getHeight(null)/2,null);
    }

    @Override
    protected Dimension getThumbSize() {
        Image img = ResourceLoader.loadPicture("assets/Component/PawSlider.png",50,50).getImage();
        return new Dimension(img.getWidth(null),img.getHeight(null));
    }

    @Override
    public void paintTrack(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(trackRect.x, trackRect.y + (trackRect.height - 10) / 2, trackRect.width, 10);
        g.setColor(Color.WHITE);
        g.fillRect(trackRect.x  , trackRect.y + (trackRect.height - 10) / 2,
                valueForXPosition(xPositionForValue(slider.getValue()))*trackRect.width/100, 10);
        //System.out.println(valueForXPosition(xPositionForValue(slider.getValue()))+" "+xPositionForValue(slider.getValue()));
    }
}

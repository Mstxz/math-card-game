package GUI.Component;

import GUI.Page.OverlayPlacement;
import GUI.Page.Page;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.ButtonUI;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SlideShow extends JPanel implements MouseListener, ActionListener {
    protected Page page;
    protected ArrayList<BufferedImage> slides;
    protected int currentIndex;
    protected JLabel leftArrow;
    protected JLabel currentSlide;
    protected JLabel rightArrow;
    protected JButton closeButton;
    public SlideShow(Page page, ArrayList<String> slidesPath){
        this.page = page;
        currentIndex = 0;
        slides = new ArrayList<>();
        for (String s : slidesPath) {
            slides.add(ResourceLoader.loadBufferedPicture(s));
        }
        currentSlide = new JLabel(new ImageIcon(slides.get(currentIndex)));
        leftArrow = new JLabel(ResourceLoader.loadPicture("assets/LeftArrow.png"));
        rightArrow = new JLabel(ResourceLoader.loadPicture("assets/RightArrow.png"));
        closeButton = new JButton("Close");
        closeButton.setUI(new ButtonUI());
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(SharedResource.SIAMESE_BRIGHT);
        closeButton.setPreferredSize(new Dimension(300,60));

        bottomPanel.add(closeButton);

        setLayout(new BorderLayout(5,0));
        add(leftArrow,BorderLayout.WEST);
        add(currentSlide,BorderLayout.CENTER);
        add(rightArrow,BorderLayout.EAST);
        add(bottomPanel,BorderLayout.SOUTH);

        setBorder(new EmptyBorder(10,30,10,30));
        setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,30,30));
        setSize(1600,840);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(rightArrow)){
            if (currentIndex != slides.size() -1){
                currentIndex += 1;
            }
        } else if (e.getSource().equals(leftArrow)) {
            if (currentIndex != 0){
                currentIndex -= 1;
            }
        }
        currentSlide.setIcon(new ImageIcon(slides.get(currentIndex)));
        currentSlide.revalidate();
        currentSlide.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(closeButton)){
            this.setVisible(false);
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag){
            leftArrow.addMouseListener(this);
            rightArrow.addMouseListener(this);
            closeButton.addActionListener(this);
            page.showOverlay(this, OverlayPlacement.CENTER);
            page.setBackdropDim(true);
        }
        else{
            leftArrow.removeMouseListener(this);
            rightArrow.removeMouseListener(this);
            closeButton.removeActionListener(this);
            page.setBackdropDim(false);
            page.removeOverlay(this);
        }
        this.revalidate();
        this.repaint();

    }
}

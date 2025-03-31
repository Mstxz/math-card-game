package GUI.Component;

import AudioPlayer.SFXPlayer;
import GUI.Page.Page;
import utils.SharedResource;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class MenuOverlay extends JPanel implements KeyListener, MouseListener {
    private final KeyListener tmp;
    private final Page        page;

    public MenuOverlay(Page page, LayoutManager layout){
        super();
        this.page = page;
        this.tmp = page.getMainFrame().getKeyListeners()[0];

        this.page.getMainFrame().removeKeyListener(tmp);
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setOpaque(false);
        this.setLayout(layout);
        this.setSize(new Dimension(1000, 600));
        this.setPreferredSize(new Dimension(1000, 600));
        this.setMinimumSize(new Dimension(600, 400));
        this.setMaximumSize(new Dimension(1200, 600));

        this.addMouseListener(this);
        this.page.getOverlayPanel().addMouseListener(this);
        page.getMainFrame().addKeyListener(this);
        this.setVisible(true);
    }

    public MenuOverlay(Page page){
        super();
        this.page = page;
        this.tmp = page.getMainFrame().getKeyListeners()[0];

        this.page.getMainFrame().removeKeyListener(tmp);
        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setSize(new Dimension(1000, 600));
        this.setPreferredSize(new Dimension(1000, 600));
        this.setMinimumSize(new Dimension(600, 400));
        this.setMaximumSize(new Dimension(1200, 600));

        this.addMouseListener(this);
        this.page.getOverlayPanel().addMouseListener(this);
        page.getMainFrame().addKeyListener(this);
        this.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
        } else if (e.getSource().equals(this.page.getOverlayPanel())) {
            setVisible(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    private void clearMenuAction() {
        page.getMainFrame().removeKeyListener(this);
        this.removeMouseListener(this);
        this.page.getOverlayPanel().removeMouseListener(this);
        page.getMainFrame().addKeyListener(tmp);
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (!aFlag)
            clearMenuAction();
        super.setVisible(aFlag);
        page.setBackdropDim(aFlag);
        page.removeOverlay(this);
        page.getMainPanel().updateUI();
    }
}

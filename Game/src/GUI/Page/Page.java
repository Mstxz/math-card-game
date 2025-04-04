package GUI.Page;

import GUI.Router;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Page {
    protected String title;
    protected JPanel mainPanel;
    protected JPanel overlayPanel;
    protected JFrame mainFrame;
    protected static MouseListener mouseListener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    };
    protected Page(){
        this.title = "Purr-fect Equations";
        this.mainPanel = new JPanel();
        mainFrame = Router.getMainFrame();
        mainPanel = new JPanel();
        overlayPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(getBackground());
                g.fillRect(0,0,getWidth(),getHeight());
            }
        };
        overlayPanel.setLayout(null);
        overlayPanel.setBounds(0,0,mainPanel.getWidth(),mainPanel.getHeight());
        overlayPanel.setBackground(new Color(0,0,0,0));
        overlayPanel.setOpaque(false);
        setupMainPanel();
    }

    protected void setupMainPanel(){
        mainPanel.setBounds(0,0,mainFrame.getWidth(),mainFrame.getHeight());
        overlayPanel.setBounds(0,0,mainPanel.getWidth(),mainPanel.getHeight());
    }

    public void showOverlay(Component c, int x , int y, int width,int height){
        c.setBounds(x,y,width,height);
        overlayPanel.add(c);
    }

    public void showOverlay(Component c, OverlayPlacement placement){
        int x = 0,y = 0;
        int midX = (mainFrame.getWidth() - c.getWidth())/2;
        int midY = (mainFrame.getHeight() - c.getHeight())/2;
        switch (placement){
            case TOP:
                x = midX;
                break;
            case LEFT:
                y = midY;
                break;
            case RIGHT:
                x = mainFrame.getWidth() - c.getWidth();
                y = midY;
                break;
            case BOTTOM:
                x = midX;
                y = mainFrame.getHeight() - c.getHeight();
                break;
            case CENTER:
                x = midX;
                y = midY;
                break;
            case TOP_LEFT:
                break;
            case TOP_RIGHT:
                x = mainFrame.getWidth() - c.getWidth();
                break;
            case BOTTOM_LEFT:
                y = mainFrame.getHeight() - c.getHeight();
                break;
            case BOTTOM_RIGHT:
                x = mainFrame.getWidth() - c.getWidth();
                y = mainFrame.getHeight() - c.getHeight();
                break;
        }
        c.setLocation(x,y);
        overlayPanel.add(c);
    }

    public void removeOverlay(Component c){
        overlayPanel.remove(c);
    }

    public void clearOverlay(){
        overlayPanel.removeAll();
        overlayPanel.setLayout(null);
        this.setBackdropDim(false);
    }

    public void setBackdropDim(boolean dimBackground){
        if (dimBackground){
            overlayPanel.setBackground(new Color(0,0,0,100));
            overlayPanel.addMouseListener(mouseListener);
        }
        else{
            overlayPanel.setBackground(new Color(0,0,0,0));
            overlayPanel.removeMouseListener(mouseListener);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getOverlayPanel() {
        return overlayPanel;
    }

    public void setOverlayPanel(JPanel overlayPanel) {
        this.overlayPanel = overlayPanel;
    }


}

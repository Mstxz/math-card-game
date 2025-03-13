package GUI.Page;

import GUI.Router;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class Page {
    protected String title;
    protected JPanel mainPanel;
    protected JPanel overlayPanel;
    protected JFrame mainFrame;
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

    protected void showOverlay(Component c, int x , int y, int width,int height){
        c.setBounds(x,y,width,height);
        overlayPanel.add(c);
    }

    protected void removeOverlay(Component c){
        overlayPanel.remove(c);
    }
    protected void setBackdropDim(boolean dimBackground){
        if (dimBackground){
            overlayPanel.setBackground(new Color(0,0,0,100));
        }
        else{
            overlayPanel.setBackground(new Color(0,0,0,0));
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

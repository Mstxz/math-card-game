package GUI.Page;

import GUI.Router;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class Page {
    protected String title;
    protected JPanel mainPanel;
    protected JFrame mainFrame;
    protected JLayeredPane layeredPane;
    protected JPanel frontPanel;
    protected Page(){
        this.title = "Math Card Game";
        this.mainPanel = new JPanel();
        this.frontPanel = new JPanel();
        mainFrame = Router.getMainFrame();
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

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public void setLayeredPane(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
    }

    public void displayDialog(){
        layeredPane.add(frontPanel);
    }
    public void closeDialog(){
        layeredPane.remove(frontPanel);
    }
    public void setUpLayeredPane(){
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setBounds(0,0,mainFrame.getWidth(),mainFrame.getHeight());
        mainPanel.setBounds(0,0,mainFrame.getWidth(),mainFrame.getHeight());
        layeredPane.removeAll();
        layeredPane.add(mainPanel,JLayeredPane.DEFAULT_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }
}

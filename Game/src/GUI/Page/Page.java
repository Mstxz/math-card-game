package GUI.Page;

import GUI.Router;

import javax.swing.*;

public abstract class Page {
    protected String title;
    protected JPanel mainPanel;
    protected JFrame mainFrame;
    protected Page(){
        this.title = "Math Card Game";
        this.mainPanel = new JPanel();
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
}

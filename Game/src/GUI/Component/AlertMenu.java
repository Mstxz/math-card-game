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

public class AlertMenu extends JPanel implements ActionListener {
    private Page page;
    private JLabel title;
    private JPanel buttonPanel;
    private JButton closeButton;
    private JLabel klongLabel;

    public AlertMenu(Page page){
        this(page,"Are You Sure?");
    }

    public AlertMenu(Page page, String headerText){
        this(page,headerText,450,340);
    }

    public AlertMenu(Page page, String headerText, int width, int height){
        super(new BorderLayout(0,20));
        this.page = page;
        this.title = new JLabel(headerText);
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
        this.title.setFont(SharedResource.getCustomSizeFont(36));

        this.klongLabel = new JLabel(ResourceLoader.loadPicture("assets/KlongAlert.webp",333,200));

        this.closeButton = new JButton("Close");
        this.closeButton.setUI(new ButtonUI());
        this.closeButton.addActionListener(this);
        this.closeButton.setPreferredSize(new Dimension(150,60));

        this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        this.buttonPanel.add(closeButton);
        this.buttonPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,30,30));
        this.add(title,BorderLayout.NORTH);
        this.add(klongLabel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
        this.setBorder(new EmptyBorder(20,20,20,20));
        this.setSize(width,height);
    }

    public void setTitle(String titleText){
        title.setText(titleText);
        title.repaint();
    }

    public void setNewSize(int width, int height) {
        this.setSize(width,height);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag){
            page.showOverlay(this, OverlayPlacement.CENTER);
            page.setBackdropDim(true);
            this.requestFocus();
        }
        else{
            page.setBackdropDim(false);
            page.removeOverlay(this);
        }
        this.revalidate();
        this.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(closeButton)){
            this.setVisible(false);
        }
    }
}
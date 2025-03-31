package GUI.Component;

import GUI.Page.AvengerAssembleGUI;
import GUI.Page.OverlayPlacement;
import GUI.Page.Page;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.ButtonUI;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmMenu extends JPanel implements ActionListener {
    private Page page;
    private final int WIDTH = 450;
    private final int HEIGHT = 340;
    private JLabel title;
    private JPanel buttonPanel;
    private JButton yesButton;
    private JButton noButton;
    private JLabel klongLabel;

    public ConfirmMenu(Page page){
        this(page,"Are You Sure?");
    }

    public ConfirmMenu(Page page,String headerText){
        super(new BorderLayout(0,20));
        this.page = page;
        this.title = new JLabel(headerText);
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
        this.title.setFont(SharedResource.getCustomSizeFont(36));
        this.title.setBounds(460,240,100,60);

        this.klongLabel = new JLabel(ResourceLoader.loadPicture("assets/KlongQuit.webp",233,156));

        this.yesButton = new JButton("Yes");
        this.yesButton.setUI(new ButtonUI());
        this.yesButton.addActionListener(this);
        this.yesButton.setPreferredSize(new Dimension(150,60));

        this.noButton = new JButton("No");
        this.noButton.setUI(new ButtonUI());
        this.noButton.addActionListener(this);
        this.noButton.setPreferredSize(new Dimension(150,60));

        this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        this.buttonPanel.add(yesButton);
        this.buttonPanel.add(noButton);
        this.buttonPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        this.setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,30,30));
        this.add(title,BorderLayout.NORTH);
        this.add(klongLabel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
        this.setBorder(new EmptyBorder(20,20,20,20));
        this.setSize(WIDTH,HEIGHT);
    }

    public void setTitle(String titleText){
        title.setText(titleText);
        title.repaint();
    }

    public void onConfirm(){

    }

    public void onDenied(){

    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag){
            page.showOverlay(this, OverlayPlacement.CENTER);
            page.setBackdropDim(true);
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
        if (e.getSource().equals(yesButton)){
            this.setVisible(false);
            onConfirm();
        }
        else{
            this.setVisible(false);
            onDenied();
        }
    }
}
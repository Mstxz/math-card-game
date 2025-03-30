package GUI.Component;

import GUI.Page.AvengerAssembleGUI;
import utils.ResourceLoader;
import utils.SharedResource;
import utils.UIManager.ButtonUI;
import utils.UIManager.RoundPanelUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel implements ActionListener {
    private AvengerAssembleGUI gameGUI;
    private final int WIDTH = 450;
    private final int HEIGHT = 340;
    private JLabel title;
    private JPanel buttonPanel;
    private JButton yesButton;
    private JButton noButton;
    private JLabel klongLabel;
    public GameMenu(AvengerAssembleGUI gameGUI){
        this.gameGUI = gameGUI;
        title = new JLabel("Want to quit?");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(SharedResource.getCustomSizeFont(36));

        klongLabel = new JLabel(ResourceLoader.loadPicture("assets/KlongQuit.webp",233,156));

        yesButton = new JButton("Yes");
        yesButton.setUI(new ButtonUI());
        yesButton.addActionListener(this);
        yesButton.setPreferredSize(new Dimension(150,60));

        noButton = new JButton("No");
        noButton.setUI(new ButtonUI());
        noButton.addActionListener(this);
        noButton.setPreferredSize(new Dimension(150,60));

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.setBackground(SharedResource.SIAMESE_BRIGHT);

        setLayout(new BorderLayout(0,20));
        setUI(new RoundPanelUI(SharedResource.SIAMESE_BRIGHT,30,30));
        add(title,BorderLayout.NORTH);
        add(klongLabel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        setBorder(new EmptyBorder(20,20,20,20));
        setSize(WIDTH,HEIGHT);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag){
            gameGUI.setBackdropDim(true);
        }
        else{
            gameGUI.setBackdropDim(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(yesButton)){
            this.setVisible(false);
            gameGUI.quitGame();
        }
        else{
            this.setVisible(false);
        }
    }
}

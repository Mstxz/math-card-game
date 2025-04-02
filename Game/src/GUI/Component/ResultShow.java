package GUI.Component;

import AudioPlayer.SFXPlayer;
import GUI.Router;
import utils.SharedResource;
import utils.UIManager.ButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class ResultShow extends JPanel implements ActionListener {
    private  JPanel banner;
    private String displayText;
    private JButton returnButton = new JButton("Return to Menu");

    public ResultShow(String displayText) {
        banner = new JPanel();
        returnButton.setUI(new ButtonUI());
        returnButton.setPreferredSize(new Dimension(350,80));
        this.displayText = displayText;
        JLabel bigText = new JLabel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setFont(SharedResource.getCustomSizeFont(100));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int x = 35;
                int y = 90;
                g2d.setColor(SharedResource.SIAMESE_BRIGHT);
                for(int dx = -5 ;dx <= 5 ;dx++){
                    for(int dy = -5 ;dy <= 5 ;dy++){
                        g2d.drawString(displayText, x+dx,y+dy);
                    }
                }
                g2d.setColor(SharedResource.SIAMESE_LIGHT);
                g2d.drawString(displayText, x,y);
            }
        };

        banner.setBackground(SharedResource.SIAMESE_DARK);
        banner.setBorder(new MatteBorder(2,0,2,0,SharedResource.SIAMESE_BRIGHT));
        banner.setBounds(0,Router.getMainFrame().getHeight()/2-150,Router.getMainFrame().getWidth(),300);
        bigText.setBounds((Router.getMainFrame().getWidth()-400)/2,banner.getY()-50,400,200);

        //System.out.println(resultText);
        this.setLayout(null);
        this.add(bigText);
        this.add(banner);

        banner.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(SharedResource.SIAMESE_DARK);
        buttonPanel.add(returnButton);

        banner.add(buttonPanel,BorderLayout.SOUTH);
        buttonPanel.setBorder(new EmptyBorder(0,0,30,0));

        returnButton.setBackground(SharedResource.SIAMESE_DARK);

        returnButton.addActionListener(this);
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == returnButton){
            SFXPlayer.playSound("Game/src/assets/Audio/SFX/Button_Click.wav");
            Router.setRoute("MainMenu", null);
        }
    }
}

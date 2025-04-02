package GUI.Component;

import Gameplay.Card;
import Gameplay.Deck;
import Gameplay.Player;
import utils.SharedResource;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static utils.ResourceLoader.*;

public class DeckIconPanel extends JPanel {
    private Player player;
    private Image deckIcon;
    private int cardCount;

    public DeckIconPanel(Player player){
        this.player = player;

        deckIcon = loadPicture("assets/Component/deckIcon.png",72,80).getImage();
        this.setPreferredSize(new Dimension(72,80));
        this.setBackground(SharedResource.SIAMESE_BRIGHT);

        this.update();
        //this.setBorder(new LineBorder(SharedResource.SIAMESE_DARK,5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(deckIcon,0,0,this.getWidth(),this.getHeight(),this);
        g.setColor(SharedResource.SIAMESE_BASE);

        //TODO: When someone implement this in Avenger or something call getFont and delete loadFont and Setfont
        //g.setFont(SharedResource.getCustomSizeFont(20));
        SharedResource.loadFont();
        g.setFont(new Font("Madimi One Regular",Font.PLAIN,30));

        //g.drawString(String.valueOf(cardCount),12,55);
        FontMetrics fm = g.getFontMetrics();
        int x = (75 - fm.stringWidth(cardCount+"")) / 2 -10;
        int y = (75 + fm.getAscent()) / 2 ;
        g.drawString(cardCount+"", x, y);
    }

    public void update(){
        if (cardCount != player.getDeck().getCards().size()){
            cardCount = player.getDeck().getCards().size();
            this.repaint();
        }
    }



}

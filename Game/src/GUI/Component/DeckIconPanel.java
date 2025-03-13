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

        //For test remove in production
        try {
            player.setDeck(Deck.LoadDeck("a"));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


        deckIcon = loadPicture("assets/Component/deckIcon.png",72,80).getImage();
        System.out.println(deckIcon);
        this.setPreferredSize(new Dimension(72,80));
        System.out.println("Panel width: " + this.getWidth());
        System.out.println("Panel height: " + this.getHeight());

        System.out.println("Image width: " + deckIcon.getWidth(null));
        System.out.println("Image height: " + deckIcon.getHeight(null));

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
        g.setFont(new Font("Madimi One Regular",Font.PLAIN,20));

        g.drawString(String.valueOf(cardCount),15,50);
    }

    private void update(){
        ArrayList<Card> d = player.getDeck().getCards();
        cardCount = 0;
        for (Card c:d){
            cardCount++;
        }
        this.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JPanel with Background Image");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        DeckIconPanel panel = new DeckIconPanel(new Player("a"));
        frame.add(panel);
        frame.setSize(500, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.setVisible(true);
        panel.repaint();
    }


}

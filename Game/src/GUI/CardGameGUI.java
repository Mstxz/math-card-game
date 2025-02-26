package GUI;

import Gameplay.Card;
import Gameplay.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CardGameGUI extends JFrame {
    private JPanel handPanel;
    private Player player;
    private Player enemy;
    private ArrayList<Card> hand; // List of card names (use actual Card objects in your game)

    public CardGameGUI(ArrayList<Card> hand,Player player,Player enemy) {
        setTitle("Card Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.player = player;
        this.enemy = enemy;
        // Panel to display cards in hand
        handPanel = new JPanel();
        handPanel.setLayout(new FlowLayout()); // Cards arranged in a row
        add(handPanel, BorderLayout.SOUTH);

        // List to store card names (you can replace this with actual Card objects)

        //this.hand = hand;

        // Button to draw a card
        JButton drawButton = new JButton("Draw Card");
        drawButton.addActionListener(e -> drawCard());

        JPanel controlPanel = new JPanel();
        controlPanel.add(drawButton);
        add(controlPanel, BorderLayout.NORTH);

        updateHand(); // Initial update
        this.setVisible(true);
    }

    private void drawCard() {
        // Simulate drawing a card (replace with actual deck logic)
        updateHand();
    }

    private void playCard(int index) {
        // Remove the clicked card from hand
        if (index >= 0 && index < hand.size()) {
            hand.get(index).action(player,enemy);
            Card c = hand.remove(index);
            player.getDeck().addDispose(c);
            Player.log(player,enemy);
            updateHand();
        }
    }

    public void updateHand() {
        handPanel.removeAll();// Clear old cards
        ArrayList<Integer> playable = Player.listPlayableCard(this.player,this.enemy);
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            JLabel cardLabel = new JLabel(card.getName(), SwingConstants.CENTER);
            cardLabel.setPreferredSize(new Dimension(80, 120)); // Set card size

            cardLabel.setOpaque(true);
            cardLabel.setBackground(Color.WHITE);
            if (playable.contains(i)) {
                cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border for visibility
                final int index = i; // Needed because of lambda scoping
                cardLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        playCard(index);
                    }
                });
            }
            else{
                cardLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            handPanel.add(cardLabel);
        }
        handPanel.revalidate();
        handPanel.repaint();
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CardGameGUI().setVisible(true));
    }*/
}

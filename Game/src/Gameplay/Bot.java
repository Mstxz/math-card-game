package Gameplay;

import java.util.ArrayList;
import java.util.Scanner;

public class Bot extends Player{
    public Bot(){
        super("Bot1");
    }

    @Override
    public void play(Player self, Player enemy) {
        self.draw();
        ArrayList<Integer> playable = self.showCard(self,enemy);
        Scanner sc = new Scanner(System.in);
        int index = playable.get(((int)(Math.random() * playable.size())));
        Card c = hand.remove(index);
        c.action(self,enemy);
        deck.addDispose(c);
    }
}

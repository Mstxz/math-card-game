package Gameplay.Bot;

import Gameplay.Card;
import Gameplay.Deck;
import Gameplay.Player;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Bot extends Player {
    private String description;

    public Bot(String name,String profile,String description,String deckName){
        super(name,profile);
        this.description = description;
        try {
            this.setDeck(Deck.LoadBotDeck(deckName));
        }
        catch (FileNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract Player getTargetId(Player self, Player enemy, Card c);
}

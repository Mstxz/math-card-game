package Gameplay.CardAction;

import Gameplay.Card;
import Gameplay.Player;

import java.util.ArrayList;

public class GetCard extends CardAction{

    private final Card[] cards;

    public GetCard(Player target, Card ...cards){
        super(CardActionType.GET_CARD,target);
        this.cards = cards;
    }

    public Card[] getCards() {
        return cards;
    }
}

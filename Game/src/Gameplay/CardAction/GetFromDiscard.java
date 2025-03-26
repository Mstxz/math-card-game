package Gameplay.CardAction;

import Gameplay.Player;

import java.util.Arrays;
import java.util.Collections;

public class GetFromDiscard extends CardAction{
    private final Integer[] cardsIndex;

    public GetFromDiscard(Player target, Integer[] cardsIndex) {
        super(CardActionType.GET_FROM_DISCARD,target);
        this.cardsIndex = cardsIndex;
        Arrays.sort(this.cardsIndex,Collections.reverseOrder());
    }

    public Integer[] getCards() {
        return cardsIndex;
    }
}

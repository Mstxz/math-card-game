package Gameplay.Cards;

import Gameplay.*;

import java.util.HashMap;

public class JesusCat extends Card implements HaveCondition {
    public JesusCat(){
        super("JesusCat","Add the card with highest difficulty which user has recently discarded to their hands.",7, Difficulty.BABY, CardType.YELLOW);
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-manaUsed);
        Card tmp = null;
        HashMap<Difficulty,Integer> hashMap = new HashMap<Difficulty,Integer>();
        hashMap.put(Difficulty.BABY,0);
        hashMap.put(Difficulty.EASY,1);
        hashMap.put(Difficulty.MEDIUM,2);
        hashMap.put(Difficulty.HARD,3);
        hashMap.put(Difficulty.EXPERT,4);
        for (int i = self.getDeck().getDispose().size()-1;i>=0;i--){
            if (self.getDeck().getDispose().get(i).getDifficult() == Difficulty.EXPERT){
                tmp = self.getDeck().getDispose().get(i);
                break;
            }
            Card a = self.getDeck().getDispose().get(i);
            if (tmp == null||hashMap.get(a.getDifficult())>hashMap.get(tmp.getDifficult())){
                tmp = a;
            }
        }
        self.getHand().add(tmp);
    }

    @Override
    public boolean checkCondition(Player user, Player receiver) {
        return !user.getDeck().getDispose().isEmpty();
    }
}

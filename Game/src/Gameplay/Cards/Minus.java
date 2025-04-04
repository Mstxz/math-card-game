package Gameplay.Cards;

import Gameplay.Card;
import Gameplay.CardType;
import Gameplay.Difficulty;
import Gameplay.Numbers.Constant;
import Gameplay.Player;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Minus extends Card {
    private int number;
    public Minus(int number,CardType type){
        super("Minus "+number,"Subtract "+number+" to hp",1, Difficulty.EASY, type);
        this.number = number;
        this.picture = "assets/Card/"+getFolder()+"/"+ getFolder() +"_HP-"+number+".png";
        if (type==CardType.GREEN){
            manaUsed+=1;
        }
    }

    @Override
    public void action(Player self, Player enemy) {
        self.setMana(self.getMana()-this.getManaUsed());
        Player receiver = this.getReceiver(self,enemy);
        receiver.setHp(receiver.getHp().subtract(new Constant(this.number)));
    }

    @Override
    public byte[] encode(){
        byte[] nameBytes = getClass().getSimpleName().getBytes(StandardCharsets.UTF_8);
        ByteBuffer bf = ByteBuffer.allocate(12+nameBytes.length);
        bf.putInt(3);
        bf.putInt(type.ordinal());
        bf.putInt(number);
        bf.put(nameBytes);
        return bf.array();
    }

}

package GameSocket;

import Gameplay.Player;

import java.util.Vector;

public class OtherPlayer extends Player {
    Vector<PlayerAction> pa;
    public OtherPlayer(String name) {
        super(name);
    }

    public OtherPlayer(String name, String profilePicture) {
        super(name, profilePicture);
    }


    public OtherPlayer(PlayerInfo info) {
        super(info.getName(), info.getProfilePicture());
        this.setHp(info.getHp());
        this.setMana(info.getMana());
        this.setPlayerNumber(info.getPlayerID());
    }

}

package GameSocket;

import Gameplay.Player;

import java.net.Socket;

public class OtherPlayer extends Player {
    private PlayerThread pt;

    public OtherPlayer(String name) {
        super(name);
    }
}

package GameSocket;

import Gameplay.Player;

import java.net.Socket;

public class OtherPlayer extends Player {
    private PlayerThread pt = null;
    private boolean isReady=false;
    public OtherPlayer(String name) {
        super(name);
    }

    public OtherPlayer(String name, PlayerThread pt) {
        super(name);
        this.pt = pt;
    }

    public PlayerThread getPt() {
        return pt;
    }

    public void setPt(PlayerThread pt) {
        this.pt = pt;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    @Override
    public String toString() {
        return "OtherPlayer{" +
                "name='" + name + '\'' +
                '}';
    }
}

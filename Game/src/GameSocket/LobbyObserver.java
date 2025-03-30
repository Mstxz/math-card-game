package GameSocket;

import Gameplay.Player;

import java.util.ArrayList;

public interface LobbyObserver {
    public abstract void onLobbyChange(ArrayList<Player> playerInfos);
    public abstract void onLobbyClose();
}

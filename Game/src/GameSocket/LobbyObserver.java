package GameSocket;

import java.util.ArrayList;

public interface LobbyObserver {
    public abstract void onLobbyChange(ArrayList<PlayerInfo> playerInfos);
    
}

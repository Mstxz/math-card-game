package GameSocket;

import java.util.ArrayList;

public interface LobbyObserver {
    public abstract void onChange(ArrayList<PlayerInfo> playerInfos);
}

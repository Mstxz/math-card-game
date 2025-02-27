package GameSocket;

public enum ClientState {
    IDLE,
    READY,
    PLAY,
    WAIT,
    LOADING
}

// IDLE = join lobby
// READY = Pressed Ready
// PLAY = client is in turn
// WAIT = other's turn
package GameSocket;

public enum ProtocolOperation {
    USER,
    READY,
    LOBBY,
    DECK,
    CARD,
    DRAW,
    HAND_UPDATE,
    END_TURN,
    START_TURN,
    START_GAME,
    END_GAME,
    ACKN,
    COUNT,
}

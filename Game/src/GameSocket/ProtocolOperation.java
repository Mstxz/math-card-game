package GameSocket;

public enum ProtocolOperation {
    USER,
    READY,
    LOBBY,
    DECK,
    CARD,
    SET_HP,
    SET_MANA,
    DRAW,
    DISCARD,

    STATUS_UPDATE,
    HAND_UPDATE,
    END_TURN,
    START_TURN,
    START_GAME,
    END_GAME,
    ACKN,
    COUNT,
}

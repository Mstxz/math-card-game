package GameSocket;

import Gameplay.Deck;

import java.net.Socket;

public class OnlinePlayer extends Thread {
    private Socket socket = null;
    private String username = null;
    private Deck deck = null;
    private boolean ready = false;

    public OnlinePlayer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}

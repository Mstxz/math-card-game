package GameSocket;

import Gameplay.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Game {
    private int capacity;
    private Player[] otherPlayers = null;
    Game(){
        this(2);
    }
    Game(int capacity){
        this.otherPlayers = new Player[capacity];
        this.capacity = capacity;
    }
    public void start(){
    }
}

package GameSocket;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        OnlineGame r = new OnlineGame(2);
        r.start();
        GameController p = new GameController("Arktik","localhost");
        GameController q = new GameController("Pupe","localhost");
        p.pressReady(Paths.get("Game/data/a.txt"));
//        try {
//            r.join();
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

}
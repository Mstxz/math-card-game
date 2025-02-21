package GameSocket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class GameController {
    private Client client;
    private GameStatus status;

    public GameController(String name,String address) {
        this.client = new Client(address,12345,new GameStatus());
        try {
            this.client.send("NAME "+name);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.client.start();
        System.out.println("Start");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void pressReady(Path deckPath){
        try{
            this.client.send("READY "+ deckPath.getFileName().toString());
            this.client.upload(deckPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void endTurn(){
        if (this.client.getStatus().isTurn()){
            try{
                this.client.send("END_TURN");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void draw(){
        if (this.client.getStatus().isTurn()){
            try{
                this.client.send("DRAW");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

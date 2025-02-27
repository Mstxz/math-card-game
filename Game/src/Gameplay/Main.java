package Gameplay;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(new Player("1"),new Bot());
        g.Play();
    }

}

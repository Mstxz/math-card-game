package Gameplay;

import Gameplay.Cards.Minus;
import Gameplay.Cards.Plus;
import Gameplay.Numbers.Constant;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Game g = new Game(new Player("1"),new Bot());
        g.Play();
    }

}

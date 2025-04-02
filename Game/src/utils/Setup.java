package utils;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Setup {
    public static void createDefaultDeck(){
        File deckFolder = new File("Assets");

        if (deckFolder.exists() && deckFolder.isDirectory() && deckFolder.listFiles().length == 0){
            File deckfile = new File("Assets/Default Deck.deck");
            try (BufferedInputStream sourceDeck = ResourceLoader.loadFileAsStream("assets/ConstantAsset/Default Deck.deck")) {
                Files.copy(sourceDeck, deckfile.toPath());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

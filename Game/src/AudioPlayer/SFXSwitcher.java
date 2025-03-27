package AudioPlayer;

import Gameplay.Card;

import java.awt.event.MouseEvent;
import java.util.EventListener;

public class SFXSwitcher {

    /*Default SFX*/
    private static String defaultclickUP = "Game/src/assets/Audio/SFX/Card_Desc_UP.wav";
    private static String defaultclickDown = "Game/src/assets/Audio/SFX/Card_Desc_Down.wav";
    private static String defaultCardDeckSelection = "Game/src/assets/Audio/SFX/Deck_Card_Click.wav";

    /*Easter egg SFX*/


    public static void cardDescUP(Card c){
        switch (c.getName()){
            case "LogAndRoll":
                SFXPlayer.playSound(defaultclickUP);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Rock_n_Roll_easterSFX_UP.wav");
                break;
            case "JesusCat":
                SFXPlayer.playSound(defaultclickUP);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Jesus_easterSFX_UP.wav");
            default:
                SFXPlayer.playSound(defaultclickUP);
                break;
        }
    }

    public static void cardDescDown(Card c){
        switch (c.getName()){
            case "LogAndRoll":
                SFXPlayer.playSound(defaultclickDown);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Rock_n_Roll_easterSFX_Down.wav");
                break;
            default:
                SFXPlayer.playSound(defaultclickDown);
                break;
        }
    }

    public static void deckSelectSwitcher(String c, MouseEvent e){
        switch (c){
            case "LogAndRoll":
                SFXPlayer.playSound(defaultCardDeckSelection);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Rock_n_Roll_easterSFX_Deck.wav");
                break;
            default:
                SFXPlayer.playSound(defaultCardDeckSelection);
                break;
        }
    }
}

package AudioPlayer;

import Gameplay.Card;

public class SFXSwitcher {
    private static String defaultclickUP = "Game/src/assets/Audio/SFX/Card_Desc_UP.wav";
    private static String defaultclickDown = "Game/src/assets/Audio/SFX/Card_Desc_Down.wav";
    public static void cardDescUP(Card c){
        switch (c.getName()){
            case "LogAndRoll":
                SFXPlayer.playSound(defaultclickUP, -10.0f);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Rock_n_Roll_easterSFX_UP.wav", -10.0f);
                break;
            default:
                SFXPlayer.playSound(defaultclickUP, -10.0f);
                break;
        }
    }

    public static void cardDescDown(Card c){
        switch (c.getName()){
            case "LogAndRoll":
                SFXPlayer.playSound(defaultclickDown, -10.0f);
                SFXPlayer.playSound("Game/src/assets/Audio/SFX/Rock_n_Roll_easterSFX_Down.wav", -10.0f);
                break;
            default:
                SFXPlayer.playSound(defaultclickDown, -10.0f);
                break;
        }
    }
}

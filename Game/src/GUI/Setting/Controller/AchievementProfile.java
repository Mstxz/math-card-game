package GUI.Setting.Controller;

import java.io.Serializable;
import java.util.ArrayList;

public class AchievementProfile implements Serializable {
    public static boolean isWinPupr;
    public static boolean isWinArsr;
    public static boolean isWinMystyr;
    public static boolean isWinWho;
    public static boolean isWinOmmThuk;

    public AchievementProfile(){
        isWinPupr = false;
        isWinArsr = false;
        isWinMystyr = false;
        isWinWho = false;
        isWinOmmThuk = false;
    }

    public static boolean isIsWinPupr() {
        return isWinPupr;
    }

    public static void setIsWinPupr(boolean isWinPupr) {
        AchievementProfile.isWinPupr = isWinPupr;
    }

    public static boolean isIsWinArsr() {
        return isWinArsr;
    }

    public static void setIsWinArsr(boolean isWinArsr) {
        AchievementProfile.isWinArsr = isWinArsr;
    }

    public static boolean isIsWinMystyr() {
        return isWinMystyr;
    }

    public static void setIsWinMystyr(boolean isWinMystyr) {
        AchievementProfile.isWinMystyr = isWinMystyr;
    }

    public static boolean isIsWinWho() {
        return isWinWho;
    }

    public static void setIsWinWho(boolean isWinWho) {
        AchievementProfile.isWinWho = isWinWho;
    }

    public static boolean isIsWinOmmThuk() {
        return isWinOmmThuk;
    }

    public static void setIsWinOmmThuk(boolean isWinOmmThuk) {
        AchievementProfile.isWinOmmThuk = isWinOmmThuk;
    }

    @Override
    public String toString() {
        return "AchievementProfile{" +
                "\n    isWinArsr: " + isWinArsr +
                "\n    isWinPupr: " + isWinPupr +
                "\n    isWinMystyr: " + isWinMystyr +
                "\n    isWinWho: " + isWinWho +
                "\n    isWinOmmThuk: " + isWinOmmThuk +
                "\n}";
    }
}

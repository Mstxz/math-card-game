package GUI.Setting.Controller;

import GUI.Setting.UserPreference;

import java.io.Serializable;
import java.util.ArrayList;

public class AchievementProfile implements Serializable {
    public boolean isWinPupr;
    public boolean isWinArsr;
    public boolean isWinMystyr;
    public boolean isWinWho;
    public boolean isWinOmmThuk;

    public AchievementProfile(){
        isWinPupr = false;
        isWinArsr = false;
        isWinMystyr = false;
        isWinWho = false;
        isWinOmmThuk = false;
    }

    public boolean isWinPupr() {
        return isWinPupr;
    }

    public void setWinPupr(boolean winPupr) {
        isWinPupr = winPupr;
        SettingController.updatePreference();
    }

    public boolean isWinArsr() {
        return isWinArsr;
    }

    public void setWinArsr(boolean winArsr) {
        isWinArsr = winArsr;
        SettingController.updatePreference();
    }

    public boolean isWinMystyr() {
        return isWinMystyr;
    }

    public void setWinMystyr(boolean winMystyr) {
        isWinMystyr = winMystyr;
        SettingController.updatePreference();
    }

    public boolean isWinWho() {
        return isWinWho;
    }

    public void setWinWho(boolean winWho) {
        isWinWho = winWho;
        SettingController.updatePreference();
    }

    public boolean isWinOmmThuk() {
        return isWinOmmThuk;
    }

    public void setWinOmmThuk(boolean winOmmThuk) {
        isWinOmmThuk = winOmmThuk;
        SettingController.updatePreference();
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

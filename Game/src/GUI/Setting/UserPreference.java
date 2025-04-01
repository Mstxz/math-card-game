package GUI.Setting;

import GUI.Component.RotatingSettingOption;
import GUI.Setting.Component.Profile;
import GUI.Setting.Controller.AchievementProfile;
import GUI.Setting.Controller.UserProfile;
import GUI.Setting.Controller.WinStat;

import java.io.*;

public class UserPreference implements Serializable {
    private int resolutionIndex;
    private int SFXVolume;
    private int MusicVolume;
    private static UserPreference userPreference = null;
    private UserProfile profile;
    private AchievementProfile achievementProfile;
    private WinStat winStat;

    public static UserPreference getInstance(){
        if (userPreference==null){
            File userFile = new File("Game/src/GUI/Setting/Preference.dat");
            if (userFile.exists()){
                try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(userFile));){
                    userPreference = (UserPreference) oi.readObject();
                    System.out.println(userPreference.toString());
                }
                catch (ClassNotFoundException | IOException e){
                    userPreference = new UserPreference();
                }
            }
            else {
                userPreference = new UserPreference();
            }
        }

        return userPreference;
    }

    public static void writeFile(){
        File f = new File("Game/src/GUI/Setting/Preference.dat");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));){
            out.writeObject(UserPreference.getInstance());

            System.out.println("write");
            System.out.println(UserPreference.getInstance().toString());
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Klong ha");
        }
    }

    private UserPreference(){
        this.resolutionIndex = 0;
        this.MusicVolume = 50;
        this.SFXVolume = 50;
        this.profile = new UserProfile();
        this.achievementProfile = new AchievementProfile();
        this.winStat = new WinStat();
    }

    private UserPreference(int resolutionIndex){
        this.resolutionIndex = resolutionIndex;
    }

    public int getResolutionIndex() {
        return resolutionIndex;
    }

    public void setResolutionIndex(int resolutionIndex) {
        this.resolutionIndex = resolutionIndex;
    }


    public int getSFXVolume() {
        return SFXVolume;
    }

    public void setSFXVolume(int SFXVolume) {
        this.SFXVolume = SFXVolume;
    }

    public int getMusicVolume() {
        return MusicVolume;
    }

    public void setMusicVolume(int musicVolume) {
        MusicVolume = musicVolume;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public AchievementProfile getAchievementProfile() {
        return achievementProfile;
    }

    public void setAchievementProfile(AchievementProfile achievementProfile) {
        this.achievementProfile = achievementProfile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "UserPreference{" +
                "resolutionIndex=" + resolutionIndex +
                ", SFXVolume=" + SFXVolume +
                ", MusicVolume=" + MusicVolume +
                ", profile=" + profile +
                ", achievementProfile=" + achievementProfile.toString() +
                '}';
    }

    public WinStat getWinStat() {
        return winStat;
    }

    public void setWinStat(WinStat winStat) {
        this.winStat = winStat;
    }
}

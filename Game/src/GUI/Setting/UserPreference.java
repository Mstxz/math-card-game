package GUI.Setting;

import GUI.Component.RotatingSettingOption;
import GUI.Setting.Component.Profile;
import GUI.Setting.Controller.UserProfile;

import java.io.*;

public class UserPreference implements Serializable {
    private int resolutionIndex;
    private int SFXVolume;
    private int MusicVolume;
    private static UserPreference userPreference = null;
    private UserProfile profile;

    public static UserPreference getInstance(){
        if (userPreference==null){
            File userFile = new File("Game/src/GUI/Setting/Preference.dat");
            if (userFile.exists()){
                try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(userFile));){
                    userPreference = (UserPreference) oi.readObject();
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
        }
        catch (IOException e){
            System.out.println("Klong ha");
        }
    }

    private UserPreference(){
        this.resolutionIndex = 0;
        this.MusicVolume = 50;
        this.SFXVolume = 50;
        this.profile = new UserProfile();
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

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}

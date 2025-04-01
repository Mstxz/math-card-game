package GUI.Setting.Controller;

import AudioPlayer.BGMPlayer;
import GUI.Router;
import GUI.Setting.UserPreference;

import java.util.ArrayList;
import java.util.List;

public class SettingController {
    public static ArrayList<String> resolutionList = new ArrayList<String>(List.of("1366x768","1920x1080"));

    public static void update(){
        UserProfile.loadProfile();
        updateResolution(UserPreference.getInstance().getResolutionIndex());
        updateSoundVolume();
    }

    public static void updateResolution(int index){
        String resolution = resolutionList.get(index);
        System.out.println("Index:" + index + " Resolution: " + resolution);
        if (resolution.equals("1920x1080")){
            Router.getMainFrame().setSize(1920,1080);
        }
        else if (resolution.equals("1366x768")){
            Router.getMainFrame().setSize(1366,768);
        }
    }

    public static void updateSoundVolume(){
        BGMPlayer.updateVolume();
    }

    public static void updatePreference(){
        UserProfile.loadProfile();
        UserPreference.writeFile();
    }
}

package GUI.Setting.Controller;

import AudioPlayer.BGMPlayer;
import GUI.Router;
import GUI.Setting.UserPreference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingController {
    public static ArrayList<String> resolutionList = new ArrayList<String>(List.of("1920x1080","Fullscreen"));

    public static void update(){
        UserProfile.loadProfile();
        updateResolution(UserPreference.getInstance().getResolutionIndex());
        updateSoundVolume();
    }

    public static void updateResolution(int index){
        String resolution = resolutionList.get(index);
        if (resolution.equals("1920x1080")){
            Router.getMainFrame().setSize(1920,1080);
            Router.getMainFrame().dispose();
            Router.getMainFrame().setLocation(0,0);
            Router.getMainFrame().setUndecorated(false);
            Router.getMainFrame().setVisible(true);
        }
        else if (resolution.equals("Fullscreen")){
            Router.getMainFrame().setSize(Toolkit.getDefaultToolkit().getScreenSize());
            Router.getMainFrame().dispose();
            Router.getMainFrame().setLocation(0,0);
            Router.getMainFrame().setUndecorated(true);
            Router.getMainFrame().setVisible(true);
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

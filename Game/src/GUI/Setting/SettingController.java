package GUI.Setting;

import GUI.Router;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingController {
    public static ArrayList<String> resolutionList = new ArrayList<String>(List.of("1366x768","1920x1080"));

    public static void update(){
        updateResolution(UserPreference.getInstance().getResolutionIndex());
    }

    public static void updateResolution(int index){
        String resolution = resolutionList.get(index);
        if (resolution.equals("1920x1080")){
            Router.getMainFrame().setSize(1920,1080);
        }
        else if (resolution.equals("1366x768")){
            Router.getMainFrame().setSize(1366,768);
        }
    }

    public static void updatePreference(){
        UserPreference.writeFile();
    }
}
